package io.mercury.ftdc.gateway.bean;

public final class RspMsg {

	private RspType rspType;

	// 返回连接信息
	private RspConnectInfo rspConnectInfo;

	// 返回行情
	private FtdcDepthMarketData ftdcDepthMarketData;

	// 报单推送
	private FtdcOrder ftdcOrder;
	// 成交推送
	private FtdcTrade ftdcTrade;

	// 返回报单错误
	private FtdcInputOrder ftdcInputOrder;
	// 返回撤单错误
	private FtdcInputOrderAction ftdcInputOrderAction;
	// 返回撤单错误
	private FtdcOrderAction ftdcOrderAction;

	private RspMsg(RspType rspType, RspConnectInfo rspConnectInfo) {
		this.rspType = rspType;
		this.rspConnectInfo = rspConnectInfo;
	}

	private RspMsg(RspType rspType, FtdcDepthMarketData ftdcDepthMarketData) {
		this.rspType = rspType;
		this.ftdcDepthMarketData = ftdcDepthMarketData;
	}

	private RspMsg(RspType rspType, FtdcOrder ftdcOrder) {
		this.rspType = rspType;
		this.ftdcOrder = ftdcOrder;
	}

	private RspMsg(RspType rspType, FtdcTrade ftdcTrade) {
		this.rspType = rspType;
		this.ftdcTrade = ftdcTrade;
	}

	private RspMsg(RspType rspType, FtdcInputOrder ftdcInputOrder) {
		this.rspType = rspType;
		this.ftdcInputOrder = ftdcInputOrder;
	}

	private RspMsg(RspType rspType, FtdcInputOrderAction ftdcInputOrderAction) {
		this.rspType = rspType;
		this.ftdcInputOrderAction = ftdcInputOrderAction;
	}

	private RspMsg(RspType rspType, FtdcOrderAction ftdcOrderAction) {
		this.rspType = rspType;
		this.ftdcOrderAction = ftdcOrderAction;
	}

	public RspType getRspType() {
		return rspType;
	}

	public FtdcDepthMarketData getFtdcDepthMarketData() {
		return ftdcDepthMarketData;
	}

	public RspConnectInfo getRspConnectInfo() {
		return rspConnectInfo;
	}

	public FtdcOrder getFtdcOrder() {
		return ftdcOrder;
	}

	public FtdcTrade getFtdcTrade() {
		return ftdcTrade;
	}

	public FtdcInputOrder getFtdcInputOrder() {
		return ftdcInputOrder;
	}

	public FtdcInputOrderAction getFtdcInputOrderAction() {
		return ftdcInputOrderAction;
	}

	public FtdcOrderAction getFtdcOrderAction() {
		return ftdcOrderAction;
	}

	public static final RspMsg withRspConnectInfo(RspConnectInfo rspConnectInfo) {
		return new RspMsg(RspType.RspConnectInfo, rspConnectInfo);
	}

	public static final RspMsg withDepthMarketData(FtdcDepthMarketData depthMarketData) {
		return new RspMsg(RspType.FtdcDepthMarketData, depthMarketData);
	}

	public static final RspMsg withFtdcOrder(FtdcOrder order) {
		return new RspMsg(RspType.FtdcOrder, order);
	}

	public static final RspMsg withFtdcTrade(FtdcTrade trade) {
		return new RspMsg(RspType.FtdcTrade, trade);
	}

	public static final RspMsg withFtdcInputOrder(FtdcInputOrder ftdcInputOrder) {
		return new RspMsg(RspType.FtdcInputOrder, ftdcInputOrder);
	}

	public static final RspMsg withErrFtdcInputOrder(FtdcInputOrder ftdcInputOrder) {
		return new RspMsg(RspType.ErrFtdcInputOrder, ftdcInputOrder);
	}

	public static final RspMsg withFtdcInputOrderAction(FtdcInputOrderAction ftdcInputOrderAction) {
		return new RspMsg(RspType.FtdcInputOrderAction, ftdcInputOrderAction);
	}

	public static final RspMsg withErrFtdcOrderAction(FtdcOrderAction ftdcOrderAction) {
		return new RspMsg(RspType.ErrFtdcOrderAction, ftdcOrderAction);
	}

	public static enum RspType {

		FtdcDepthMarketData,

		RspConnectInfo,

		FtdcOrder,

		FtdcTrade,

		FtdcInputOrder,

		ErrFtdcInputOrder,

		FtdcInputOrderAction,

		ErrFtdcOrderAction,

		Other,

		;

	}

}
