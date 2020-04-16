package io.mercury.ftdc.adaptor;

import io.mercury.common.param.api.ParamKey;
import io.mercury.common.param.api.ParamType;

public enum FtdcAdaptorParam implements ParamKey {

	/**
	 * CTP Params
	 */
	CTP_TraderAddr(201, "trader.addr", ParamType.STRING),

	CTP_MdAddr(202, "md.addr", ParamType.STRING),

	CTP_AppId(203, "app.id", ParamType.STRING),

	CTP_BrokerId(204, "broker.id", ParamType.STRING),

	CTP_InvestorId(205, "investor.id", ParamType.STRING),

	CTP_AccountId(206, "account.id", ParamType.STRING),

	CTP_UserId(207, "user.id", ParamType.STRING),

	CTP_Password(208, "password", ParamType.STRING),

	CTP_AuthCode(209, "auth.code", ParamType.STRING),

	CTP_IpAddr(210, "ip.addr", ParamType.STRING),

	CTP_MacAddr(211, "mac.addr", ParamType.STRING),

	;

	private int paramId;
	private String paramName;
	private ParamType paramType;

	private FtdcAdaptorParam(int paramId, String paramName, ParamType paramType) {
		this.paramId = paramId;
		this.paramName = paramName;
		this.paramType = paramType;
	}

	@Override
	public int paramId() {
		return paramId;
	}

	@Override
	public ParamType paramType() {
		return paramType;
	}

	@Override
	public String paramName() {
		return paramName;
	}

}
