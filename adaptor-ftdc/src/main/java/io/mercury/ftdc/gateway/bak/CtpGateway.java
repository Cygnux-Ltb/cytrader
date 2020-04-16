package io.mercury.ctp.gateway.bak;

import java.util.HashSet;

import org.slf4j.Logger;

import io.mercury.common.collections.queue.api.Queue;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.thread.ThreadUtil;
import io.mercury.ctp.gateway.bean.req.ReqCancelOrder;
import io.mercury.ctp.gateway.bean.req.ReqOrder;

/**
 * @author yellow013
 *
 */
@Deprecated
@SuppressWarnings("unused")
public class CtpGateway {

	private static Logger log = CommonLoggerFactory.getLogger(CtpGateway.class);

	static {
		try {
			// 根据操作系统选择加载的库文件
			if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
				System.loadLibrary("lib/win64/thosttraderapi");
				System.loadLibrary("lib/win64/thostmduserapi");
				System.loadLibrary("lib/win64/thostapi_wrap");
			} else {
				System.loadLibrary("lib/linux64/thosttraderapi");
				System.loadLibrary("lib/linux64/thostmduserapi");
				System.loadLibrary("lib/linux64/thostapi_wrap");
			}
			log.info("Load libs success...");
		} catch (Exception e) {
			log.error("Load libs error...", e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private MdApi mdApi;
	private TdApi tdApi;

	private MdSpi mdSpi;
	private TdSpi tdSpi;

	private String gatewayId;

	private HashSet<String> subscribeSymbols = new HashSet<>(); // 行情订阅列表

	private int tdFrontId; // 前置机编号
	private int tdSessionId; // 会话编号
	private Queue<Object> inboundQueue;

	public CtpGateway(String gatewayId, CtpConfig config) {
		this.gatewayId = gatewayId;
		this.mdSpi = new MdSpi(this);
		this.tdSpi = new TdSpi(this);
		if (config != null) {
			this.mdApi = new MdApi(gatewayId, mdSpi, config);
			this.tdApi = new TdApi(gatewayId, tdSpi, config);
		} else
			throw new RuntimeException("Cannot init, CtpConfig is null.");
		// ThreadUtil.startNewTimerTask(() -> query(), 0, 2500);
	}

	private void query() {
		if (isConnected())
			queryAccount();
		ThreadUtil.sleep(1150);
		if (isConnected())
			queryPosition();
		ThreadUtil.sleep(1150);
	}

	public String getGatewayId() {
		return gatewayId;
	}

	public HashSet<String> getSubscribedSymbols() {
		return subscribeSymbols;
	}

	public boolean isConnected() {
		return tdApi.isConnected() && mdApi.isConnected();
	}

	public boolean isLogin() {
		return tdApi.isLogin() && mdApi.isLogin();
	}

	public void connect() {
		if (mdApi != null)
			mdApi.connect();
		if (tdApi != null)
			tdApi.connect();
	}

	public void login() {
		if (mdApi != null)
			mdApi.login();
		if (tdApi != null)
			tdApi.login();
	}

	public void close() {
		// 判断连接状态
		if (mdApi != null && mdApi.isConnected())
			mdApi.close();
		if (tdApi != null && tdApi.isConnected())
			tdApi.close();
	}

	public void subscribe(String symbol) {
		subscribeSymbols.add(symbol);
		if (mdApi != null && mdApi.isLogin())
			mdApi.subscribe(symbol);
	}

	public void unsubscribe(String symbol) {
		subscribeSymbols.remove(symbol);
		if (mdApi != null && mdApi.isLogin())
			mdApi.unsubscribe(symbol);
	}

	public void sendOrder(ReqOrder reqOrder) {
		if (tdApi != null)
			tdApi.sendOrder(reqOrder);
	}

	public void cancelOrder(ReqCancelOrder reqCancelOrder) {
		if (tdApi != null)
			tdApi.cancelOrder(reqCancelOrder);
	}

	public void queryAccount() {
		if (tdApi != null)
			tdApi.queryAccount();
	}

	public void queryPosition() {
		if (tdApi != null)
			tdApi.queryPosition();
	}

	CtpGateway setTdFrontId(int tdFrontId) {
		this.tdFrontId = tdFrontId;
		return this;
	}

	CtpGateway setTdSessionId(int tdSessionId) {
		this.tdSessionId = tdSessionId;
		return this;
	}

	void onMdFrontConnected() {
		mdApi.setConnected(true);
		mdApi.login();
	}

	void onTdFrontConnected() {
		tdApi.setConnected(true);
		tdApi.login();
	}

	void onMdFrontDisconnected(int nReason) {
		log.info("Md front disconnected nReason==[{}]", nReason);
		if (mdApi != null)
			mdApi.close();
	}

	void onTdFrontDisconnected(int nReason) {
		log.info("Td front disconnected nReason==[{}]", nReason);
		if (tdApi != null)
			tdApi.close();
	}

	void onMdRspUserLogin(boolean isSuccess) {
		if (isSuccess) {
			mdApi.setLogin(true);
			if (!subscribeSymbols.isEmpty())
				mdApi.subscribe(subscribeSymbols.toArray(new String[subscribeSymbols.size()]));
		} else {
			// TODO 添加行情登录失败处理
		}
	}

	void onTdRspUserLogin(boolean isSuccess) {
		if (isSuccess) {
			tdApi.setLogin(true);
			query();
		} else
			tdApi.setLoginFailed(true);
	}

	void onMdRspUserLogout() {
		mdApi.setLogin(false);
	}

	void onTdRspUserLogout() {
		tdApi.setLogin(false);
	}

	void onMdRspError() {
		// TODO Auto-generated method stub
	}

	void onTdRspError() {
		// TODO Auto-generated method stub
	}

	void onRspSubMarketData() {
		// TODO Auto-generated method stub
	}

	void onRspUnSubMarketData() {
		// TODO Auto-generated method stub
	}

	void onRtnDepthMarketData() {
		// TODO Auto-generated method stub
	}

	void onRspAuthenticate() {
		tdApi.setAuth(true);
		tdApi.login();
	}

	void join() {
		mdApi.join();
		tdApi.join();
	}

	public static void main(String[] args) {

		CtpGateway ctpGateway = new CtpGateway("simnow-test",
				new CtpConfig().setMdAddress("tcp://180.168.146.187:10010").setTdAddress("tcp://180.168.146.187:10000")
						.setBrokerId("9999").setUserId("005853").setPassword("jinpengpass101"));
		ctpGateway.connect();
		ctpGateway.subscribe("rb1905");
		ctpGateway.join();

	}

}
