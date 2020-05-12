package io.mercury.ftdc.gateway;

import static io.mercury.common.thread.ThreadHelper.sleep;
import static io.mercury.common.thread.ThreadHelper.startNewThread;

import java.io.File;
import java.lang.annotation.Native;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import org.slf4j.Logger;

import ctp.thostapi.CThostFtdcDepthMarketDataField;
import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import ctp.thostapi.CThostFtdcInvestorPositionField;
import ctp.thostapi.CThostFtdcMdApi;
import ctp.thostapi.CThostFtdcMdSpi;
import ctp.thostapi.CThostFtdcOrderActionField;
import ctp.thostapi.CThostFtdcOrderField;
import ctp.thostapi.CThostFtdcQryInstrumentField;
import ctp.thostapi.CThostFtdcQryInvestorPositionField;
import ctp.thostapi.CThostFtdcQryOrderField;
import ctp.thostapi.CThostFtdcQrySettlementInfoField;
import ctp.thostapi.CThostFtdcQryTradingAccountField;
import ctp.thostapi.CThostFtdcReqAuthenticateField;
import ctp.thostapi.CThostFtdcReqUserLoginField;
import ctp.thostapi.CThostFtdcRspAuthenticateField;
import ctp.thostapi.CThostFtdcRspInfoField;
import ctp.thostapi.CThostFtdcRspUserLoginField;
import ctp.thostapi.CThostFtdcSpecificInstrumentField;
import ctp.thostapi.CThostFtdcTradeField;
import ctp.thostapi.CThostFtdcTraderApi;
import ctp.thostapi.CThostFtdcTraderSpi;
import ctp.thostapi.CThostFtdcTradingAccountField;
import ctp.thostapi.THOST_TE_RESUME_TYPE;
import io.mercury.common.collections.MutableSets;
import io.mercury.common.collections.queue.api.Queue;
import io.mercury.common.datetime.DateTimeUtil;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.sys.SysProperties;
import io.mercury.common.util.Assertor;
import io.mercury.common.util.StringUtil;
import io.mercury.ftdc.gateway.bean.FtdcDepthMarketData;
import io.mercury.ftdc.gateway.bean.FtdcInputOrderAction;
import io.mercury.ftdc.gateway.bean.FtdcOrderAction;
import io.mercury.ftdc.gateway.bean.RspMsg;
import io.mercury.ftdc.gateway.bean.RspTraderConnect;
import io.mercury.ftdc.gateway.converter.FtdcDepthMarketDataConverter;
import io.mercury.ftdc.gateway.converter.FtdcInputOrderActionConverter;
import io.mercury.ftdc.gateway.converter.FtdcInputOrderConverter;
import io.mercury.ftdc.gateway.converter.FtdcOrderActionConverter;
import io.mercury.ftdc.gateway.converter.FtdcOrderConverter;
import io.mercury.ftdc.gateway.converter.FtdcTradeConverter;

@NotThreadSafe
public class FtdcGateway {

	private static final Logger log = CommonLoggerFactory.getLogger(FtdcGateway.class);

	static {
		try {
			// 根据操作系统选择加载不同库文件
			if (SysProperties.OS_NAME.toLowerCase().startsWith("windows")) {
				log.info("Copy win64 library file to [java.library.path]...");
				log.info("java.library.path -> {}", SysProperties.JAVA_LIBRARY_PATH);
				// TODO 复制到相应目录
			} else {
				log.info("Copy linux64 library file to [java.library.path]...");
				log.info("java.library.path -> {}", SysProperties.JAVA_LIBRARY_PATH);
				// TODO 复制到相应目录
			}
			log.info("Loading CTP library...");
			System.loadLibrary("thostapi_wrap");
			System.loadLibrary("thosttraderapi_se");
			System.loadLibrary("thostmduserapi_se");
			log.info("Load library success...");
		} catch (Throwable e) {
			log.error("Load library error...", e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * id
	 */
	private String gatewayId;

	/**
	 * 基础配置信息
	 */
	private FtdcConfig ftdcConfig;

	@Native
	private CThostFtdcMdApi ftdcMdApi;
	@Native
	private CThostFtdcTraderApi ftdcTraderApi;

	/**
	 * 
	 */
	private volatile boolean isInitialize = false;
	private Queue<RspMsg> bufferQueue;

	private int mdRequestId = -1;
	private int traderRequestId = -1;

	private boolean isMdLogin;
	private boolean isTraderLogin;
	private boolean isAuthenticate;

	private int frontID;
	private int sessionID;

	public FtdcGateway(String gatewayId, @Nonnull FtdcConfig ftdcConfig, @Nonnull Queue<RspMsg> bufferQueue) {
		this.gatewayId = gatewayId;
		this.ftdcConfig = Assertor.nonNull(ftdcConfig, "ftdcConfig");
		this.bufferQueue = Assertor.nonNull(bufferQueue, "bufferQueue");
	}

	private File generateTempDir() {
		// 创建临时文件存储目录
		File tempDir = new File(
				SysProperties.JAVA_IO_TMPDIR + File.separator + "ctp-" + gatewayId + "-" + DateTimeUtil.date());
		log.info("FtdcGateway :: Temp file dir is -> {}", tempDir.getAbsolutePath());
		if (!tempDir.exists())
			tempDir.mkdirs();
		return tempDir;
	}

	/**
	 * 启动并挂起线程
	 */
	public void initAndJoin() {
		if (!isInitialize) {
			// 获取临时文件目录
			File tempDir = generateTempDir();
			log.info("FtdcGateway :: TraderApi version {}", CThostFtdcTraderApi.GetApiVersion());
			log.info("FtdcGateway :: MdApi version {}", CThostFtdcMdApi.GetApiVersion());
			try {
				startNewThread(() -> mdInitAndJoin(tempDir), "Md-Spi-Thread");
				sleep(2000);
				startNewThread(() -> traderInitAndJoin(tempDir), "Trader-Spi-Thread");
				this.isInitialize = true;
			} catch (Exception e) {
				log.error("FtdcGateway :: Method initAndJoin throw Exception -> {}", e.getMessage(), e);
				throw new RuntimeException(e);
			}
		}
	}

	private void mdInitAndJoin(File tempDir) {
		// 指定md临时文件地址
		String mdTempFilePath = new File(tempDir, "md").getAbsolutePath();
		log.info("FtdcGateway :: Gateway -> {} md use temp file path : {}", gatewayId, mdTempFilePath);
		// 创建mdApi
		this.ftdcMdApi = CThostFtdcMdApi.CreateFtdcMdApi(mdTempFilePath);
		// 创建mdSpi
		CThostFtdcMdSpi ftdcMdSpi = new FtdcMdSpiImpl(this);
		// 将mdSpi注册到mdApi
		ftdcMdApi.RegisterSpi(ftdcMdSpi);
		// 注册到md前置机
		ftdcMdApi.RegisterFront(ftdcConfig.getMdAddr());
		// 初始化mdApi
		log.info("FtdcGateway :: Call function mdApi.Init()...");
		ftdcMdApi.Init();
		// 阻塞当前线程
		log.info("FtdcGateway :: Call function mdApi.Join()...");
		ftdcMdApi.Join();
	}

	private void traderInitAndJoin(File tempDir) {
		// 指定trader临时文件地址
		String traderTempFilePath = new File(tempDir, "trader").getAbsolutePath();
		log.info("FtdcGateway :: Gateway -> {} trader use temp file path : {}", gatewayId, traderTempFilePath);
		// 创建traderApi
		this.ftdcTraderApi = CThostFtdcTraderApi.CreateFtdcTraderApi(traderTempFilePath);
		// 创建traderSpi
		CThostFtdcTraderSpi ftdcTraderSpi = new FtdcTraderSpiImpl(this);
		// 将traderSpi注册到traderApi
		ftdcTraderApi.RegisterSpi(ftdcTraderSpi);
		// 注册到trader前置机
		ftdcTraderApi.RegisterFront(ftdcConfig.getTraderAddr());
		/// THOST_TERT_RESTART:从本交易日开始重传
		/// THOST_TERT_RESUME:从上次收到的续传
		/// THOST_TERT_QUICK:只传送登录后私有流的内容
		// 订阅公有流和私有流
		ftdcTraderApi.SubscribePublicTopic(THOST_TE_RESUME_TYPE.THOST_TERT_RESUME);
		ftdcTraderApi.SubscribePrivateTopic(THOST_TE_RESUME_TYPE.THOST_TERT_RESUME);
		// 初始化traderApi
		log.info("FtdcGateway :: Call function traderApi.Init()...");
		ftdcTraderApi.Init();
		// 阻塞当前线程
		log.info("FtdcGateway :: Call function traderApi.Join()...");
		ftdcTraderApi.Join();
	}

	/*
	 ****************************************************************
	 * 以下是行情相关接口与回调
	 */

	/**
	 * 行情前置连接回调
	 */
	void onMdFrontConnected() {
		CThostFtdcReqUserLoginField reqUserLogin = new CThostFtdcReqUserLoginField();
		reqUserLogin.setBrokerID(ftdcConfig.getBrokerId());
		reqUserLogin.setUserID(ftdcConfig.getUserId());
		reqUserLogin.setPassword(ftdcConfig.getPassword());
		ftdcMdApi.ReqUserLogin(reqUserLogin, ++mdRequestId);
		log.info("FtdcGateway :: Send Md ReqUserLogin OK");
	}

	/**
	 * 
	 */
	void onMdFrontDisconnected() {
		log.info("FtdcGateway :: Md front disconnected");
		// TODO 行情断开处理逻辑
	}

	/**
	 * 行情登录回调
	 * 
	 * @param rspUserLogin
	 */
	void onMdRspUserLogin(CThostFtdcRspUserLoginField rspUserLogin) {
		log.info("FtdcGateway :: Md UserLogin Success -> Brokerid==[{}] UserID==[{}]", rspUserLogin.getBrokerID(),
				rspUserLogin.getUserID());
		this.isMdLogin = true;
		//
	}

	private Set<String> subscribeInstruementSet = MutableSets.newUnifiedSet();

	/**
	 * 行情订阅接口
	 */
	public void SubscribeMarketData(Set<String> inputInstruementSet) {
		subscribeInstruementSet.addAll(inputInstruementSet);
		log.info("FtdcGateway :: Add Subscribe Instruement set -> Count==[{}]", inputInstruementSet.size());
		if (isMdLogin && !subscribeInstruementSet.isEmpty())
			innerSubscribeMarketData();
		else
			log.info("FtdcGateway :: Cannot SubscribeMarketData -> isMdLogin==[false]");
	}

	private void innerSubscribeMarketData() {
		if (!subscribeInstruementSet.isEmpty()) {
			String[] instruementIdList = new String[subscribeInstruementSet.size()];
			Iterator<String> iterator = subscribeInstruementSet.iterator();
			for (int i = 0; i < instruementIdList.length; i++) {
				instruementIdList[i] = iterator.next();
				log.info("FtdcGateway :: Add Subscribe Instruement -> instruementCode==[{}]", instruementIdList[i]);
			}
			ftdcMdApi.SubscribeMarketData(instruementIdList, instruementIdList.length);
			subscribeInstruementSet.clear();
			log.info("FtdcGateway :: Send SubscribeMarketData -> count==[{}]", instruementIdList.length);
		}
	}

	/**
	 * 订阅行情回调
	 * 
	 * @param specificInstrument
	 */
	void onRspSubMarketData(CThostFtdcSpecificInstrumentField specificInstrumentField) {
		log.info("FtdcGateway :: SubscribeMarketData Success -> InstrumentCode==[{}]",
				specificInstrumentField.getInstrumentID());
	}

	private Function<CThostFtdcDepthMarketDataField, FtdcDepthMarketData> depthMarketDataConverter = new FtdcDepthMarketDataConverter();

	/**
	 * 行情推送回调
	 * 
	 * @param depthMarketData
	 */
	void onRtnDepthMarketData(CThostFtdcDepthMarketDataField depthMarketData) {
		log.debug(
				"FtdcGateway :: Gateway onRtnDepthMarketData -> InstrumentID == [{}], UpdateTime==[{}], UpdateMillisec==[{}]",
				depthMarketData.getInstrumentID(), depthMarketData.getUpdateTime(),
				depthMarketData.getUpdateMillisec());
		bufferQueue.enqueue(new RspMsg(depthMarketDataConverter.apply(depthMarketData)));
	}

	/*
	 ****************************************************************
	 * 以下是报单, 撤单相关接口与回调
	 */

	/**
	 * 交易前置机连接回调
	 */
	void onTraderFrontConnected() {
		if (!StringUtil.nonEmpty(ftdcConfig.getAuthCode()) && !isAuthenticate) {
			// 验证
			CThostFtdcReqAuthenticateField authenticateField = new CThostFtdcReqAuthenticateField();
			authenticateField.setUserID(ftdcConfig.getUserId());
			authenticateField.setBrokerID(ftdcConfig.getBrokerId());
			authenticateField.setAuthCode(ftdcConfig.getAuthCode());
			int nRequestID = ++traderRequestId;
			ftdcTraderApi.ReqAuthenticate(authenticateField, nRequestID);
			log.info("FtdcGateway :: Send ReqAuthenticate OK -> nRequestID==[{}]", nRequestID);
		} else {
			// TODO
		}
	}

	/**
	 * 交易前置断开回调
	 */
	void onTraderFrontDisconnected() {
		log.info("FtdcGateway :: Trader front disconnected");
		this.isTraderLogin = false;
		this.isAuthenticate = false;
		// TODO 交易前置断开处理
	}

	/**
	 * 交易登录回调
	 * 
	 * @param rspUserLogin
	 */
	void onTraderRspUserLogin(CThostFtdcRspUserLoginField ftdcRspUserLogin) {
		log.info("Trader UserLogin Success -> Brokerid==[{}] UserID==[{}]", ftdcRspUserLogin.getBrokerID(),
				ftdcRspUserLogin.getUserID());
		this.frontID = ftdcRspUserLogin.getFrontID();
		this.sessionID = ftdcRspUserLogin.getSessionID();
		this.isTraderLogin = true;
		bufferQueue.enqueue(
				new RspMsg(new RspTraderConnect().setAvailable(true).setFrontID(frontID).setSessionID(sessionID)));
	}

	void onRspAuthenticate(CThostFtdcRspAuthenticateField ftdcRspAuthenticate) {
		this.isAuthenticate = true;
		CThostFtdcReqUserLoginField reqUserLogin = new CThostFtdcReqUserLoginField();
		reqUserLogin.setBrokerID(ftdcConfig.getBrokerId());
		reqUserLogin.setUserID(ftdcConfig.getUserId());
		reqUserLogin.setPassword(ftdcConfig.getPassword());
		int nRequestID = ++traderRequestId;
		ftdcTraderApi.ReqUserLogin(reqUserLogin, nRequestID);
		log.info("Send Trader ReqUserLogin OK -> nRequestID == {}", nRequestID);

	}

	/****************
	 * 报单接口
	 */
	public void ReqOrderInsert(CThostFtdcInputOrderField inputOrderField) {
		if (isTraderLogin) {
			// 设置账号信息
			inputOrderField.setAccountID(ftdcConfig.getAccountId());
			inputOrderField.setUserID(ftdcConfig.getUserId());
			inputOrderField.setBrokerID(ftdcConfig.getBrokerId());
			inputOrderField.setIPAddress(ftdcConfig.getIpAddr());
			inputOrderField.setMacAddress(ftdcConfig.getMacAddr());
			int nRequestID = ++traderRequestId;
			ftdcTraderApi.ReqOrderInsert(inputOrderField, nRequestID);
			log.info("Send ReqOrderInsert OK -> orderRef==[{}], nRequestID==[{}]", inputOrderField.getOrderRef(),
					nRequestID);
		} else
			log.error("Trader Error :: TraderApi is not login");
	}

	private FtdcInputOrderConverter ftdcInputOrderConverter = new FtdcInputOrderConverter();

	/**
	 * 报单回调
	 * 
	 * @param rspOrderInsert
	 */
	void onRspOrderInsert(CThostFtdcInputOrderField inputOrderField) {
		bufferQueue.enqueue(new RspMsg(ftdcInputOrderConverter.apply(inputOrderField)));
	}

	/**
	 * 报单错误回调
	 * 
	 * @param inputOrder
	 */
	void onErrRtnOrderInsert(CThostFtdcInputOrderField inputOrderField) {
		bufferQueue.enqueue(new RspMsg(ftdcInputOrderConverter.apply(inputOrderField)));
	}

	private FtdcOrderConverter ftdcOrderConverter = new FtdcOrderConverter();

	/**
	 * 报单推送
	 * 
	 * @param rtnOrder
	 */
	void onRtnOrder(CThostFtdcOrderField orderField) {
		log.debug("Gateway onRtnOrder -> AccountID==[{}], OrderRef==[{}]", orderField.getAccountID(),
				orderField.getOrderRef());
		bufferQueue.enqueue(new RspMsg(ftdcOrderConverter.apply(orderField)));
	}

	private FtdcTradeConverter ftdcTradeConverter = new FtdcTradeConverter();

	/**
	 * 成交推送
	 * 
	 * @param rtnTrade
	 */
	void onRtnTrade(CThostFtdcTradeField tradeField) {
		log.debug("Gateway onRtnTrade -> OrderRef==[{}], Price==[{}], Volume==[{}]", tradeField.getOrderRef(),
				tradeField.getPrice(), tradeField.getVolume());
		bufferQueue.enqueue(new RspMsg(ftdcTradeConverter.apply(tradeField)));
	}

	/****************
	 * 撤单接口
	 */
	public void ReqOrderAction(CThostFtdcInputOrderActionField ftdcInputOrderAction) {
		if (isTraderLogin) {
			// 设置账号信息
			ftdcInputOrderAction.setBrokerID(ftdcConfig.getBrokerId());
			ftdcInputOrderAction.setUserID(ftdcConfig.getUserId());
			ftdcInputOrderAction.setBrokerID(ftdcConfig.getBrokerId());
			ftdcInputOrderAction.setIPAddress(ftdcConfig.getIpAddr());
			ftdcInputOrderAction.setMacAddress(ftdcConfig.getMacAddr());
			int nRequestID = ++traderRequestId;
			ftdcTraderApi.ReqOrderAction(ftdcInputOrderAction, nRequestID);
			log.info("Send ReqOrderAction OK -> orderRef==[{}], nRequestID==[{}]", ftdcInputOrderAction.getOrderRef(),
					nRequestID);
		} else
			log.error("Trader Error :: TraderApi is not login");
	}

	private Function<CThostFtdcInputOrderActionField, FtdcInputOrderAction> ftdcInputOrderActionConverter = new FtdcInputOrderActionConverter();

	/**
	 * 撤单回调
	 * 
	 * @param inputOrderAction
	 */
	void onRspOrderAction(CThostFtdcInputOrderActionField inputOrderActionField) {
		bufferQueue.enqueue(new RspMsg(ftdcInputOrderActionConverter.apply(inputOrderActionField)));
	}

	private Function<CThostFtdcOrderActionField, FtdcOrderAction> ftdcOrderActionConverter = new FtdcOrderActionConverter();

	/**
	 * 撤单错误回调
	 * 
	 * @param orderAction
	 */
	void onErrRtnOrderAction(CThostFtdcOrderActionField orderActionField) {
		bufferQueue.enqueue(new RspMsg(ftdcOrderActionConverter.apply(orderActionField)));
	}

	/**
	 * 错误推送
	 * 
	 * @param rspInfo
	 */
	void onRspError(CThostFtdcRspInfoField rspInfo) {
		log.error("Gateway onRspError -> ErrorID==[{}], ErrorMsg==[{}]", rspInfo.getErrorID(), rspInfo.getErrorMsg());
	}

	public void ReqQryOrder(String exchangeId) {
		int nRequestID = ++traderRequestId;
		CThostFtdcQryOrderField ftdcQryOrder = new CThostFtdcQryOrderField();
		ftdcQryOrder.setBrokerID(ftdcConfig.getBrokerId());
		ftdcQryOrder.setInvestorID(ftdcConfig.getInvestorId());
		ftdcQryOrder.setExchangeID(exchangeId);
		ftdcTraderApi.ReqQryOrder(ftdcQryOrder, nRequestID);
		log.info("Send ReqQryOrder OK -> nRequestID==[{}]", nRequestID);
	}

	void onRspQryOrder(CThostFtdcOrderField ftdcOrder, boolean isLast) {
		// TODO Inbound
	}

	public void ReqQryTradingAccount() {
		CThostFtdcQryTradingAccountField qryTradingAccount = new CThostFtdcQryTradingAccountField();
		qryTradingAccount.setBrokerID(ftdcConfig.getBrokerId());
		qryTradingAccount.setInvestorID(ftdcConfig.getInvestorId());
		qryTradingAccount.setCurrencyID(ftdcConfig.getCurrencyId());
		int nRequestID = ++traderRequestId;
		ftdcTraderApi.ReqQryTradingAccount(qryTradingAccount, nRequestID);
		log.info("Send ReqQryTradingAccount OK -> nRequestID==[{}]", nRequestID);
	}

	void onQryTradingAccount(CThostFtdcTradingAccountField tradingAccountField, boolean bIsLast) {
		log.info("onQryTradingAccount -> Balance==[{}] Available==[{}] WithdrawQuota==[{}] Credit==[{}]",
				tradingAccountField.getBalance(), tradingAccountField.getAvailable(),
				tradingAccountField.getWithdrawQuota(), tradingAccountField.getCredit());

		// TODO Inbound
	}

	public void ReqQryInvestorPosition() {
		// ThreadUtil.startNewThread(() -> innerQureyPosition());
		CThostFtdcQryInvestorPositionField qryInvestorPositionField = new CThostFtdcQryInvestorPositionField();
		qryInvestorPositionField.setBrokerID(ftdcConfig.getBrokerId());
		qryInvestorPositionField.setInvestorID(ftdcConfig.getInvestorId());
		int nRequestID = ++traderRequestId;
		ftdcTraderApi.ReqQryInvestorPosition(qryInvestorPositionField, nRequestID);
		log.info("Send ReqQryInvestorPosition OK -> nRequestID==[{}]", nRequestID);
	}

	void onRspQryInvestorPosition(CThostFtdcInvestorPositionField investorPositionField, boolean isLast) {
		log.info("onRspQryInvestorPosition -> InstrumentID==[{}] InvestorID==[{}] Position==[{}]",
				investorPositionField.getInstrumentID(), investorPositionField.getInvestorID(),
				investorPositionField.getPosition());
		// TODO Inbound
	}

	/**
	 * 查询结算信息
	 */
	public void ReqQrySettlementInfo() {
		CThostFtdcQrySettlementInfoField qrySettlementInfo = new CThostFtdcQrySettlementInfoField();
		qrySettlementInfo.setBrokerID(ftdcConfig.getBrokerId());
		qrySettlementInfo.setInvestorID(ftdcConfig.getInvestorId());
		qrySettlementInfo.setTradingDay(ftdcConfig.getTradingDay());
		qrySettlementInfo.setAccountID(ftdcConfig.getAccountId());
		qrySettlementInfo.setCurrencyID(ftdcConfig.getCurrencyId());
		int nRequestID = ++traderRequestId;
		ftdcTraderApi.ReqQrySettlementInfo(qrySettlementInfo, nRequestID);
		log.info("Send ReqQrySettlementInfo OK -> nRequestID==[{}]", nRequestID);
	}

	/**
	 * 查询标的
	 */
	public void ReqQryInstrument() {
		CThostFtdcQryInstrumentField qryInstrument = new CThostFtdcQryInstrumentField();
		int nRequestID = ++traderRequestId;
		ftdcTraderApi.ReqQryInstrument(qryInstrument, nRequestID);
		log.info("Send ReqQryInstrument OK -> nRequestID==[{}]", nRequestID);
	}

}