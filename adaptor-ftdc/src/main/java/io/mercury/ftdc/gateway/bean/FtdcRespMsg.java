package io.mercury.ctp.gateway.bean;

import io.mercury.ctp.gateway.bean.rsp.RspConnectInfo;

public final class RspMsg {

	private FtdcMsgType ftdcMsgType;
	private FtdcDepthMarketData ftdcDepthMarketData;

	private RspConnectInfo rspConnectInfo;

	private FtdcOrder ftdcOrder;
	private FtdcTrade ftdcTrade;

	private FtdcInputOrder ftdcInputOrder;
	private FtdcInputOrderAction ftdcInputOrderAction;

	private RspMsg(FtdcMsgType ftdcMsgType, FtdcDepthMarketData ftdcDepthMarketData) {
		this.ftdcMsgType = ftdcMsgType;
		this.ftdcDepthMarketData = ftdcDepthMarketData;
	}

	private RspMsg(FtdcMsgType ftdcMsgType, FtdcOrder ftdcOrder) {
		this.ftdcMsgType = ftdcMsgType;
		this.ftdcOrder = ftdcOrder;
	}

	private RspMsg(FtdcMsgType ftdcMsgType, FtdcTrade ftdcTrade) {
		this.ftdcMsgType = ftdcMsgType;
		this.ftdcTrade = ftdcTrade;
	}

	private RspMsg(FtdcMsgType ftdcMsgType, FtdcInputOrder ftdcInputOrder) {
		this.ftdcMsgType = ftdcMsgType;
		this.ftdcInputOrder = ftdcInputOrder;
	}

	private RspMsg(FtdcMsgType ftdcMsgType, FtdcInputOrderAction ftdcInputOrderAction) {
		this.ftdcMsgType = ftdcMsgType;
		this.ftdcInputOrderAction = ftdcInputOrderAction;
	}

	public FtdcMsgType getFtdcMsgType() {
		return ftdcMsgType;
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

	public static final RspMsg withDepthMarketData(FtdcDepthMarketData depthMarketData) {
		return new RspMsg(FtdcMsgType.FtdcDepthMarketData, depthMarketData);
	}

	public static final RspMsg withFtdcOrder(FtdcOrder order) {
		return new RspMsg(FtdcMsgType.FtdcOrder, order);
	}

	public static final RspMsg withFtdcTrade(FtdcTrade trade) {
		return new RspMsg(FtdcMsgType.FtdcTrade, trade);
	}

	public static final RspMsg withFtdcInputOrder(FtdcInputOrder ftdcInputOrder) {
		return new RspMsg(FtdcMsgType.FtdcInputOrder, ftdcInputOrder);
	}

	public static final RspMsg withErrFtdcInputOrder(FtdcInputOrder ftdcInputOrder) {
		return new RspMsg(FtdcMsgType.ErrFtdcInputOrder, ftdcInputOrder);
	}

	public static final RspMsg withFtdcInputOrderAction(FtdcInputOrderAction ftdcInputOrderAction) {
		return new RspMsg(FtdcMsgType.FtdcInputOrderAction, ftdcInputOrderAction);
	}

	public static final RspMsg withErrFtdcInputOrderAction(FtdcInputOrderAction ftdcInputOrderAction) {
		return new RspMsg(FtdcMsgType.ErrFtdcInputOrderAction, ftdcInputOrderAction);
	}

	public static enum FtdcMsgType {

		FtdcDepthMarketData,

		RspConnectInfo,

		FtdcOrder,

		FtdcTrade,

		FtdcInputOrder,

		ErrFtdcInputOrder,

		FtdcInputOrderAction,

		ErrFtdcInputOrderAction,

		Other,

		;

	}

}
