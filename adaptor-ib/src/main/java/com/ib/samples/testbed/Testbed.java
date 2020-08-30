package com.ib.samples.testbed;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.ib.client.EClientSocket;
import com.ib.client.EReader;
import com.ib.client.EReaderSignal;
import com.ib.client.ExecutionFilter;
import com.ib.client.Order;
import com.ib.client.Types.FADataType;
import com.ib.samples.testbed.advisor.FAMethodSamples;
import com.ib.samples.testbed.contracts.ContractSamples;
import com.ib.samples.testbed.orders.AvailableAlgoParams;
import com.ib.samples.testbed.orders.OrderSamples;
import com.ib.samples.testbed.scanner.ScannerSubscriptionSamples;

public class Testbed {

	public static void main(String[] args) throws InterruptedException {
		EWrapperImpl wrapper = new EWrapperImpl();

		final EClientSocket m_client = wrapper.getClient();
		final EReaderSignal m_signal = wrapper.getSignal();
		// ! [connect]
		m_client.eConnect("127.0.0.1", 7496, 0);
		// ! [connect]
		// ! [ereader]
		final EReader reader = new EReader(m_client, m_signal);
		reader.start();
		new Thread() {
			public void run() {
				while (m_client.isConnected()) {
					m_signal.waitForSignal();
					try {
						reader.processMsgs();
					} catch (Exception e) {
						System.out.println("Exception: " + e.getMessage());
					}
				}
			}
		}.start();
		// ! [ereader]
		Thread.sleep(1000);

		// orderOperations(wrapper.getClient(), wrapper.getCurrentOrderId());
		// contractOperations(wrapper.getClient());
		// hedgeSample(wrapper.getClient(), wrapper.getCurrentOrderId());
		// testAlgoSamples(wrapper.getClient(), wrapper.getCurrentOrderId());
		// bracketSample(wrapper.getClient(), wrapper.getCurrentOrderId());
		// bulletins(wrapper.getClient());
		// reutersFundamentals(wrapper.getClient());
		// marketDataType(wrapper.getClient());
		// historicalDataRequests(wrapper.getClient());
		// accountOperations(wrapper.getClient());

		Thread.sleep(100000);
		m_client.eDisconnect();
	}

	@SuppressWarnings("unused")
	private static void orderOperations(EClientSocket client, int nextOrderId) throws InterruptedException {

		/*** Requesting the next valid id ***/
		// ! [reqids]
		// The parameter is always ignored.
		client.reqIds(-1);
		// ! [reqids]
		// Thread.sleep(1000);
		/*** Requesting all open orders ***/
		// ! [reqallopenorders]
		client.reqAllOpenOrders();
		// ! [reqallopenorders]
		// Thread.sleep(1000);
		/*** Taking over orders to be submitted via TWS ***/
		// ! [reqautoopenorders]
		client.reqAutoOpenOrders(true);
		// ! [reqautoopenorders]
		// Thread.sleep(1000);
		/*** Requesting this API client's orders ***/
		// ! [reqopenorders]
		client.reqOpenOrders();
		// ! [reqopenorders]
		// Thread.sleep(1000);

		/***
		 * Placing/modifying an order - remember to ALWAYS increment the nextValidId
		 * after placing an order so it can be used for the next one!
		 ***/
		// ! [order_submission]
		client.placeOrder(nextOrderId++, ContractSamples.USStock(), OrderSamples.LimitOrder("SELL", 1, 50));
		// ! [order_submission]

		// ! [faorderoneaccount]
		Order faOrderOneAccount = OrderSamples.MarketOrder("BUY", 100);
		// Specify the Account Number directly
		faOrderOneAccount.account("DU119915");
		client.placeOrder(nextOrderId++, ContractSamples.USStock(), faOrderOneAccount);
		// ! [faorderoneaccount]

		// ! [faordergroupequalquantity]
		Order faOrderGroupEQ = OrderSamples.LimitOrder("SELL", 200, 2000);
		faOrderGroupEQ.faGroup("Group_Equal_Quantity");
		faOrderGroupEQ.faMethod("EqualQuantity");
		client.placeOrder(nextOrderId++, ContractSamples.USStock(), faOrderGroupEQ);
		// ! [faordergroupequalquantity]

		// ! [faordergrouppctchange]
		Order faOrderGroupPC = OrderSamples.MarketOrder("BUY", 0);
		;
		// You should not specify any order quantity for PctChange allocation method
		faOrderGroupPC.faGroup("Pct_Change");
		faOrderGroupPC.faMethod("PctChange");
		faOrderGroupPC.faPercentage("100");
		client.placeOrder(nextOrderId++, ContractSamples.EurGbpFx(), faOrderGroupPC);
		// ! [faordergrouppctchange]

		// ! [faorderprofile]
		Order faOrderProfile = OrderSamples.LimitOrder("BUY", 200, 100);
		faOrderProfile.faProfile("Percent_60_40");
		client.placeOrder(nextOrderId++, ContractSamples.EuropeanStock(), faOrderProfile);
		// ! [faorderprofile]

		// client.placeOrder(nextOrderId++, ContractSamples.USStock(),
		// OrderSamples.PeggedToMarket("BUY", 10, 0.01));
		// client.placeOrder(nextOrderId++, ContractSamples.EurGbpFx(),
		// OrderSamples.MarketOrder("BUY", 10));
		// client.placeOrder(nextOrderId++, ContractSamples.USStock(),
		// OrderSamples.Discretionary("SELL", 1, 45, 0.5));

		// ! [reqexecutions]
		client.reqExecutions(10001, new ExecutionFilter());
		// ! [reqexecutions]

		Thread.sleep(10000);

	}

	@SuppressWarnings("unused")
	private static void OcaSample(EClientSocket client, int nextOrderId) {

		// OCA order
		// ! [ocasubmit]
		List<Order> OcaOrders = new ArrayList<Order>();
		OcaOrders.add(OrderSamples.LimitOrder("BUY", 1, 10));
		OcaOrders.add(OrderSamples.LimitOrder("BUY", 1, 11));
		OcaOrders.add(OrderSamples.LimitOrder("BUY", 1, 12));
		OcaOrders = OrderSamples.OneCancelsAll("TestOCA_" + nextOrderId, OcaOrders, 2);
		for (Order o : OcaOrders) {

			client.placeOrder(nextOrderId++, ContractSamples.USStock(), o);
		}
		// ! [ocasubmit]

	}

	@SuppressWarnings("unused")
	private static void tickDataOperations(EClientSocket client) throws InterruptedException {

		/*** Requesting real time market data ***/
		// Thread.sleep(1000);
		// ! [reqmktdata]
		client.reqMktData(1001, ContractSamples.StockComboContract(), "", false, null);
		// ! [reqmktdata]

		// ! [reqmktdata_snapshot]
		client.reqMktData(1003, ContractSamples.FutureComboContract(), "", true, null);
		// ! [reqmktdata_snapshot]

		// ! [reqmktdata_genticks]
		// Requesting RTVolume (Time & Sales), shortable and Fundamental Ratios generic
		// ticks
		client.reqMktData(1004, ContractSamples.USStock(), "233,236,258", false, null);
		// ! [reqmktdata_genticks]
		// ! [reqmktdata_contractnews]
		client.reqMktData(1005, ContractSamples.USStock(), "mdoff,292:BZ", false, null);
		client.reqMktData(1006, ContractSamples.USStock(), "mdoff,292:BT", false, null);
		client.reqMktData(1007, ContractSamples.USStock(), "mdoff,292:FLY", false, null);
		client.reqMktData(1008, ContractSamples.USStock(), "mdoff,292:MT", false, null);
		// ! [reqmktdata_contractnews]
		// ! [reqmktdata_broadtapenews]
		client.reqMktData(1009, ContractSamples.BTbroadtapeNewsFeed(), "mdoff,292", false, null);
		client.reqMktData(1010, ContractSamples.BZbroadtapeNewsFeed(), "mdoff,292", false, null);
		client.reqMktData(1011, ContractSamples.FLYbroadtapeNewsFeed(), "mdoff,292", false, null);
		client.reqMktData(1012, ContractSamples.MTbroadtapeNewsFeed(), "mdoff,292", false, null);
		// ! [reqmktdata_broadtapenews]
		// ! [reqoptiondatagenticks]
		// Requesting data for an option contract will return the greek values
		client.reqMktData(1002, ContractSamples.OptionWithLocalSymbol(), "", false, null);
		// ! [reqoptiondatagenticks]

		Thread.sleep(10000);
		// ! [cancelmktdata]
		client.cancelMktData(1001);
		client.cancelMktData(1002);
		client.cancelMktData(1003);
		// ! [cancelmktdata]

	}

	@SuppressWarnings("unused")
	private static void historicalDataRequests(EClientSocket client) throws InterruptedException {

		/*** Requesting historical data ***/
		// ! [reqhistoricaldata]
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -6);
		SimpleDateFormat form = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		String formatted = form.format(cal.getTime());
		client.reqHistoricalData(4001, ContractSamples.EurGbpFx(), formatted, "1 M", "1 day", "MIDPOINT", 1, 1, null);
		client.reqHistoricalData(4002, ContractSamples.EuropeanStock(), formatted, "10 D", "1 min", "TRADES", 1, 1,
				null);
		Thread.sleep(2000);
		/*** Canceling historical data requests ***/
		client.cancelHistoricalData(4001);
		client.cancelHistoricalData(4002);
		// ! [reqhistoricaldata]

	}

	@SuppressWarnings("unused")
	private static void realTimeBars(EClientSocket client) throws InterruptedException {

		/*** Requesting real time bars ***/
		// ! [reqrealtimebars]
		client.reqRealTimeBars(3001, ContractSamples.EurGbpFx(), 5, "MIDPOINT", true, null);
		// ! [reqrealtimebars]
		Thread.sleep(2000);
		/*** Canceling real time bars ***/
		// ! [cancelrealtimebars]
		client.cancelRealTimeBars(3001);
		// ! [cancelrealtimebars]

	}

	@SuppressWarnings("unused")
	private static void marketDepthOperations(EClientSocket client) throws InterruptedException {

		/*** Requesting the Deep Book ***/
		// ! [reqMarketDepth]
		client.reqMktDepth(2001, ContractSamples.EurGbpFx(), 5, null);
		// ! [reqMarketDepth]
		Thread.sleep(2000);
		/*** Canceling the Deep Book request ***/
		// ! [cancelMktDepth]
		client.cancelMktDepth(2001);
		// ! [cancelMktDepth]

	}

	@SuppressWarnings("unused")
	private static void accountOperations(EClientSocket client) throws InterruptedException {

		// client.reqAccountUpdatesMulti(9002, null, "EUstocks", true);
		// ! [reqPositionsMulti]
		client.reqPositionsMulti(9003, "DU74649", "EUstocks");
		// ! [reqPositionsMulti]
		Thread.sleep(10000);

		/*** Requesting managed accounts ***/
		// ! [reqManagedAccts]
		client.reqManagedAccts();
		// ! [reqManagedAccts]
		/*** Requesting accounts' summary ***/
		Thread.sleep(2000);
		// ! [reqAccountSummary]
		client.reqAccountSummary(9001, "All",
				"AccountType,NetLiquidation,TotalCashValue,SettledCash,AccruedCash,BuyingPower,EquityWithLoanValue,PreviousEquityWithLoanValue,GrossPositionValue,ReqTEquity,ReqTMargin,SMA,InitMarginReq,MaintMarginReq,AvailableFunds,ExcessLiquidity,Cushion,FullInitMarginReq,FullMaintMarginReq,FullAvailableFunds,FullExcessLiquidity,LookAheadNextChange,LookAheadInitMarginReq ,LookAheadMaintMarginReq,LookAheadAvailableFunds,LookAheadExcessLiquidity,HighestSeverity,DayTradesRemaining,Leverage");
		// ! [reqAccountSummary]

		// ! [reqAccountSummaryLedger]
		client.reqAccountSummary(9002, "All", "$LEDGER");
		// ! [reqAccountSummaryLedger]
		Thread.sleep(2000);
		// ! [reqAccountSummaryLedgerCurrency]
		client.reqAccountSummary(9003, "All", "$LEDGER:EUR");
		// ! [reqAccountSummaryLedgerCurrency]
		Thread.sleep(2000);
		// ! [reqAccountSummaryLedgerAll]
		client.reqAccountSummary(9004, "All", "$LEDGER:ALL");
		// ! [reqAccountSummaryLedgerAll]

		// ! [cancelAcountSummary]
		client.cancelAccountSummary(9001);
		client.cancelAccountSummary(9002);
		client.cancelAccountSummary(9003);
		client.cancelAccountSummary(9004);
		// ! [cancelAccountSummary]

		/*** Subscribing to an account's information. Only one at a time! ***/
		Thread.sleep(2000);
		// ! [reqAccountUpdates]
		client.reqAccountUpdates(true, "U150462");
		// ! [reqAccountUpdates]
		Thread.sleep(2000);
		// ! [cancelAccountUpdates]
		client.reqAccountUpdates(false, "U150462");
		// ! [cancelAccountUpdates]

		// ! [reqAccountUpdatesMulti]
		client.reqAccountUpdatesMulti(9002, "U150462", "EUstocks", true);
		// ! [reqAccountUpdatesMulti]
		Thread.sleep(2000);
		/*** Requesting all accounts' positions. ***/
		// ! [reqPositions]
		client.reqPositions();
		// ! [reqPositions]
		Thread.sleep(2000);
		// ! [cancelPositions]
		client.cancelPositions();
		// ! [cancelPositions]
	}

	@SuppressWarnings("unused")
	private static void conditionSamples(EClientSocket client, int nextOrderId) {

		// ! [order_conditioning_activate]
		Order mkt = OrderSamples.MarketOrder("BUY", 100);
		// Order will become active if conditioning criteria is met
		mkt.conditionsCancelOrder(true);
		mkt.conditions().add(OrderSamples.PriceCondition(208813720, "SMART", 600, false, false));
		mkt.conditions().add(OrderSamples.ExecutionCondition("EUR.USD", "CASH", "IDEALPRO", true));
		mkt.conditions().add(OrderSamples.MarginCondition(30, true, false));
		mkt.conditions().add(OrderSamples.PercentageChangeCondition(15.0, 208813720, "SMART", true, true));
		mkt.conditions().add(OrderSamples.TimeCondition("20160118 23:59:59", true, false));
		mkt.conditions().add(OrderSamples.VolumeCondition(208813720, "SMART", false, 100, true));
		client.placeOrder(nextOrderId++, ContractSamples.EuropeanStock(), mkt);
		// ! [order_conditioning_activate]

		// Conditions can make the order active or cancel it. Only LMT orders can be
		// conditionally canceled.
		// ! [order_conditioning_cancel]
		Order lmt = OrderSamples.LimitOrder("BUY", 100, 20);
		// The active order will be cancelled if conditioning criteria is met
		lmt.conditionsCancelOrder(true);
		lmt.conditions().add(OrderSamples.PriceCondition(208813720, "SMART", 600, false, false));
		client.placeOrder(nextOrderId++, ContractSamples.EuropeanStock(), lmt);
		// ! [order_conditioning_cancel]

	}

	@SuppressWarnings("unused")
	private static void contractOperations(EClientSocket client) {

		// ! [reqContractDetails]
		client.reqContractDetails(210, ContractSamples.OptionForQuery());
		// ! [reqContractDetails]

	}

	@SuppressWarnings("unused")
	private static void contractNewsFeed(EClientSocket client) {

		// ! [reqContractDetailsnews]
		client.reqContractDetails(211, ContractSamples.NewsFeedForQuery());
		// ! [reqContractDetailsnews]

	}

	@SuppressWarnings("unused")
	private static void hedgeSample(EClientSocket client, int nextOrderId) throws InterruptedException {

		// F Hedge order
		// ! [hedgeSubmit]
		// Parent order on a contract which currency differs from your base currency
		Order parent = OrderSamples.LimitOrder("BUY", 100, 10);
		parent.orderId(nextOrderId++);
		// Hedge on the currency conversion
		Order hedge = OrderSamples.MarketFHedge(parent.orderId(), "BUY");
		// Place the parent first...
		client.placeOrder(parent.orderId(), ContractSamples.EuropeanStock(), parent);
		// Then the hedge order
		client.placeOrder(nextOrderId++, ContractSamples.EurGbpFx(), hedge);
		// ! [hedgeSubmit]

	}

	@SuppressWarnings("unused")
	private static void testAlgoSamples(EClientSocket client, int nextOrderId) throws InterruptedException {

		// ! [algo_base_order]
		Order baseOrder = OrderSamples.LimitOrder("BUY", 1000, 1);
		// ! [algo_base_order]

		// ! [arrivalPx]
		AvailableAlgoParams.FillArrivalPriceParams(baseOrder, 0.1, "Aggressive", "09:00:00 CET", "16:00:00 CET", true,
				true);
		client.placeOrder(nextOrderId++, ContractSamples.USStockAtSmart(), baseOrder);
		// ! [arrivalPx]

		Thread.sleep(500);

		// ! [darkIce]
		AvailableAlgoParams.FillDarkIceParams(baseOrder, 10, "09:00:00 CET", "16:00:00 CET", true);
		client.placeOrder(nextOrderId++, ContractSamples.USStockAtSmart(), baseOrder);
		// ! [darkIce]

		Thread.sleep(500);

		// ! [ad]
		AvailableAlgoParams.FillAccumulateDistributeParams(baseOrder, 10, 60, true, true, 1, true, true, "09:00:00 CET",
				"16:00:00 CET");
		client.placeOrder(nextOrderId++, ContractSamples.USStockAtSmart(), baseOrder);
		// ! [ad]

		Thread.sleep(500);

		// ! [twap]
		AvailableAlgoParams.FillTwapParams(baseOrder, "Marketable", "09:00:00 CET", "16:00:00 CET", true);
		client.placeOrder(nextOrderId++, ContractSamples.USStockAtSmart(), baseOrder);
		// ! [twap]

		Thread.sleep(500);

		// ! [vwap]
		AvailableAlgoParams.FillVwapParams(baseOrder, 0.2, "09:00:00 CET", "16:00:00 CET", true, true);
		client.placeOrder(nextOrderId++, ContractSamples.USStockAtSmart(), baseOrder);
		// ! [vwap]

		Thread.sleep(500);

		// ! [balanceImpactRisk]
		AvailableAlgoParams.FillBalanceImpactRiskParams(baseOrder, 0.1, "Aggressive", true);
		client.placeOrder(nextOrderId++, ContractSamples.USOptionContract(), baseOrder);
		// ! [balanceImpactRisk]

		Thread.sleep(500);

		// ! [minImpact]
		AvailableAlgoParams.FillMinImpactParams(baseOrder, 0.3);
		client.placeOrder(nextOrderId++, ContractSamples.USOptionContract(), baseOrder);
		// ! [minImpact]

		// ! [adaptive]
		AvailableAlgoParams.FillAdaptiveParams(baseOrder, "Normal");
		client.placeOrder(nextOrderId++, ContractSamples.USStockAtSmart(), baseOrder);
		// ! [adaptive]

	}

	@SuppressWarnings("unused")
	private static void bracketSample(EClientSocket client, int nextOrderId) throws InterruptedException {

		// BRACKET ORDER
		// ! [bracketsubmit]
		List<Order> bracket = OrderSamples.BracketOrder(nextOrderId++, "BUY", 100, 30, 40, 20);
		for (Order o : bracket) {
			client.placeOrder(o.orderId(), ContractSamples.EuropeanStock(), o);
		}
		// ! [bracketsubmit]

	}

	@SuppressWarnings("unused")
	private static void bulletins(EClientSocket client) throws InterruptedException {

		// ! [reqNewsBulletins]
		client.reqNewsBulletins(true);
		// ! [reqNewsBulletins]

		Thread.sleep(2000);

		// ! [cancelNewsBulletins]
		client.cancelNewsBulletins();
		// ! [cancelNewsBulletins]

	}

	@SuppressWarnings("unused")
	private static void reutersFundamentals(EClientSocket client) throws InterruptedException {

		// ! [reqFundamentalData]
		client.reqFundamentalData(8001, ContractSamples.USStock(), "ReportsFinSummary");
		// ! [reqFundamentalData]

		Thread.sleep(2000);

		// ! [fundamentalExamples]
		client.reqFundamentalData(8002, ContractSamples.USStock(), "ReportSnapshot"); // for company overview
		client.reqFundamentalData(8003, ContractSamples.USStock(), "ReportRatios"); // for financial ratios
		client.reqFundamentalData(8004, ContractSamples.USStock(), "ReportsFinStatements"); // for financial statements
		client.reqFundamentalData(8005, ContractSamples.USStock(), "RESC"); // for analyst estimates
		client.reqFundamentalData(8006, ContractSamples.USStock(), "CalendarReport"); // for company calendar
		// ! [fundamentalExamples]

		// ! [cancelFundamentalData]
		client.cancelFundamentalData(8001);
		// ! [cancelFundamentalData]

	}

	@SuppressWarnings("unused")
	private static void marketScanners(EClientSocket client) throws InterruptedException {

		/***
		 * Requesting all available parameters which can be used to build a scanner
		 * request
		 ***/
		// ! [reqScannerParameters]
		client.reqScannerParameters();
		// ! [reqScannerParameters]
		Thread.sleep(2000);

		/*** Triggering a scanner subscription ***/
		// ! [reqScannerSubscription]
		client.reqScannerSubscription(7001, ScannerSubscriptionSamples.HighOptVolumePCRatioUSIndexes(), null);
		// ! [reqScannerSubscription]

		Thread.sleep(2000);
		/*** Canceling the scanner subscription ***/
		// ! [cancelScannerSubscription]
		client.cancelScannerSubscription(7001);
		// ! [cancelScannerSubscription]

	}

	@SuppressWarnings("unused")
	private static void financialAdvisorOperations(EClientSocket client) {

		/*** Requesting FA information ***/
		// ! [requestFaaliases]
		client.requestFA(FADataType.ALIASES.ordinal());
		// ! [requestFaaliases]

		// ! [requestFagroups]
		client.requestFA(FADataType.GROUPS.ordinal());
		// ! [requestFagroups]

		// ! [requestFaprofiles]
		client.requestFA(FADataType.PROFILES.ordinal());
		// ! [requestFaprofiles]

		/*** Replacing FA information - Fill in with the appropriate XML string. ***/
		// ! [replaceFaonegroup]
		client.replaceFA(FADataType.GROUPS.ordinal(), FAMethodSamples.FaOneGroup);
		// ! [replaceFaonegroup]

		// ! [replaceFatwogroups]
		client.replaceFA(FADataType.GROUPS.ordinal(), FAMethodSamples.FaTwoGroups);
		// ! [replaceFatwogroups]

		// ! [replaceFaoneprofile]
		client.replaceFA(FADataType.PROFILES.ordinal(), FAMethodSamples.FaOneProfile);
		// ! [replaceFaoneprofile]

		// ! [replaceFatwoprofiles]
		client.replaceFA(FADataType.PROFILES.ordinal(), FAMethodSamples.FaTwoProfiles);
		// ! [replaceFatwoprofiles]

	}

	@SuppressWarnings("unused")
	private static void testDisplayGroups(EClientSocket client) throws InterruptedException {

		// ! [queryDisplayGroups]
		client.queryDisplayGroups(9001);
		// ! [queryDisplayGroups]

		Thread.sleep(500);

		// ! [subscribeToGroupEvents]
		client.subscribeToGroupEvents(9002, 1);
		// ! [subscribeToGroupEvents]

		Thread.sleep(500);

		// ! [updateDisplayGroup]
		client.updateDisplayGroup(9002, "8314@SMART");
		// ! [updateDisplayGroup]

		Thread.sleep(500);

		// ! [subscribeFromGroupEvents]
		client.unsubscribeFromGroupEvents(9002);
		// ! [subscribeFromGroupEvents]

	}

	@SuppressWarnings("unused")
	private static void marketDataType(EClientSocket client) {

		// ! [reqMarketDataType]
		/*** Switch to live (1) frozen (2) delayed (3) or delayed frozen (4) ***/
		client.reqMarketDataType(2);
		// ! [reqMarketDataType]

	}

	@SuppressWarnings("unused")
	private static void optionsOperations(EClientSocket client) {

		// ! [reqSecDefOptParams]
		client.reqSecDefOptParams(0, "IBM", "", "STK", 8314);
		// ! [reqSecDefOptParams]

		// ! [calculateImpliedVolatility]
		client.calculateImpliedVolatility(5001, ContractSamples.OptionAtBOX(), 5, 85);
		// ! [calculateImpliedVolatility]

		// ** Canceling implied volatility ***
		client.cancelCalculateImpliedVolatility(5001);

		// ! [calculateOptionPrice]
		client.calculateOptionPrice(5002, ContractSamples.OptionAtBOX(), 0.22, 85);
		// ! [calculateOptionPrice]

		// ** Canceling option's price calculation ***
		client.cancelCalculateOptionPrice(5002);

		// ! [exercise_options]
		// ** Exercising options ***
		client.exerciseOptions(5003, ContractSamples.OptionWithTradingClass(), 1, 1, "", 1);
		// ! [exercise_options]
	}

}
