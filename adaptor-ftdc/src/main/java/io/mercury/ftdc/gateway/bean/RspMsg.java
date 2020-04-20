package io.mercury.ftdc.gateway.bean;

public final class RspMsg {

	private RspType rspType;

	// 返回交易接口连接信息
	private RspTraderConnect rspTraderConnect;
	// 返回行情接口连接信息
	private RspMdConnect rspMdConnect;

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

	public RspMsg(RspTraderConnect rspTraderConnect) {
		this.rspType = RspType.RspTraderConnect;
		this.rspTraderConnect = rspTraderConnect;
	}

	public RspMsg(RspMdConnect rspMdConnect) {
		this.rspType = RspType.RspMdConnect;
		this.rspMdConnect = rspMdConnect;
	}

	public RspMsg(FtdcDepthMarketData ftdcDepthMarketData) {
		this.rspType = RspType.FtdcDepthMarketData;
		this.ftdcDepthMarketData = ftdcDepthMarketData;
	}

	public RspMsg(FtdcOrder ftdcOrder) {
		this.rspType = RspType.FtdcOrder;
		this.ftdcOrder = ftdcOrder;
	}

	public RspMsg(FtdcTrade ftdcTrade) {
		this.rspType = RspType.FtdcTrade;
		this.ftdcTrade = ftdcTrade;
	}

	public RspMsg(FtdcInputOrder ftdcInputOrder) {
		this.rspType = RspType.FtdcInputOrder;
		this.ftdcInputOrder = ftdcInputOrder;
	}

	public RspMsg(FtdcInputOrderAction ftdcInputOrderAction) {
		this.rspType = RspType.FtdcInputOrderAction;
		this.ftdcInputOrderAction = ftdcInputOrderAction;
	}

	public RspMsg(FtdcOrderAction ftdcOrderAction) {
		this.rspType = RspType.FtdcOrderAction;
		this.ftdcOrderAction = ftdcOrderAction;
	}

	public RspType getRspType() {
		return rspType;
	}

	public FtdcDepthMarketData getFtdcDepthMarketData() {
		return ftdcDepthMarketData;
	}

	public RspTraderConnect getRspTraderConnect() {
		return rspTraderConnect;
	}

	public RspMdConnect getRspMdConnect() {
		return rspMdConnect;
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

	public static enum RspType {

		FtdcDepthMarketData,

		RspTraderConnect,

		RspMdConnect,

		FtdcOrder,

		FtdcTrade,

		FtdcInputOrder,

		FtdcInputOrderAction,

		FtdcOrderAction,

		Other,

		;

	}

}
