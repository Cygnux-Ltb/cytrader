package io.mercury.gateway.ctp;

import java.io.File;
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
import ctp.thostapi.CThostFtdcReqUserLoginField;
import ctp.thostapi.CThostFtdcRspInfoField;
import ctp.thostapi.CThostFtdcRspUserLoginField;
import ctp.thostapi.CThostFtdcSpecificInstrumentField;
import ctp.thostapi.CThostFtdcTradeField;
import ctp.thostapi.CThostFtdcTraderApi;
import ctp.thostapi.CThostFtdcTraderSpi;
import ctp.thostapi.CThostFtdcTradingAccountField;
import ctp.thostapi.THOST_TE_RESUME_TYPE;
import io.mercury.common.annotation.lang.JNI;
import io.mercury.common.collections.MutableSets;
import io.mercury.common.collections.queue.api.Queue;
import io.mercury.common.datetime.DateTimeUtil;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.sys.SysProperties;
import io.mercury.common.thread.ThreadUtil;
import io.mercury.common.util.Assertor;
import io.mercury.common.util.StringUtil;
import io.mercury.gateway.ctp.bean.config.CtpConnectionInfo;
import io.mercury.gateway.ctp.bean.rsp.RspDepthMarketData;
import io.mercury.gateway.ctp.bean.rsp.RspMsg;
import io.mercury.gateway.ctp.converter.RspOrderActionConverter;
import io.mercury.gateway.ctp.converter.RspOrderInsertConverter;
import io.mercury.gateway.ctp.converter.RtnOrderConverter;
import io.mercury.gateway.ctp.converter.RtnTradeConverter;

@NotThreadSafe
public class CtpGateway {

	private static final Logger logger = CommonLoggerFactory.getLogger(CtpGateway.class);

	private static void copyLibraryForWin64() {
		logger.info("Copy win64 library file to [java.library.path]...");
		logger.info("java.library.path -> {}", SysProperties.JAVA_LIBRARY_PATH);
		// TODO 复制到相应目录
	}

	private static void copyLibraryForLinux64() {
		logger.info("Copy linux64 library file to [java.library.path]......");
		logger.info("java.library.path -> {}", SysProperties.JAVA_LIBRARY_PATH);
		// TODO 复制到相应目录
	}

	private synchronized static void loadCtpLibrary() {
		logger.info("Loading CTP library...");
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
			logger.info("Load libs success...");
		} catch (Throwable e) {
			logger.error("Load libs error...", e);
			logger.error("");
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private String gatewayId;
	private CtpConnectionInfo connectionInfo;

	@JNI
	private CThostFtdcTraderApi traderApi;
	@JNI
	private CThostFtdcMdApi mdApi;

	private boolean isInit = false;
	private Queue<RspMsg> inboundBuffer;

	private int mdRequestId = -1;
	private int traderRequestId = -1;

	private boolean isMdLogin;
	private boolean isTraderLogin;

	public CtpGateway(String gatewayId, @Nonnull CtpConnectionInfo connectionInfo,
			@Nonnull Queue<RspMsg> inboundBuffer) {
		this.gatewayId = gatewayId;
		this.connectionInfo = Assertor.nonNull(connectionInfo, "userInfo");
		this.inboundBuffer = Assertor.nonNull(inboundBuffer, "inboundBuffer");
	}

	private File getTempDir() {
		// 创建临时文件存储目录
		String tempFileHome = SysProperties.JAVA_IO_TMPDIR + File.separator + "jctp";
		File tempFileDir = new File(tempFileHome + File.separator + gatewayId + File.separator + DateTimeUtil.date());
		logger.info("Temp file dir -> {}", tempFileDir.getAbsolutePath());
		if (!tempFileDir.exists())
			tempFileDir.mkdirs();
		return tempFileDir;
	}

	public void initAndJoin() {
		if (!isInit) {
			// 获取临时文件目录
			File tempDir = getTempDir();
			logger.info("TraderApi version {}", CThostFtdcTraderApi.GetApiVersion());
			logger.info("MdApi version {}", CThostFtdcMdApi.GetApiVersion());
			try {
				ThreadUtil.startNewThread(() -> mdInitAndJoin(tempDir), "Md-Spi-Thread");
				ThreadUtil.sleep(2000);
				ThreadUtil.startNewThread(() -> traderInitAndJoin(tempDir), "Trader-Spi-Thread");
				this.isInit = true;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				this.isInit = false;
			}
		}
	}

	private void mdInitAndJoin(File tempDir) {
		// 指定md临时文件地址
		String mdTempFilePath = new File(tempDir, "md").getAbsolutePath();
		logger.info("{} md use temp file path : {}", gatewayId, mdTempFilePath);
		// 创建mdApi
		this.mdApi = CThostFtdcMdApi.CreateFtdcMdApi(mdTempFilePath);
		// 创建mdSpi
		CThostFtdcMdSpi mdSpi = new MdSpi(this);
		// 将mdSpi注册到mdApi
		mdApi.RegisterSpi(mdSpi);
		// 注册到md前置机
		mdApi.RegisterFront(connectionInfo.getMdAddress());
		// 初始化mdApi
		mdApi.Init();
		logger.info("Call mdApi.Init()...");
		// 阻塞当前线程
		mdApi.Join();
		logger.info("Call mdApi.Join()");
	}

	private void traderInitAndJoin(File tempDir) {
		// 指定trader临时文件地址
		String traderTempFilePath = new File(tempDir, "trader").getAbsolutePath();
		logger.info("{} trader use temp file path : {}", gatewayId, traderTempFilePath);
		// 创建traderApi
		this.traderApi = CThostFtdcTraderApi.CreateFtdcTraderApi(traderTempFilePath);
		// 创建traderSpi
		CThostFtdcTraderSpi traderSpi = new TraderSpi(this);
		// 将traderSpi注册到traderApi
		traderApi.RegisterSpi(traderSpi);
		// 注册到trader前置机
		traderApi.RegisterFront(connectionInfo.getTraderAddress());
		// 订阅公有流
		traderApi.SubscribePublicTopic(THOST_TE_RESUME_TYPE.THOST_TERT_QUICK);
		// 订阅私有流
		traderApi.SubscribePrivateTopic(THOST_TE_RESUME_TYPE.THOST_TERT_QUICK);
		// 初始化traderApi
		traderApi.Init();
		logger.info("Call traderApi.Init()...");
		// 阻塞当前线程
		traderApi.Join();
		logger.info("Call traderApi.Join()");
	}

	void onMdFrontConnected() {
		CThostFtdcReqUserLoginField reqUserLogin = new CThostFtdcReqUserLoginField();
		reqUserLogin.setBrokerID(connectionInfo.getBrokerId());
		reqUserLogin.setUserID(connectionInfo.getUserId());
		reqUserLogin.setPassword(connectionInfo.getPassword());
		mdApi.ReqUserLogin(reqUserLogin, ++mdRequestId);
		logger.info("Send Md ReqUserLogin OK");
	}

	void onMdRspUserLogin(CThostFtdcRspUserLoginField rspUserLogin) {
		logger.info("Md UserLogin Success -> Brokerid==[{}] UserID==[{}]", rspUserLogin.getBrokerID(),
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
		logger.info("Add Subscribe Instruement set -> Count==[{}]", inputInstruementSet.size());
		if (isMdLogin && !subscribeInstruementSet.isEmpty())
			innerSubscribeMarketData();
		else
			logger.info("Cannot SubscribeMarketData -> isMdLogin==[false]");
	}

	private void innerSubscribeMarketData() {
		if (!subscribeInstruementSet.isEmpty()) {
			String[] instruementIdList = new String[subscribeInstruementSet.size()];
			Iterator<String> iterator = subscribeInstruementSet.iterator();
			for (int i = 0; i < instruementIdList.length; i++) {
				instruementIdList[i] = iterator.next();
				logger.info("Add Subscribe Instruement -> instruementCode==[{}]", instruementIdList[i]);
			}
			mdApi.SubscribeMarketData(instruementIdList, instruementIdList.length);
			subscribeInstruementSet.clear();
			logger.info("Send SubscribeMarketData -> count==[{}]", instruementIdList.length);
		}
	}

	void onRspSubMarketData(CThostFtdcSpecificInstrumentField specificInstrument) {
		logger.info("SubscribeMarketData Success -> InstrumentCode==[{}]", specificInstrument);
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

	void onRtnDepthMarketData(CThostFtdcDepthMarketDataField depthMarketData) {
		logger.debug("Gateway onRtnDepthMarketData -> InstrumentID == [{}], UpdateTime==[{}], UpdateMillisec==[{}]",
				depthMarketData.getInstrumentID(), depthMarketData.getUpdateTime(),
				depthMarketData.getUpdateMillisec());
		inboundBuffer.enqueue(RspMsg.ofDepthMarketData(depthMarketDataFunction.apply(depthMarketData)));
	}

	/**
	 * 报单相关调用和回调
	 */
	public void newOrder(CThostFtdcInputOrderField inputOrder) {
		if (isTraderLogin) {
			// set account
			// TODO
			inputOrder.setAccountID(connectionInfo.getAccountId());
			inputOrder.setUserID(connectionInfo.getUserId());
			inputOrder.setBrokerID(connectionInfo.getBrokerId());
			traderApi.ReqOrderInsert(inputOrder, ++traderRequestId);
		} else
			logger.warn("TraderApi is not login, isTraderLogin==[false]");
	}

	private RspOrderInsertConverter orderInsertConverter = new RspOrderInsertConverter();

	void onRspOrderInsert(CThostFtdcInputOrderField rspOrderInsert) {
		inboundBuffer.enqueue(RspMsg.ofRspOrderInsert(orderInsertConverter.apply(rspOrderInsert)));
	}

	void onErrRtnOrderInsert(CThostFtdcInputOrderField inputOrder) {
		inboundBuffer.enqueue(RspMsg.ofErrRtnOrderInsert(inputOrder));
	}

	private RtnOrderConverter rtnOrderConverter = new RtnOrderConverter();

	void onRtnOrder(CThostFtdcOrderField rtnOrder) {
		logger.debug("Gateway onRtnOrder -> AccountID==[{}], OrderRef==[{}]", rtnOrder.getAccountID(),
				rtnOrder.getOrderRef());
		inboundBuffer.enqueue(RspMsg.ofRtnOrder(rtnOrderConverter.apply(rtnOrder)));
	}

	private RtnTradeConverter rtnTradeConverter = new RtnTradeConverter();

	void onRtnTrade(CThostFtdcTradeField rtnTrade) {
		logger.debug("Gateway onRtnTrade -> OrderRef==[{}], Price==[{}], Volume==[{}]", rtnTrade.getOrderRef(),
				rtnTrade.getPrice(), rtnTrade.getVolume());
		inboundBuffer.enqueue(RspMsg.ofRtnTrade(rtnTradeConverter.apply(rtnTrade)));
	}

	/**
	 * 撤单相关调用和回调
	 */
	public void cancelOrder(CThostFtdcInputOrderActionField inputOrderAction) {
		if (isTraderLogin) {
			inputOrderAction.setBrokerID(connectionInfo.getBrokerId());
			inputOrderAction.setUserID(connectionInfo.getUserId());
			inputOrderAction.setBrokerID(connectionInfo.getBrokerId());
			traderApi.ReqOrderAction(inputOrderAction, ++traderRequestId);
		} else
			logger.error("Trader Error :: TraderApi is not login");
	}

	private RspOrderActionConverter orderActionConverter = new RspOrderActionConverter();

	void onRspOrderAction(CThostFtdcInputOrderActionField inputOrderAction) {
		inboundBuffer.enqueue(RspMsg.ofRspOrderAction(orderActionConverter.apply(inputOrderAction)));
	}

	void onErrRtnOrderAction(CThostFtdcOrderActionField orderAction) {
		inboundBuffer.enqueue(RspMsg.ofErrRtnOrderAction(orderAction));
	}

	void onRspError(CThostFtdcRspInfoField rspInfo) {
		logger.error("Gateway onRspError -> ErrorID==[{}], ErrorMsg==[{}]", rspInfo.getErrorID(),
				StringUtil.conversionGbkToUtf8(rspInfo.getErrorMsg()));
	}

	void onTraderFrontConnected() {
		CThostFtdcReqUserLoginField reqUserLogin = new CThostFtdcReqUserLoginField();
		reqUserLogin.setBrokerID(connectionInfo.getBrokerId());
		reqUserLogin.setUserID(connectionInfo.getUserId());
		reqUserLogin.setPassword(connectionInfo.getPassword());
		reqUserLogin.setUserProductInfo(connectionInfo.getUserProductInfo());
		traderApi.ReqUserLogin(reqUserLogin, ++traderRequestId);
		logger.info("Send Trader ReqUserLogin OK");
	}

	void onTraderRspUserLogin(CThostFtdcRspUserLoginField rspUserLogin) {
		logger.info("Trader UserLogin Success -> Brokerid==[{}] UserID==[{}]", rspUserLogin.getBrokerID(),
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
		qryTradingAccount.setBrokerID(connectionInfo.getBrokerId());
		qryTradingAccount.setInvestorID(connectionInfo.getInvestorId());
		qryTradingAccount.setCurrencyID(connectionInfo.getCurrencyId());
		int nRequestID = ++traderRequestId;
		traderApi.ReqQryTradingAccount(qryTradingAccount, nRequestID);
		logger.info("Send ReqQryTradingAccount OK -> nRequestID==[{}]", nRequestID);
	}

	void onQryTradingAccount(CThostFtdcTradingAccountField tradingAccount) {
		logger.info("onQryTradingAccount -> Balance==[{}] Available==[{}] WithdrawQuota==[{}] Credit==[{}]",
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
		qryInvestorPosition.setBrokerID(connectionInfo.getBrokerId());
		qryInvestorPosition.setInvestorID(connectionInfo.getInvestorId());
		int nRequestID = ++traderRequestId;
		traderApi.ReqQryInvestorPosition(qryInvestorPosition, nRequestID);
		logger.info("Send ReqQryInvestorPosition OK -> nRequestID==[{}]", nRequestID);
	}

	void onRspQryInvestorPosition(CThostFtdcInvestorPositionField investorPosition) {
		logger.info("onRspQryInvestorPosition -> InstrumentID==[{}] InvestorID==[{}] Position==[{}]",
				investorPosition.getInstrumentID(), investorPosition.getInvestorID(), investorPosition.getPosition());
	}

	public void qureySettlementInfo() {
		CThostFtdcQrySettlementInfoField qrySettlementInfo = new CThostFtdcQrySettlementInfoField();
		qrySettlementInfo.setBrokerID(connectionInfo.getBrokerId());
		qrySettlementInfo.setInvestorID(connectionInfo.getInvestorId());
		qrySettlementInfo.setTradingDay(connectionInfo.getTradingDay());
		qrySettlementInfo.setAccountID(connectionInfo.getAccountId());
		qrySettlementInfo.setCurrencyID(connectionInfo.getCurrencyId());
		int nRequestID = ++traderRequestId;
		traderApi.ReqQrySettlementInfo(qrySettlementInfo, nRequestID);
		logger.info("Send ReqQrySettlementInfo OK -> nRequestID==[{}]", nRequestID);
	}

	public void qureyInstrument() {
		CThostFtdcQryInstrumentField qryInstrument = new CThostFtdcQryInstrumentField();
		traderApi.ReqQryInstrument(qryInstrument, ++traderRequestId);
		logger.info("Send ReqQryInstrument OK");
	}

}