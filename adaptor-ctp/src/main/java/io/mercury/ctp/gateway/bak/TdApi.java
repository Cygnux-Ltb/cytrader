package io.mercury.ctp.gateway.bak;

import java.io.File;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;

import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import ctp.thostapi.CThostFtdcQryInvestorPositionField;
import ctp.thostapi.CThostFtdcQryTradingAccountField;
import ctp.thostapi.CThostFtdcReqAuthenticateField;
import ctp.thostapi.CThostFtdcReqUserLoginField;
import ctp.thostapi.CThostFtdcTraderApi;
import ctp.thostapi.thosttraderapiConstants;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.thread.ThreadUtil;
import io.mercury.common.util.StringUtil;
import io.mercury.ctp.gateway.base.Constant;
import io.mercury.ctp.gateway.base.CtpConstant;
import io.mercury.ctp.gateway.bean.req.ReqCancelOrder;
import io.mercury.ctp.gateway.bean.req.ReqOrder;

@Deprecated
@SuppressWarnings("unused")
public class TdApi {

	private Logger log = CommonLoggerFactory.getLogger(getClass());

	private volatile CThostFtdcTraderApi cThostFtdcTraderApi;

	private volatile boolean isConnecting = false; // 是否正在连接中
	private volatile boolean isConnected = false; // 连接状态

	private boolean isLogin = false; // 登陆状态
	private boolean isLoginFailed = false; // 是否已经使用错误的信息尝试登录过
	private boolean isAuth = false; // 验证状态

	private String tdAddress;
	private String brokerId;
	private String userId;
	private String password;
	private String authCode;
	private String userProductInfo;
	private String gatewayId;

	private TdSpi tdSpi;

	private AtomicInteger reqId = new AtomicInteger(0); // 操作请求编号
	private AtomicInteger orderRef = new AtomicInteger(0); // 订单编号

	public TdApi(String gatewayId, TdSpi tdSpi, CtpConfig config) {
		this.gatewayId = gatewayId;
		this.tdSpi = tdSpi;
		this.tdAddress = config.getTdAddress();
		this.brokerId = config.getBrokerId();
		this.userId = config.getUserId();
		this.password = config.getPassword();
		this.authCode = config.getAuthCode();
	}

	/**
	 * 连接
	 */
	synchronized void connect() {
		if (isConnected() || isConnecting)
			return;
		if (cThostFtdcTraderApi != null) {
			cThostFtdcTraderApi.RegisterSpi(null);
			// 由于CTP底层原因，部分情况下不能正确执行Release
			ThreadUtil.startNewThread(() -> apiRelease(), gatewayId + "-TdApiReleaseThread-" + LocalDateTime.now());
			ThreadUtil.sleep(2000);
			isConnecting = false;
			isConnected = false;
			isLogin = false;
		}
		log.info("{} TdApi instance init...", gatewayId);
		String ctpTempFileHome = System.getProperty("user.home") + File.separator + "jctp";
		File ctpTempFilePath = new File(ctpTempFileHome + File.separator + "id_" + gatewayId + File.separator + "td");
		if (!ctpTempFilePath.exists())
			ctpTempFilePath.mkdirs();
		File tempFile = new File(ctpTempFilePath, "td");
		log.info("{} td use temp file path : {}", gatewayId, tempFile.getParentFile().getAbsolutePath());
		cThostFtdcTraderApi = CThostFtdcTraderApi.CreateFtdcTraderApi(tempFile.getAbsolutePath());
		cThostFtdcTraderApi.RegisterSpi(tdSpi);
		cThostFtdcTraderApi.RegisterFront(tdAddress);
		isConnecting = true;
		cThostFtdcTraderApi.Init();
	}

	/**
	 * 关闭
	 */
	synchronized void close() {
		if (cThostFtdcTraderApi != null) {
			log.info("{} TdApi close and release start.", gatewayId);
			cThostFtdcTraderApi.RegisterSpi(null);
			// 避免异步线程找不到引用
			// CThostFtdcTraderApi cThostFtdcTraderApiForRelease = cThostFtdcTraderApi;
			// 由于CTP底层原因，部分情况下不能正确执行Release
			ThreadUtil.startNewThread(() -> apiRelease(), gatewayId + "-TdApiReleaseThread-" + LocalDateTime.now());
			ThreadUtil.sleep(2000);
			cThostFtdcTraderApi = null;
			isConnecting = false;
			isConnected = false;
			isLogin = false;
			log.info("{} TdApi instance close and release end.", gatewayId);
			// 通知停止其他关联实例
		} else
			log.warn("{} TdApi instance is null.", gatewayId);
	}

	private void apiRelease() {
		try {
			log.info("{} TdApi release thread start...", gatewayId);
			cThostFtdcTraderApi.Release();
		} catch (Exception e) {
			log.error("{} TdApi release thread error...", gatewayId, e);
		}
	}

	void authenticate() {
		if (!StringUtil.nonEmpty(authCode) && !isAuth) {
			// 验证
			CThostFtdcReqAuthenticateField authenticateField = new CThostFtdcReqAuthenticateField();
			authenticateField.setUserID(userId);
			authenticateField.setBrokerID(brokerId);
			authenticateField.setAuthCode(authCode);
			authenticateField.setUserProductInfo(userProductInfo);
			cThostFtdcTraderApi.ReqAuthenticate(authenticateField, reqId.incrementAndGet());
		}
	}

	void login() {
		if (isLoginFailed) {
			log.warn("{} can not login, isLoginFailed == true", gatewayId);
			return;
		}
		if (cThostFtdcTraderApi == null) {
			log.warn("{} is not init", gatewayId);
			return;
		}
		if (StringUtil.isNullOrEmpty(brokerId)) {
			log.error("{} can not login, brokerId is null or empty");
			return;
		}
		if (StringUtil.isNullOrEmpty(userId)) {
			log.error("{} can not login, userId is null or empty");
			return;
		}
		if (StringUtil.isNullOrEmpty(password)) {
			log.error("{} can not login, password is null or empty");
			return;
		}
		if (StringUtil.isNullOrEmpty(authCode) && isAuth) {
			CThostFtdcReqUserLoginField userLoginField = new CThostFtdcReqUserLoginField();
			userLoginField.setBrokerID(brokerId);
			userLoginField.setUserID(userId);
			userLoginField.setPassword(password);
			cThostFtdcTraderApi.ReqUserLogin(userLoginField, 0);
		}
	}

	void queryAccount() {
		if (cThostFtdcTraderApi == null) {
			log.info("{} TdApi not init, cThostFtdcTraderApi == null", gatewayId);
			return;
		}
		if (!isLogin) {
			log.info("{} TdApi not login, isLogin == false", gatewayId);
			return;
		}
		CThostFtdcQryTradingAccountField cThostFtdcQryTradingAccountField = new CThostFtdcQryTradingAccountField();
		cThostFtdcTraderApi.ReqQryTradingAccount(cThostFtdcQryTradingAccountField, reqId.incrementAndGet());
	}

	void queryPosition() {
		if (cThostFtdcTraderApi == null) {
			log.info("{} TdApi is not init, cThostFtdcTraderApi == null", gatewayId);
			return;
		}
		if (!isLogin) {
			log.info("{} TdApi not login, isLogin == false", gatewayId);
			return;
		}
		CThostFtdcQryInvestorPositionField cThostFtdcQryInvestorPositionField = new CThostFtdcQryInvestorPositionField();
		cThostFtdcQryInvestorPositionField.setBrokerID(brokerId);
		cThostFtdcQryInvestorPositionField.setInvestorID(userId);
		cThostFtdcTraderApi.ReqQryInvestorPosition(cThostFtdcQryInvestorPositionField, reqId.incrementAndGet());
	}

	/**
	 * 发单
	 * 
	 * @param orderReq
	 * @return
	 */
	void sendOrder(ReqOrder orderReq) {
		if (cThostFtdcTraderApi == null) {
			log.info("{}尚未初始化,无法发单", gatewayId);
			return;
		}
		CThostFtdcInputOrderField cThostFtdcInputOrderField = new CThostFtdcInputOrderField();
		orderRef.incrementAndGet();
		cThostFtdcInputOrderField.setInstrumentID(orderReq.getSymbol());
		cThostFtdcInputOrderField.setLimitPrice(orderReq.getPrice());
		cThostFtdcInputOrderField.setVolumeTotalOriginal(orderReq.getVolume());

		cThostFtdcInputOrderField.setOrderPriceType(
				CtpConstant.priceTypeMap.getOrDefault(orderReq.getPriceType(), Character.valueOf('\0')));
		cThostFtdcInputOrderField
				.setDirection(CtpConstant.directionMap.getOrDefault(orderReq.getDirection(), Character.valueOf('\0')));
		cThostFtdcInputOrderField.setCombOffsetFlag(
				String.valueOf(CtpConstant.offsetMap.getOrDefault(orderReq.getOffset(), Character.valueOf('\0'))));
		cThostFtdcInputOrderField.setOrderRef(orderRef.get() + "");
		cThostFtdcInputOrderField.setInvestorID(userId);
		cThostFtdcInputOrderField.setUserID(userId);
		cThostFtdcInputOrderField.setBrokerID(brokerId);

		cThostFtdcInputOrderField.setCombHedgeFlag(String.valueOf(thosttraderapiConstants.THOST_FTDC_HF_Speculation));
		cThostFtdcInputOrderField.setContingentCondition(thosttraderapiConstants.THOST_FTDC_CC_Immediately);
		cThostFtdcInputOrderField.setForceCloseReason(thosttraderapiConstants.THOST_FTDC_FCC_NotForceClose);
		cThostFtdcInputOrderField.setIsAutoSuspend(0);
		cThostFtdcInputOrderField.setTimeCondition(thosttraderapiConstants.THOST_FTDC_TC_GFD);
		cThostFtdcInputOrderField.setVolumeCondition(thosttraderapiConstants.THOST_FTDC_VC_AV);
		cThostFtdcInputOrderField.setMinVolume(1);

		// 判断FAK FOK市价单
		if (Constant.PRICETYPE_FAK.equals(orderReq.getPriceType())) {
			cThostFtdcInputOrderField.setOrderPriceType(thosttraderapiConstants.THOST_FTDC_OPT_LimitPrice);
			cThostFtdcInputOrderField.setTimeCondition(thosttraderapiConstants.THOST_FTDC_TC_IOC);
			cThostFtdcInputOrderField.setVolumeCondition(thosttraderapiConstants.THOST_FTDC_VC_AV);
		} else if (Constant.PRICETYPE_FOK.equals(orderReq.getPriceType())) {
			cThostFtdcInputOrderField.setOrderPriceType(thosttraderapiConstants.THOST_FTDC_OPT_LimitPrice);
			cThostFtdcInputOrderField.setTimeCondition(thosttraderapiConstants.THOST_FTDC_TC_IOC);
			cThostFtdcInputOrderField.setVolumeCondition(thosttraderapiConstants.THOST_FTDC_VC_CV);
		}

		cThostFtdcTraderApi.ReqOrderInsert(cThostFtdcInputOrderField, reqId.incrementAndGet());
		String rtOrderID = gatewayId + "." + orderRef.get();

		if (StringUtil.isNullOrEmpty(orderReq.getOriginalOrderID())) {
			// originalOrderIdMap.put(rtOrderID, orderReq.getOriginalOrderID());
		}

		// return rtOrderID;
	}

	// 撤单
	void cancelOrder(ReqCancelOrder reqCancelOrder) {
		if (cThostFtdcTraderApi == null) {
			log.info("{}尚未初始化,无法撤单", gatewayId);
			return;
		}
		CThostFtdcInputOrderActionField cThostFtdcInputOrderActionField = new CThostFtdcInputOrderActionField();

		cThostFtdcInputOrderActionField.setInstrumentID(reqCancelOrder.getSymbol());
		cThostFtdcInputOrderActionField.setExchangeID(reqCancelOrder.getExchange());
		cThostFtdcInputOrderActionField.setOrderRef(reqCancelOrder.getOrderID());
		cThostFtdcInputOrderActionField.setFrontID(reqCancelOrder.getFrontID());
		cThostFtdcInputOrderActionField.setSessionID(reqCancelOrder.getSessionID());

		cThostFtdcInputOrderActionField.setActionFlag(thosttraderapiConstants.THOST_FTDC_AF_Delete);
		cThostFtdcInputOrderActionField.setBrokerID(brokerId);
		cThostFtdcInputOrderActionField.setInvestorID(userId);

		cThostFtdcTraderApi.ReqOrderAction(cThostFtdcInputOrderActionField, reqId.incrementAndGet());
	}

	/**
	 * 返回接口状态
	 * 
	 * @return
	 */
	boolean isConnected() {
		return isConnected;
	}

	void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

	boolean isLogin() {
		return isLogin;
	}

	void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	void setLoginFailed(boolean isLoginFailed) {
		this.isLoginFailed = isLoginFailed;
	}

	void setAuth(boolean isAuth) {
		this.isAuth = isAuth;
	}

	void join() {
		cThostFtdcTraderApi.Join();
	}

}
