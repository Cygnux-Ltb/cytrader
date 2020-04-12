package io.mercury.ctp.gateway;

import static io.mercury.common.thread.ThreadUtil.sleep;
import static io.mercury.common.thread.ThreadUtil.startNewThread;

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
import ctp.thostapi.CThostFtdcQrySettlementInfoField;
import ctp.thostapi.CThostFtdcQryTradingAccountField;
import ctp.thostapi.CThostFtdcReqAuthenticateField;
import ctp.thostapi.CThostFtdcReqUserLoginField;
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
import io.mercury.ctp.gateway.bean.CtpConfigInfo;
import io.mercury.ctp.gateway.bean.RspMsg;
import io.mercury.ctp.gateway.bean.rsp.RspDepthMarketData;
import io.mercury.ctp.gateway.converter.RspOrderActionConverter;
import io.mercury.ctp.gateway.converter.RspOrderInsertConverter;
import io.mercury.ctp.gateway.converter.RtnOrderConverter;
import io.mercury.ctp.gateway.converter.RtnTradeConverter;

@NotThreadSafe
public class CtpGateway {

	private static final Logger log = CommonLoggerFactory.getLogger(CtpGateway.class);

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
	private CtpConfigInfo ctpConfigInfo;

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
	private boolean isAuth;

	public CtpGateway(String gatewayId, @Nonnull CtpConfigInfo ctpConfigInfo, @Nonnull Queue<RspMsg> inboundBuffer) {
		this.gatewayId = gatewayId;
		this.ctpConfigInfo = Assertor.nonNull(ctpConfigInfo, "ctpConfigInfo");
		this.bufferQueue = Assertor.nonNull(bufferQueue, "bufferQueue");
	}

	private File generateTempDir() {
		// 创建临时文件存储目录
		File tempDir = new File(
				SysProperties.JAVA_IO_TMPDIR + File.separator + "ctp-" + gatewayId + "-" + DateTimeUtil.date());
		log.info("Temp file dir -> {}", tempDir.getAbsolutePath());
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
			log.info("TraderApi version {}", CThostFtdcTraderApi.GetApiVersion());
			log.info("MdApi version {}", CThostFtdcMdApi.GetApiVersion());
			try {
				startNewThread(() -> mdInitAndJoin(tempDir), "Md-Spi-Thread");
				sleep(2000);
				startNewThread(() -> traderInitAndJoin(tempDir), "Trader-Spi-Thread");
				this.isInitialize = true;
			} catch (Exception e) {
				log.error("method initAndJoin throw Exception -> {}", e.getMessage(), e);
				throw new RuntimeException(e);
			}
		}
	}

	private void mdInitAndJoin(File tempDir) {
		// 指定md临时文件地址
		String mdTempFilePath = new File(tempDir, "md").getAbsolutePath();
		log.info("{} md use temp file path : {}", gatewayId, mdTempFilePath);
		// 创建mdApi
		this.ftdcMdApi = CThostFtdcMdApi.CreateFtdcMdApi(mdTempFilePath);
		// 创建mdSpi
		CThostFtdcMdSpi ftdcMdSpi = new CtpMdSpiImpl(this);
		// 将mdSpi注册到mdApi
		ftdcMdApi.RegisterSpi(ftdcMdSpi);
		// 注册到md前置机
		ftdcMdApi.RegisterFront(ctpConfigInfo.getMdAddr());
		// 初始化mdApi
		log.info("Call mdApi.Init()...");
		ftdcMdApi.Init();
		// 阻塞当前线程
		log.info("Call mdApi.Join()...");
		ftdcMdApi.Join();
	}

	private void traderInitAndJoin(File tempDir) {
		// 指定trader临时文件地址
		String traderTempFilePath = new File(tempDir, "trader").getAbsolutePath();
		log.info("{} trader use temp file path : {}", gatewayId, traderTempFilePath);
		// 创建traderApi
		this.ftdcTraderApi = CThostFtdcTraderApi.CreateFtdcTraderApi(traderTempFilePath);
		// 创建traderSpi
		CThostFtdcTraderSpi ftdcTraderSpi = new CtpTraderSpiImpl(this);
		// 将traderSpi注册到traderApi
		ftdcTraderApi.RegisterSpi(ftdcTraderSpi);
		// 注册到trader前置机
		ftdcTraderApi.RegisterFront(ctpConfigInfo.getTraderAddr());
		/// THOST_TERT_RESTART:从本交易日开始重传
		/// THOST_TERT_RESUME:从上次收到的续传
		/// THOST_TERT_QUICK:只传送登录后私有流的内容
		// 订阅公有流和私有流
		ftdcTraderApi.SubscribePublicTopic(THOST_TE_RESUME_TYPE.THOST_TERT_RESUME);
		ftdcTraderApi.SubscribePrivateTopic(THOST_TE_RESUME_TYPE.THOST_TERT_RESUME);
		// 初始化traderApi
		log.info("Call traderApi.Init()...");
		ftdcTraderApi.Init();
		// 阻塞当前线程
		log.info("Call traderApi.Join()...");
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
		reqUserLogin.setBrokerID(ctpConfigInfo.getBrokerId());
		reqUserLogin.setUserID(ctpConfigInfo.getUserId());
		reqUserLogin.setPassword(ctpConfigInfo.getPassword());
		ftdcMdApi.ReqUserLogin(reqUserLogin, ++mdRequestId);
		log.info("Send Md ReqUserLogin OK");
	}

	/**
	 * 
	 */
	void onMdFrontDisconnected() {
		// TODO Auto-generated method stub

	}

	/**
	 * 行情登录回调
	 * 
	 * @param rspUserLogin
	 */
	void onMdRspUserLogin(CThostFtdcRspUserLoginField rspUserLogin) {
		log.info("Md UserLogin Success -> Brokerid==[{}] UserID==[{}]", rspUserLogin.getBrokerID(),
				rspUserLogin.getUserID());
		this.isMdLogin = true;
		rspUserLogin.getBrokerID();
		rspUserLogin.getUserID();
		rspUserLogin.getMaxOrderRef();
		// inboundBuffer.enqueue(RspMsg.)
		innerSubscribeMarketData();
	}

	private Set<String> subscribeInstruementSet = MutableSets.newUnifiedSet();

	/**
	 * 行情订阅接口
	 */
	public void subscribeMarketData(Set<String> inputInstruementSet) {
		subscribeInstruementSet.addAll(inputInstruementSet);
		log.info("Add Subscribe Instruement set -> Count==[{}]", inputInstruementSet.size());
		if (isMdLogin && !subscribeInstruementSet.isEmpty())
			innerSubscribeMarketData();
		else
			log.info("Cannot SubscribeMarketData -> isMdLogin==[false]");
	}

	private void innerSubscribeMarketData() {
		if (!subscribeInstruementSet.isEmpty()) {
			String[] instruementIdList = new String[subscribeInstruementSet.size()];
			Iterator<String> iterator = subscribeInstruementSet.iterator();
			for (int i = 0; i < instruementIdList.length; i++) {
				instruementIdList[i] = iterator.next();
				log.info("Add Subscribe Instruement -> instruementCode==[{}]", instruementIdList[i]);
			}
			ftdcMdApi.SubscribeMarketData(instruementIdList, instruementIdList.length);
			subscribeInstruementSet.clear();
			log.info("Send SubscribeMarketData -> count==[{}]", instruementIdList.length);
		}
	}

	/**
	 * 订阅行情回调
	 * 
	 * @param specificInstrument
	 */
	void onRspSubMarketData(CThostFtdcSpecificInstrumentField specificInstrument) {
		log.info("SubscribeMarketData Success -> InstrumentCode==[{}]", specificInstrument.getInstrumentID());
	}

	private Function<CThostFtdcDepthMarketDataField, RspDepthMarketData> depthMarketDataFunction = from -> {
		return new RspDepthMarketData().setTradingDay(from.getTradingDay()).setInstrumentID(from.getInstrumentID())
				.setExchangeID(from.getExchangeID()).setExchangeInstID(from.getExchangeInstID())
				.setLastPrice(from.getLastPrice()).setPreSettlementPrice(from.getPreSettlementPrice())
				.setPreClosePrice(from.getPreClosePrice()).setPreOpenInterest(from.getPreOpenInterest())
				.setOpenPrice(from.getOpenPrice()).setHighestPrice(from.getHighestPrice())
				.setLowestPrice(from.getLowestPrice()).setVolume(from.getVolume()).setTurnover(from.getTurnover())
				.setOpenInterest(from.getOpenInterest()).setClosePrice(from.getClosePrice())
				.setSettlementPrice(from.getSettlementPrice()).setUpperLimitPrice(from.getUpperLimitPrice())
				.setLowerLimitPrice(from.getLowerLimitPrice()).setPreDelta(from.getPreDelta())
				.setCurrDelta(from.getCurrDelta()).setBidPrice1(from.getBidPrice1()).setBidVolume1(from.getBidVolume1())
				.setAskPrice1(from.getAskPrice1()).setAskVolume1(from.getAskVolume1()).setBidPrice2(from.getBidPrice2())
				.setBidVolume2(from.getBidVolume2()).setAskPrice2(from.getAskPrice2())
				.setAskVolume2(from.getAskVolume2()).setBidPrice3(from.getBidPrice3())
				.setBidVolume3(from.getBidVolume3()).setAskPrice3(from.getAskPrice3())
				.setAskVolume3(from.getAskVolume3()).setBidPrice4(from.getBidPrice4())
				.setBidVolume4(from.getBidVolume4()).setAskPrice4(from.getAskPrice4())
				.setAskVolume4(from.getAskVolume4()).setBidPrice5(from.getBidPrice5())
				.setBidVolume5(from.getBidVolume5()).setAskPrice5(from.getAskPrice5())
				.setAskVolume5(from.getAskVolume5()).setAveragePrice(from.getAveragePrice())
				.setUpdateTime(from.getUpdateTime()).setUpdateMillisec(from.getUpdateMillisec())
				.setActionDay(from.getActionDay());
	};

	/**
	 * 行情推送回调
	 * 
	 * @param depthMarketData
	 */
	void onRtnDepthMarketData(CThostFtdcDepthMarketDataField depthMarketData) {
		log.debug("Gateway onRtnDepthMarketData -> InstrumentID == [{}], UpdateTime==[{}], UpdateMillisec==[{}]",
				depthMarketData.getInstrumentID(), depthMarketData.getUpdateTime(),
				depthMarketData.getUpdateMillisec());
		bufferQueue.enqueue(RspMsg.ofDepthMarketData(depthMarketDataFunction.apply(depthMarketData)));
	}

	/*
	 ****************************************************************
	 * 以下是报单, 撤单相关接口与回调
	 */

	/**
	 * 交易前置机连接回调
	 */
	void onTraderFrontConnected() {
		CThostFtdcReqUserLoginField reqUserLogin = new CThostFtdcReqUserLoginField();
		reqUserLogin.setBrokerID(ctpConfigInfo.getBrokerId());
		reqUserLogin.setUserID(ctpConfigInfo.getUserId());
		reqUserLogin.setPassword(ctpConfigInfo.getPassword());
		reqUserLogin.setUserProductInfo(ctpConfigInfo.getUserProductInfo());
		ftdcTraderApi.ReqUserLogin(reqUserLogin, ++traderRequestId);
		log.info("Send Trader ReqUserLogin OK");
	}

	/**
	 * 
	 */
	void onTraderFrontDisconnected() {
		log.info("Send Trader ReqUserLogin OK");
	}

	/**
	 * 交易登录回调
	 * 
	 * @param rspUserLogin
	 */
	void onTraderRspUserLogin(CThostFtdcRspUserLoginField ftdcRspUserLogin) {
		log.info("Trader UserLogin Success -> Brokerid==[{}] UserID==[{}]", ftdcRspUserLogin.getBrokerID(),
				ftdcRspUserLogin.getUserID());
		int frontID = ftdcRspUserLogin.getFrontID();
		int sessionID = ftdcRspUserLogin.getSessionID();
		this.isTraderLogin = true;

		if (!StringUtil.nonEmpty(ctpConfigInfo.getAuthCode()) && !isAuth) {
			// 验证
			CThostFtdcReqAuthenticateField authenticateField = new CThostFtdcReqAuthenticateField();
			authenticateField.setUserID(ctpConfigInfo.getUserId());
			authenticateField.setBrokerID(ctpConfigInfo.getBrokerId());
			authenticateField.setAuthCode(ctpConfigInfo.getAuthCode());
			authenticateField.setUserProductInfo(ctpConfigInfo.getUserProductInfo());
			int nRequestID = ++traderRequestId;
			ftdcTraderApi.ReqAuthenticate(authenticateField, nRequestID);
			log.info("Send ReqAuthenticate OK -> nRequestID==[{}]", nRequestID);
		}

	}

	/****************
	 * 报单接口
	 */
	public void ReqOrderInsert(CThostFtdcInputOrderField ftdcInputOrder) {
		if (isTraderLogin) {
			// 设置账号信息
			ftdcInputOrder.setAccountID(ctpConfigInfo.getAccountId());
			ftdcInputOrder.setUserID(ctpConfigInfo.getUserId());
			ftdcInputOrder.setBrokerID(ctpConfigInfo.getBrokerId());
			ftdcInputOrder.setIPAddress(ctpConfigInfo.getReportIpAddr());
			ftdcInputOrder.setMacAddress(ctpConfigInfo.getReportMacAddr());
			int nRequestID = ++traderRequestId;
			ftdcTraderApi.ReqOrderInsert(ftdcInputOrder, nRequestID);
			log.info("Send ReqOrderInsert OK -> orderRef==[{}], nRequestID==[{}]", ftdcInputOrder.getOrderRef(),
					nRequestID);
		} else
			log.error("Trader Error :: TraderApi is not login");
	}

	private RspOrderInsertConverter orderInsertConverter = new RspOrderInsertConverter();

	/**
	 * 报单回调
	 * 
	 * @param rspOrderInsert
	 */
	void onRspOrderInsert(CThostFtdcInputOrderField rspOrderInsert) {
		bufferQueue.enqueue(RspMsg.ofRspOrderInsert(orderInsertConverter.apply(rspOrderInsert)));
	}

	/**
	 * 报单错误回调
	 * 
	 * @param inputOrder
	 */
	void onErrRtnOrderInsert(CThostFtdcInputOrderField inputOrder) {
		bufferQueue.enqueue(RspMsg.ofErrRtnOrderInsert(inputOrder));
	}

	private RtnOrderConverter rtnOrderConverter = new RtnOrderConverter();

	/**
	 * 报单推送
	 * 
	 * @param rtnOrder
	 */
	void onRtnOrder(CThostFtdcOrderField rtnOrder) {
		log.debug("Gateway onRtnOrder -> AccountID==[{}], OrderRef==[{}]", rtnOrder.getAccountID(),
				rtnOrder.getOrderRef());
		bufferQueue.enqueue(RspMsg.ofRtnOrder(rtnOrderConverter.apply(rtnOrder)));
	}

	private RtnTradeConverter rtnTradeConverter = new RtnTradeConverter();

	/**
	 * 成交推送
	 * 
	 * @param rtnTrade
	 */
	void onRtnTrade(CThostFtdcTradeField rtnTrade) {
		log.debug("Gateway onRtnTrade -> OrderRef==[{}], Price==[{}], Volume==[{}]", rtnTrade.getOrderRef(),
				rtnTrade.getPrice(), rtnTrade.getVolume());
		bufferQueue.enqueue(RspMsg.ofRtnTrade(rtnTradeConverter.apply(rtnTrade)));
	}

	/****************
	 * 撤单接口
	 */
	public void ReqOrderAction(CThostFtdcInputOrderActionField ftdcInputOrderAction) {
		if (isTraderLogin) {
			// 设置账号信息
			ftdcInputOrderAction.setBrokerID(ctpConfigInfo.getBrokerId());
			ftdcInputOrderAction.setUserID(ctpConfigInfo.getUserId());
			ftdcInputOrderAction.setBrokerID(ctpConfigInfo.getBrokerId());
			ftdcInputOrderAction.setIPAddress(ctpConfigInfo.getReportIpAddr());
			ftdcInputOrderAction.setMacAddress(ctpConfigInfo.getReportMacAddr());
			int nRequestID = ++traderRequestId;
			ftdcTraderApi.ReqOrderAction(ftdcInputOrderAction, nRequestID);
			log.info("Send ReqOrderAction OK -> orderRef==[{}], nRequestID==[{}]", ftdcInputOrderAction.getOrderRef(),
					nRequestID);
		} else
			log.error("Trader Error :: TraderApi is not login");
	}

	private RspOrderActionConverter orderActionConverter = new RspOrderActionConverter();

	/**
	 * 撤单回调
	 * 
	 * @param inputOrderAction
	 */
	void onRspOrderAction(CThostFtdcInputOrderActionField inputOrderAction) {
		bufferQueue.enqueue(RspMsg.ofRspOrderAction(orderActionConverter.apply(inputOrderAction)));
	}

	/**
	 * 撤单错误回调
	 * 
	 * @param orderAction
	 */
	void onErrRtnOrderAction(CThostFtdcOrderActionField orderAction) {
		bufferQueue.enqueue(RspMsg.ofErrRtnOrderAction(orderAction));
	}

	/**
	 * 错误推送
	 * 
	 * @param rspInfo
	 */
	void onRspError(CThostFtdcRspInfoField rspInfo) {
		log.error("Gateway onRspError -> ErrorID==[{}], ErrorMsg==[{}]", rspInfo.getErrorID(), rspInfo.getErrorMsg());
	}

	public void ReqQryTradingAccount() {
		// ThreadUtil.startNewThread(() -> innerQureyAccount());
		CThostFtdcQryTradingAccountField qryTradingAccount = new CThostFtdcQryTradingAccountField();
		qryTradingAccount.setBrokerID(ctpConfigInfo.getBrokerId());
		qryTradingAccount.setInvestorID(ctpConfigInfo.getInvestorId());
		qryTradingAccount.setCurrencyID(ctpConfigInfo.getCurrencyId());
		int nRequestID = ++traderRequestId;
		ftdcTraderApi.ReqQryTradingAccount(qryTradingAccount, nRequestID);
		log.info("Send ReqQryTradingAccount OK -> nRequestID==[{}]", nRequestID);
	}

//	private void innerQureyAccount() {
//		ThreadUtil.sleep(1250);
//		CThostFtdcQryTradingAccountField qryTradingAccount = new CThostFtdcQryTradingAccountField();
//		qryTradingAccount.setBrokerID(ctpConfigInfo.getBrokerId());
//		qryTradingAccount.setInvestorID(ctpConfigInfo.getInvestorId());
//		qryTradingAccount.setCurrencyID(ctpConfigInfo.getCurrencyId());
//		int nRequestID = ++traderRequestId;
//		traderApi.ReqQryTradingAccount(qryTradingAccount, nRequestID);
//		log.info("Send ReqQryTradingAccount OK -> nRequestID==[{}]", nRequestID);
//	}

	void onQryTradingAccount(CThostFtdcTradingAccountField tradingAccount) {
		log.info("onQryTradingAccount -> Balance==[{}] Available==[{}] WithdrawQuota==[{}] Credit==[{}]",
				tradingAccount.getBalance(), tradingAccount.getAvailable(), tradingAccount.getWithdrawQuota(),
				tradingAccount.getCredit());
		// TODO Inbound
	}

	public void ReqQryInvestorPosition() {
		// ThreadUtil.startNewThread(() -> innerQureyPosition());
		CThostFtdcQryInvestorPositionField qryInvestorPosition = new CThostFtdcQryInvestorPositionField();
		qryInvestorPosition.setBrokerID(ctpConfigInfo.getBrokerId());
		qryInvestorPosition.setInvestorID(ctpConfigInfo.getInvestorId());
		int nRequestID = ++traderRequestId;
		ftdcTraderApi.ReqQryInvestorPosition(qryInvestorPosition, nRequestID);
		log.info("Send ReqQryInvestorPosition OK -> nRequestID==[{}]", nRequestID);
	}

//	private void innerQureyPosition() {
//		ThreadUtil.sleep(1250);
//		CThostFtdcQryInvestorPositionField qryInvestorPosition = new CThostFtdcQryInvestorPositionField();
//		qryInvestorPosition.setBrokerID(ctpConfigInfo.getBrokerId());
//		qryInvestorPosition.setInvestorID(ctpConfigInfo.getInvestorId());
//		int nRequestID = ++traderRequestId;
//		traderApi.ReqQryInvestorPosition(qryInvestorPosition, nRequestID);
//		log.info("Send ReqQryInvestorPosition OK -> nRequestID==[{}]", nRequestID);
//	}

	void onRspQryInvestorPosition(CThostFtdcInvestorPositionField investorPosition) {
		log.info("onRspQryInvestorPosition -> InstrumentID==[{}] InvestorID==[{}] Position==[{}]",
				investorPosition.getInstrumentID(), investorPosition.getInvestorID(), investorPosition.getPosition());
	}

	public void ReqQrySettlementInfo() {
		CThostFtdcQrySettlementInfoField qrySettlementInfo = new CThostFtdcQrySettlementInfoField();
		qrySettlementInfo.setBrokerID(ctpConfigInfo.getBrokerId());
		qrySettlementInfo.setInvestorID(ctpConfigInfo.getInvestorId());
		qrySettlementInfo.setTradingDay(ctpConfigInfo.getTradingDay());
		qrySettlementInfo.setAccountID(ctpConfigInfo.getAccountId());
		qrySettlementInfo.setCurrencyID(ctpConfigInfo.getCurrencyId());
		int nRequestID = ++traderRequestId;
		ftdcTraderApi.ReqQrySettlementInfo(qrySettlementInfo, nRequestID);
		log.info("Send ReqQrySettlementInfo OK -> nRequestID==[{}]", nRequestID);
	}

	public void ReqQryInstrument() {
		CThostFtdcQryInstrumentField qryInstrument = new CThostFtdcQryInstrumentField();
		int nRequestID = ++traderRequestId;
		ftdcTraderApi.ReqQryInstrument(qryInstrument, nRequestID);
		log.info("Send ReqQryInstrument OK -> nRequestID==[{}]", nRequestID);
	}

}