package io.mercury.ftdc.gateway.bean;

public final class FtdcRespMsg {

	private FtdcRespType ftdcRespType;
	private FtdcDepthMarketData ftdcDepthMarketData;

	private RspConnectInfo rspConnectInfo;

	private FtdcOrder ftdcOrder;
	private FtdcTrade ftdcTrade;

	private FtdcInputOrder ftdcInputOrder;
	private FtdcInputOrderAction ftdcInputOrderAction;
	private FtdcOrderAction ftdcOrderAction;

	private FtdcRespMsg(FtdcRespType ftdcRespType, FtdcDepthMarketData ftdcDepthMarketData) {
		this.ftdcRespType = ftdcRespType;
		this.ftdcDepthMarketData = ftdcDepthMarketData;
	}

	private FtdcRespMsg(FtdcRespType ftdcRespType, FtdcOrder ftdcOrder) {
		this.ftdcRespType = ftdcRespType;
		this.ftdcOrder = ftdcOrder;
	}

	private FtdcRespMsg(FtdcRespType ftdcRespType, FtdcTrade ftdcTrade) {
		this.ftdcRespType = ftdcRespType;
		this.ftdcTrade = ftdcTrade;
	}

	private FtdcRespMsg(FtdcRespType ftdcRespType, FtdcInputOrder ftdcInputOrder) {
		this.ftdcRespType = ftdcRespType;
		this.ftdcInputOrder = ftdcInputOrder;
	}

	private FtdcRespMsg(FtdcRespType ftdcRespType, FtdcInputOrderAction ftdcInputOrderAction) {
		this.ftdcRespType = ftdcRespType;
		this.ftdcInputOrderAction = ftdcInputOrderAction;
	}

	private FtdcRespMsg(FtdcRespType ftdcRespType, FtdcOrderAction ftdcOrderAction) {
		this.ftdcRespType = ftdcRespType;
		this.ftdcOrderAction = ftdcOrderAction;
	}

	public FtdcRespType getFtdcRespType() {
		return ftdcRespType;
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

	public static final FtdcRespMsg withDepthMarketData(FtdcDepthMarketData depthMarketData) {
		return new FtdcRespMsg(FtdcRespType.FtdcDepthMarketData, depthMarketData);
	}

	public static final FtdcRespMsg withFtdcOrder(FtdcOrder order) {
		return new FtdcRespMsg(FtdcRespType.FtdcOrder, order);
	}

	public static final FtdcRespMsg withFtdcTrade(FtdcTrade trade) {
		return new FtdcRespMsg(FtdcRespType.FtdcTrade, trade);
	}

	public static final FtdcRespMsg withFtdcInputOrder(FtdcInputOrder ftdcInputOrder) {
		return new FtdcRespMsg(FtdcRespType.FtdcInputOrder, ftdcInputOrder);
	}

	public static final FtdcRespMsg withErrFtdcInputOrder(FtdcInputOrder ftdcInputOrder) {
		return new FtdcRespMsg(FtdcRespType.ErrFtdcInputOrder, ftdcInputOrder);
	}

	public static final FtdcRespMsg withFtdcInputOrderAction(FtdcInputOrderAction ftdcInputOrderAction) {
		return new FtdcRespMsg(FtdcRespType.FtdcInputOrderAction, ftdcInputOrderAction);
	}

	public static final FtdcRespMsg withErrFtdcOrderAction(FtdcOrderAction ftdcOrderAction) {
		return new FtdcRespMsg(FtdcRespType.ErrFtdcOrderAction, ftdcOrderAction);
	}

	public static enum FtdcRespType {

		FtdcDepthMarketData,

		RespConnectInfo,

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
