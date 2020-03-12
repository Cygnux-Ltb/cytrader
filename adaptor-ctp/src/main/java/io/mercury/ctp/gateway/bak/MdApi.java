package io.mercury.gateway.ctp.bak;

import java.io.File;
import java.time.LocalDateTime;

import org.slf4j.Logger;

import ctp.thostapi.CThostFtdcMdApi;
import ctp.thostapi.CThostFtdcReqUserLoginField;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.thread.ThreadUtil;
import io.mercury.common.util.StringUtil;

@Deprecated
public class MdApi {

	private Logger log = CommonLoggerFactory.getLogger(getClass());

	private volatile CThostFtdcMdApi cThostFtdcMdApi;
	private volatile boolean isConnecting = false; // 是否正在连接中
	private volatile boolean isConnected = false; // 连接状态

	private boolean isLogin = false; // 登陆状态

	private MdSpi mdSpi;
	private String gatewayId;

	private String mdAddress;
	private String brokerId;
	private String userId;
	private String password;

	MdApi(String gatewayId, MdSpi mdSpi, CtpConfig config) {
		this.mdSpi = mdSpi;
		this.gatewayId = gatewayId;
		this.mdAddress = config.getMdAddress();
		this.brokerId = config.getBrokerId();
		this.userId = config.getUserId();
		this.password = config.getPassword();
	}

	/**
	 * 连接
	 */
	synchronized void connect() {
		if (isConnected() || isConnecting)
			return;
		if (cThostFtdcMdApi != null) {
			cThostFtdcMdApi.RegisterSpi(null);
			// 由于CTP底层原因，部分情况下不能正确执行Release
			ThreadUtil.startNewThread(() -> apiRelease(), gatewayId + "-MdApiReleaseThread" + LocalDateTime.now());
			ThreadUtil.sleep(2000);
			isConnecting = false;
			isConnected = false;
			isLogin = false;
		}
		log.info("{} MdApi instance init...", gatewayId);
		String ctpTempFileHome = System.getProperty("user.home") + File.separator + "jctp";
		File ctpTempFilePath = new File(ctpTempFileHome + File.separator + "id_" + gatewayId + File.separator + "md");
		if (!ctpTempFilePath.exists())
			ctpTempFilePath.mkdirs();
		File tempFile = new File(ctpTempFilePath, "md");
		log.info("{} md use temp file path : {}", gatewayId, tempFile.getParentFile().getAbsolutePath());
		cThostFtdcMdApi = CThostFtdcMdApi.CreateFtdcMdApi(tempFile.getAbsolutePath());
		cThostFtdcMdApi.RegisterSpi(mdSpi);
		cThostFtdcMdApi.RegisterFront(mdAddress);
		isConnecting = true;
		cThostFtdcMdApi.Init();
	}

	/**
	 * 关闭
	 */
	synchronized void close() {
		if (cThostFtdcMdApi != null) {
			log.info("{} MdApi close and release start.", gatewayId);
			cThostFtdcMdApi.RegisterSpi(null);
			// 避免异步线程找不到引用
			// CThostFtdcMdApi cThostFtdcMdApiForRelease = cThostFtdcMdApi;
			// 由于CTP底层原因，部分情况下不能正确执行Release
			ThreadUtil.startNewThread(() -> apiRelease(), gatewayId + "-MdApiReleaseThread" + LocalDateTime.now());
			ThreadUtil.sleep(1000);
			cThostFtdcMdApi = null;
			isConnecting = false;
			isConnected = false;
			isLogin = false;
			log.info("{} MdApi instance close and release end.", gatewayId);
			// 通知停止其他关联实例
		} else
			log.warn("{} MdApi instance is null.", gatewayId);
	}

	private void apiRelease() {
		try {
			log.info("{} MdApi release thread start...", gatewayId);
			cThostFtdcMdApi.Release();
		} catch (Exception e) {
			log.error("{} MdApi release thread error...", gatewayId, e);
		}
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

	/**
	 * 订阅行情
	 * 
	 * @param rtSymbol
	 */
	void subscribe(String... symbols) {
		if (isConnected() && isLogin())
			cThostFtdcMdApi.SubscribeMarketData(symbols, symbols.length);
		else
			log.warn(gatewayId + "无法订阅行情,行情服务器尚未连接成功");
	}

	/**
	 * 退订行情
	 */
	void unsubscribe(String... symbols) {
		if (isConnected() && isLogin())
			cThostFtdcMdApi.UnSubscribeMarketData(symbols, symbols.length);
		else
			log.warn(gatewayId + "退订无效,行情服务器尚未连接成功");
	}

	void login() {
		if (StringUtil.isNullOrEmpty(brokerId) || StringUtil.isNullOrEmpty(userId) || StringUtil.isNullOrEmpty(password)) {
			log.error(gatewayId + " - brokerId or userId or password不允许为空");
			return;
		}
		// 登录
		CThostFtdcReqUserLoginField userLoginField = new CThostFtdcReqUserLoginField();
		userLoginField.setBrokerID(brokerId);
		userLoginField.setUserID(userId);
		userLoginField.setPassword(password);
		cThostFtdcMdApi.ReqUserLogin(userLoginField, 0);
	}

	void join() {
		cThostFtdcMdApi.Join();
	}

}
