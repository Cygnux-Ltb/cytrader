package io.mercury.ctp.gateway.bak;

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
import ctp.thostapi.CThostFtdcOrderActionField;
import ctp.thostapi.CThostFtdcOrderField;
import ctp.thostapi.CThostFtdcQryInstrumentField;
import ctp.thostapi.CThostFtdcQryInvestorPositionField;
import ctp.thostapi.CThostFtdcQrySettlementInfoField;
import ctp.thostapi.CThostFtdcQryTradingAccountField;
import ctp.thostapi.CThostFtdcReqUserLoginField;
import ctp.thostapi.CThostFtdcRspInfoField;
import ctp.thostapi.CThostFtdcRspUserLoginField;
import ctp.thostapi.CThostFtdcSpecificInstrumentField;
import ctp.thostapi.CThostFtdcTradeField;
import ctp.thostapi.CThostFtdcTraderApi;
import ctp.thostapi.CThostFtdcTradingAccountField;
import ctp.thostapi.THOST_TE_RESUME_TYPE;
import io.mercury.common.collections.MutableSets;
import io.mercury.common.collections.queue.api.Queue;
import io.mercury.common.datetime.DateTimeUtil;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.sys.SysProperties;
import io.mercury.common.thread.ThreadUtil;
import io.mercury.common.util.Assertor;
import io.mercury.common.util.StringUtil;
import io.mercury.ctp.gateway.bean.CtpConfigInfo;
import io.mercury.ctp.gateway.bean.RspMsg;
import io.mercury.ctp.gateway.bean.rsp.RspDepthMarketData;
import io.mercury.ftdc.gateway.converter.RspOrderActionConverter;
import io.mercury.ftdc.gateway.converter.RspOrderInsertConverter;
import io.mercury.ftdc.gateway.converter.RtnOrderConverter;
import io.mercury.ftdc.gateway.converter.RtnTradeConverter;

@NotThreadSafe
public class CtpGateway0 {

	private static final Logger log = CommonLoggerFactory.getLogger(CtpGateway0.class);

	private static void copyLibraryForWin64() {
		log.info("Copy win64 library file to [java.library.path]...");
		log.info("java.library.path -> {}", SysProperties.JAVA_LIBRARY_PATH);
		// TODO
	}

	private static void copyLibraryForLinux64() {
		log.info("Copy linux64 library file to [java.library.path]......");
		log.info("java.library.path -> {}", SysProperties.JAVA_LIBRARY_PATH);
		// TODO
	}

	private synchronized static void loadCtpLibrary() {
		log.info("Loading CTP library...");
		System.loadLibrary("thostapi_wrap");
		System.loadLibrary("thosttraderapi_se");
		System.loadLibrary("thostmduserapi_se");
	}

	static {
		try {
			// 根据操作系统选择加载不同库文件
			if (SysProperties.OS_NAME.toLowerCase().startsWith("windows"))
				copyLibraryForWin64();
			else
				copyLibraryForLinux64();
			loadCtpLibrary();
			log.info("Load libs success...");
		} catch (Throwable e) {
			log.error("Load libs error...", e);
			log.error("");
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private String gatewayId;
	private CtpConfigInfo ctpConfigInfo;

	@Native
	private CThostFtdcTraderApi traderApi;
	@Native
	private CThostFtdcMdApi mdApi;

	private boolean isInit = false;
	private Queue<RspMsg> inboundQueue;

	private int mdRequestId = -1;
	private int traderRequestId = -1;

	private boolean isMdLogin;
	private boolean isTraderLogin;

	public CtpGateway0(String gatewayId, @Nonnull CtpConfigInfo ctpConfigInfo, @Nonnull Queue<RspMsg> inboundQueue) {
		this.gatewayId = gatewayId;
		this.ctpConfigInfo = Assertor.nonNull(ctpConfigInfo, "ctpConfigInfo");
		this.inboundQueue = inboundQueue;
	}

	private File getTempDir() {
		// 创建临时文件存储目录
		String tempFileHome = SysProperties.JAVA_IO_TMPDIR + File.separator + "jctp";
		File tempFileDir = new File(tempFileHome + File.separator + gatewayId + File.separator + DateTimeUtil.date());
		log.info("Temp file dir -> {}", tempFileDir.getAbsolutePath());
		if (!tempFileDir.exists())
			tempFileDir.mkdirs();
		return tempFileDir;
	}

	public void initAndJoin() {
		if (!isInit) {
			// 获取临时文件目录
			File tempDir = getTempDir();
			log.info("TraderApi version {}", CThostFtdcTraderApi.GetApiVersion());
			log.info("MdApi version {}", CThostFtdcMdApi.GetApiVersion());
			try {
				ThreadUtil.startNewThread(() -> mdInitAndJoin(tempDir), "Md-Spi-Thread");
				ThreadUtil.sleep(2000);
				ThreadUtil.startNewThread(() -> traderInitAndJoin(tempDir), "Trader-Spi-Thread");
				this.isInit = true;
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				this.isInit = false;
			}
		}
	}

	private void mdInitAndJoin(File tempDir) {
		// 指定md临时文件地址
		String mdTempFilePath = new File(tempDir, "md").getAbsolutePath();
		log.info("{} md use temp file path : {}", gatewayId, mdTempFilePath);
		// 创建mdApi
		this.mdApi = CThostFtdcMdApi.CreateFtdcMdApi(mdTempFilePath);
		// 创建mdSpi
		MdSpiImpl0 mdSpiImpl = new MdSpiImpl0(this);
		// 将mdSpi注册到mdApi
		mdApi.RegisterSpi(mdSpiImpl);
		// 注册到md前置机
		mdApi.RegisterFront(ctpConfigInfo.getMdAddr());
		// 初始化mdApi
		mdApi.Init();
		log.info("Call mdApi.Init()...");
		// 阻塞当前线程
		mdApi.Join();
		log.info("Call mdApi.Join()");
	}

	private void traderInitAndJoin(File tempDir) {
		// 指定trader临时文件地址
		String traderTempFilePath = new File(tempDir, "trader").getAbsolutePath();
		log.info("{} trader use temp file path : {}", gatewayId, traderTempFilePath);
		// 创建traderApi
		this.traderApi = CThostFtdcTraderApi.CreateFtdcTraderApi(traderTempFilePath);
		// 创建traderSpi
		TraderSpiImpl0 traderSpiImpl = new TraderSpiImpl0(this);
		// 将traderSpi注册到traderApi
		traderApi.RegisterSpi(traderSpiImpl);
		// 注册到trader前置机
		traderApi.RegisterFront(ctpConfigInfo.getTraderAddr());
		// 订阅公有流
		traderApi.SubscribePublicTopic(THOST_TE_RESUME_TYPE.THOST_TERT_QUICK);
		// 订阅私有流
		traderApi.SubscribePrivateTopic(THOST_TE_RESUME_TYPE.THOST_TERT_QUICK);
		// 初始化traderApi
		traderApi.Init();
		log.info("Call traderApi.Init()...");
		// 阻塞当前线程
		traderApi.Join();
		log.info("Call traderApi.Join()");
	}

	void onMdFrontConnected() {
		CThostFtdcReqUserLoginField reqUserLogin = new CThostFtdcReqUserLoginField();
		reqUserLogin.setBrokerID(ctpConfigInfo.getBrokerId());
		reqUserLogin.setUserID(ctpConfigInfo.getUserId());
		reqUserLogin.setPassword(ctpConfigInfo.getPassword());
		mdApi.ReqUserLogin(reqUserLogin, ++mdRequestId);
		log.info("Send Md ReqUserLogin OK");
	}

	void onMdRspUserLogin(CThostFtdcRspUserLoginField rspUserLogin) {
		log.info("Md UserLogin Success -> Brokerid==[{}] UserID==[{}]", rspUserLogin.getBrokerID(),
				rspUserLogin.getUserID());
		this.isMdLogin = true;
		innerSubscribeMarketData();
	}

	private Set<String> subscribeInstruementSet = MutableSets.newUnifiedSet();

	/**
	 * 行情相关调用和回调
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
			mdApi.SubscribeMarketData(instruementIdList, instruementIdList.length);
			subscribeInstruementSet.clear();
			log.info("Send SubscribeMarketData -> count==[{}]", instruementIdList.length);
		}
	}

	void onRspSubMarketData(CThostFtdcSpecificInstrumentField specificInstrument) {
		log.info("SubscribeMarketData Success -> InstrumentCode==[{}]", specificInstrument);
	}

	private Function<CThostFtdcDepthMarketDataField, RspDepthMarketData> depthMarketDataFunction = (
			CThostFtdcDepthMarketDataField from) -> {
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

	void onRtnDepthMarketData(CThostFtdcDepthMarketDataField depthMarketData) {
		log.debug("Gateway onRtnDepthMarketData -> InstrumentID == [{}], UpdateTime==[{}], UpdateMillisec==[{}]",
				depthMarketData.getInstrumentID(), depthMarketData.getUpdateTime(),
				depthMarketData.getUpdateMillisec());
		inboundQueue.enqueue(RspMsg.ofDepthMarketData(depthMarketDataFunction.apply(depthMarketData)));
	}

	/**
	 * 报单相关调用和回调
	 */
	public void newOrder(CThostFtdcInputOrderField inputOrder) {
		if (isTraderLogin) {
			// set account
			// TODO
			inputOrder.setAccountID(ctpConfigInfo.getAccountId());
			inputOrder.setUserID(ctpConfigInfo.getUserId());
			inputOrder.setBrokerID(ctpConfigInfo.getBrokerId());
			traderApi.ReqOrderInsert(inputOrder, ++traderRequestId);
		} else
			log.warn("TraderApi is not login, isTraderLogin==[false]");
	}

	private RspOrderInsertConverter orderInsertConverter = new RspOrderInsertConverter();

	void onRspOrderInsert(CThostFtdcInputOrderField rspOrderInsert) {
		inboundQueue.enqueue(RspMsg.ofRspOrderInsert(orderInsertConverter.apply(rspOrderInsert)));
	}

	void onErrRtnOrderInsert(CThostFtdcInputOrderField inputOrder) {
		inboundQueue.enqueue(RspMsg.ofErrRtnOrderInsert(inputOrder));
	}

	private RtnOrderConverter rtnOrderConverter = new RtnOrderConverter();

	void onRtnOrder(CThostFtdcOrderField rtnOrder) {
		log.debug("Gateway onRtnOrder -> AccountID==[{}], OrderRef==[{}]", rtnOrder.getAccountID(),
				rtnOrder.getOrderRef());
		inboundQueue.enqueue(RspMsg.ofRtnOrder(rtnOrderConverter.apply(rtnOrder)));
	}

	private RtnTradeConverter rtnTradeConverter = new RtnTradeConverter();

	void onRtnTrade(CThostFtdcTradeField rtnTrade) {
		log.debug("Gateway onRtnTrade -> OrderRef==[{}], Price==[{}], Volume==[{}]", rtnTrade.getOrderRef(),
				rtnTrade.getPrice(), rtnTrade.getVolume());
		inboundQueue.enqueue(RspMsg.ofRtnTrade(rtnTradeConverter.apply(rtnTrade)));
	}

	/**
	 * 撤单相关调用和回调
	 */
	public void cancelOrder(CThostFtdcInputOrderActionField inputOrderAction) {
		if (isTraderLogin) {
			inputOrderAction.setBrokerID(ctpConfigInfo.getBrokerId());
			inputOrderAction.setUserID(ctpConfigInfo.getUserId());
			inputOrderAction.setBrokerID(ctpConfigInfo.getBrokerId());
			traderApi.ReqOrderAction(inputOrderAction, ++traderRequestId);
		} else
			log.warn("TraderApi is not login, isTraderLogin==[false]");
	}

	private RspOrderActionConverter orderActionConverter = new RspOrderActionConverter();

	void onRspOrderAction(CThostFtdcInputOrderActionField inputOrderAction) {
		inboundQueue.enqueue(RspMsg.ofRspOrderAction(orderActionConverter.apply(inputOrderAction)));
	}

	void onErrRtnOrderAction(CThostFtdcOrderActionField orderAction) {
		inboundQueue.enqueue(RspMsg.ofErrRtnOrderAction(orderAction));
	}

	void onRspError(CThostFtdcRspInfoField rspInfo) {
		log.error("Gateway onRspError -> ErrorID==[{}], ErrorMsg==[{}]", rspInfo.getErrorID(),
				StringUtil.conversionGbkToUtf8(rspInfo.getErrorMsg()));
	}

	void onTraderFrontConnected() {
		CThostFtdcReqUserLoginField reqUserLogin = new CThostFtdcReqUserLoginField();
		reqUserLogin.setBrokerID(ctpConfigInfo.getBrokerId());
		reqUserLogin.setUserID(ctpConfigInfo.getUserId());
		reqUserLogin.setPassword(ctpConfigInfo.getPassword());
		reqUserLogin.setUserProductInfo(ctpConfigInfo.getUserProductInfo());
		traderApi.ReqUserLogin(reqUserLogin, ++traderRequestId);
		log.info("Send Trader ReqUserLogin OK");
	}

	void onTraderRspUserLogin(CThostFtdcRspUserLoginField rspUserLogin) {
		log.info("Trader UserLogin Success -> Brokerid==[{}] UserID==[{}]", rspUserLogin.getBrokerID(),
				rspUserLogin.getUserID());
		this.isTraderLogin = true;
		qureyAccount();
	}

	public void qureyAccount() {
		ThreadUtil.startNewThread(() -> innerQureyAccount());
	}

	private void innerQureyAccount() {
		ThreadUtil.sleep(1250);
		CThostFtdcQryTradingAccountField qryTradingAccount = new CThostFtdcQryTradingAccountField();
		qryTradingAccount.setBrokerID(ctpConfigInfo.getBrokerId());
		qryTradingAccount.setInvestorID(ctpConfigInfo.getInvestorId());
		qryTradingAccount.setCurrencyID(ctpConfigInfo.getCurrencyId());
		int nRequestID = ++traderRequestId;
		traderApi.ReqQryTradingAccount(qryTradingAccount, nRequestID);
		log.info("Send ReqQryTradingAccount OK -> nRequestID==[{}]", nRequestID);
	}

	void onQryTradingAccount(CThostFtdcTradingAccountField tradingAccount) {
		log.info("onQryTradingAccount -> Balance==[{}] Available==[{}] WithdrawQuota==[{}] Credit==[{}]",
				tradingAccount.getBalance(), tradingAccount.getAvailable(), tradingAccount.getWithdrawQuota(),
				tradingAccount.getCredit());
		qureyPosition();
	}

	public void qureyPosition() {
		ThreadUtil.startNewThread(() -> innerQureyPosition());
	}

	private void innerQureyPosition() {
		ThreadUtil.sleep(1250);
		CThostFtdcQryInvestorPositionField qryInvestorPosition = new CThostFtdcQryInvestorPositionField();
		qryInvestorPosition.setBrokerID(ctpConfigInfo.getBrokerId());
		qryInvestorPosition.setInvestorID(ctpConfigInfo.getInvestorId());
		int nRequestID = ++traderRequestId;
		traderApi.ReqQryInvestorPosition(qryInvestorPosition, nRequestID);
		log.info("Send ReqQryInvestorPosition OK -> nRequestID==[{}]", nRequestID);
	}

	void onRspQryInvestorPosition(CThostFtdcInvestorPositionField investorPosition) {
		log.info("onRspQryInvestorPosition -> InstrumentID==[{}] InvestorID==[{}] Position==[{}]",
				investorPosition.getInstrumentID(), investorPosition.getInvestorID(), investorPosition.getPosition());
	}

	public void qureySettlementInfo() {
		CThostFtdcQrySettlementInfoField qrySettlementInfo = new CThostFtdcQrySettlementInfoField();
		qrySettlementInfo.setBrokerID(ctpConfigInfo.getBrokerId());
		qrySettlementInfo.setInvestorID(ctpConfigInfo.getInvestorId());
		qrySettlementInfo.setTradingDay(ctpConfigInfo.getTradingDay());
		qrySettlementInfo.setAccountID(ctpConfigInfo.getAccountId());
		qrySettlementInfo.setCurrencyID(ctpConfigInfo.getCurrencyId());
		int nRequestID = ++traderRequestId;
		traderApi.ReqQrySettlementInfo(qrySettlementInfo, nRequestID);
		log.info("Send ReqQrySettlementInfo OK -> nRequestID==[{}]", nRequestID);
	}

	public void qureyInstrument() {
		CThostFtdcQryInstrumentField qryInstrument = new CThostFtdcQryInstrumentField();
		traderApi.ReqQryInstrument(qryInstrument, ++traderRequestId);
		log.info("Send ReqQryInstrument OK");
	}

}