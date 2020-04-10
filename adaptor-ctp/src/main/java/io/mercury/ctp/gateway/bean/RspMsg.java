package io.mercury.ctp.gateway.bean;

import ctp.thostapi.CThostFtdcInputOrderField;
import ctp.thostapi.CThostFtdcOrderActionField;
import io.mercury.ctp.gateway.bean.rsp.RspConnectInfo;
import io.mercury.ctp.gateway.bean.rsp.RspDepthMarketData;
import io.mercury.ctp.gateway.bean.rsp.RspOrderAction;
import io.mercury.ctp.gateway.bean.rsp.RspOrderInsert;
import io.mercury.ctp.gateway.bean.rsp.RtnOrder;
import io.mercury.ctp.gateway.bean.rsp.RtnTrade;

public final class RspMsg {

	private RspMsgType rspMsgType;
	private RspDepthMarketData rspDepthMarketData;

	private RspConnectInfo rspConnectInfo;

	private RtnOrder rtnOrder;
	private RtnTrade rtnTrade;

	private RspOrderInsert rspOrderInsert;
	private RspOrderAction rspOrderAction;

	private CThostFtdcInputOrderField errRtnOrderInsert;
	private CThostFtdcOrderActionField errRtnOrderAction;

	private RspMsg(RspMsgType rspMsgType) {
		this.rspMsgType = rspMsgType;
	}

	public RspMsgType getRspMsgType() {
		return rspMsgType;
	}

	public RspDepthMarketData getRspDepthMarketData() {
		return rspDepthMarketData;
	}

	public RspConnectInfo getRspConnectInfo() {
		return rspConnectInfo;
	}

	public RtnOrder getRtnOrder() {
		return rtnOrder;
	}

	public RtnTrade getRtnTrade() {
		return rtnTrade;
	}

	public RspOrderInsert getRspOrderInsert() {
		return rspOrderInsert;
	}

	public RspOrderAction getRspOrderAction() {
		return rspOrderAction;
	}

	public CThostFtdcInputOrderField getErrRtnOrderInsert() {
		return errRtnOrderInsert;
	}

	public CThostFtdcOrderActionField getErrRtnOrderAction() {
		return errRtnOrderAction;
	}

	public static final RspMsg ofDepthMarketData(RspDepthMarketData depthMarketData) {
		return new RspMsg(RspMsgType.DepthMarketData).setRspDepthMarketData(depthMarketData);
	}

	private RspMsg setRspDepthMarketData(RspDepthMarketData rspDepthMarketData) {
		this.rspDepthMarketData = rspDepthMarketData;
		return this;
	}

	public static final RspMsg ofRtnOrder(RtnOrder order) {
		return new RspMsg(RspMsgType.RtnOrder).setRtnOrder(order);
	}

	private RspMsg setRtnOrder(RtnOrder rtnOrder) {
		this.rtnOrder = rtnOrder;
		return this;
	}

	public static final RspMsg ofRtnTrade(RtnTrade trade) {
		return new RspMsg(RspMsgType.RtnTrade).setRtnTrade(trade);
	}

	private RspMsg setRtnTrade(RtnTrade rtnTrade) {
		this.rtnTrade = rtnTrade;
		return this;
	}

	public static final RspMsg ofRspOrderInsert(RspOrderInsert rspOrderInsert) {
		return new RspMsg(RspMsgType.RspOrderInsert).setRspOrderInsert(rspOrderInsert);
	}

	private RspMsg setRspOrderInsert(RspOrderInsert rspOrderInsert) {
		this.rspOrderInsert = rspOrderInsert;
		return this;
	}

	public static final RspMsg ofRspOrderAction(RspOrderAction rspOrderAction) {
		return new RspMsg(RspMsgType.RspOrderInsert).setRspOrderAction(rspOrderAction);
	}

	private RspMsg setRspOrderAction(RspOrderAction rspOrderAction) {
		this.rspOrderAction = rspOrderAction;
		return this;
	}

	public static final RspMsg ofErrRtnOrderInsert(CThostFtdcInputOrderField errRtnOrderInsert) {
		return new RspMsg(RspMsgType.ErrRtnOrderInsert).setErrRtnOrderInsert(errRtnOrderInsert);
	}

	private RspMsg setErrRtnOrderInsert(CThostFtdcInputOrderField errRtnOrderInsert) {
		this.errRtnOrderInsert = errRtnOrderInsert;
		return this;
	}

	public static final RspMsg ofErrRtnOrderAction(CThostFtdcOrderActionField errRtnOrderAction) {
		return new RspMsg(RspMsgType.ErrRtnOrderAction).setErrRtnOrderAction(errRtnOrderAction);
	}

	public RspMsg setErrRtnOrderAction(CThostFtdcOrderActionField errRtnOrderAction) {
		this.errRtnOrderAction = errRtnOrderAction;
		return this;
	}

	public static enum RspMsgType {

		DepthMarketData,

		RspConnectInfo,

		RtnOrder,

		RtnTrade,

		RspOrderInsert,

		RspOrderAction,

		ErrRtnOrderInsert,

		ErrRtnOrderAction,

		Other,

		;

	}

}
