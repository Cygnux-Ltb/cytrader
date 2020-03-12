package io.mercury.gateway.ctp;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;

import io.mercury.common.concurrent.queue.MpscArrayBlockingQueue;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.thread.ThreadUtil;
import io.mercury.ctp.gateway.CtpGateway;
import io.mercury.ctp.gateway.bean.config.CtpConnectionInfo;
import io.mercury.ctp.gateway.bean.rsp.RspDepthMarketData;
import io.mercury.ctp.gateway.bean.rsp.RtnOrder;
import io.mercury.ctp.gateway.bean.rsp.RtnTrade;

public class CtpGatewayTest {

	private final Logger logger = CommonLoggerFactory.getLogger(CtpGatewayTest.class);

	//标准CTP
//	private String TradeAddress = "tcp://180.168.146.187:10100";
//	private String MdAddress = "tcp://180.168.146.187:10110";
	
	//7*24 CTP连通测试
	private String TradeAddress = "tcp://180.168.146.187:10130";
	private String MdAddress = "tcp://180.168.146.187:10131";

	private String BrokerId = "9999";
	private String InvestorId = "132796";
	private String UserId = "132796";
	private String AccountId = "132796";
	private String Password = "tc311911";

	private String TradingDay = "20200302";
	private String CurrencyId = "CNY";

	private String GatewayId = "simnow_test";

	@Test
	public void test() {

		CtpConnectionInfo simnowUserInfo = CtpConnectionInfo.newEmpty().setTraderAddress(TradeAddress).setMdAddress(MdAddress)
				.setBrokerId(BrokerId).setInvestorId(InvestorId).setUserId(UserId).setAccountId(AccountId)
				.setPassword(Password).setTradingDay(TradingDay).setCurrencyId(CurrencyId);

		CtpGateway gateway = new CtpGateway(GatewayId, simnowUserInfo,
				MpscArrayBlockingQueue.autoStartQueue("Simnow-Handle-Queue", 1024, msg -> {
					switch (msg.getType()) {
					case DepthMarketData:
						RspDepthMarketData depthMarketData = msg.getRspDepthMarketData();
						logger.info(
								"Handle CThostFtdcDepthMarketDataField -> InstrumentID==[{}]  UpdateTime==[{}]  UpdateMillisec==[{}]  AskPrice1==[{}]  BidPrice1==[{}]",
								depthMarketData.getInstrumentID(), depthMarketData.getUpdateTime(),
								depthMarketData.getUpdateMillisec(), depthMarketData.getAskPrice1(),
								depthMarketData.getBidPrice1());
						break;
					case RtnOrder:
						RtnOrder order = msg.getRtnOrder();
						logger.info("Handle RtnOrder -> OrderRef==[{}]", order.getOrderRef());
						break;
					case RtnTrade:
						RtnTrade trade = msg.getRtnTrade();
						logger.info("Handle RtnTrade -> OrderRef==[{}]", trade.getOrderRef());
						break;
					default:
						break;
					}
				}));
		gateway.initAndJoin();
		Set<String> instruementIdSet = new HashSet<>();
		instruementIdSet.add("rb2010");
		gateway.subscribeMarketData(instruementIdSet);
		ThreadUtil.join();
	}

}
