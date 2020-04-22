package io.mercury.ftdc.adaptor;

import io.mercury.common.param.ParamKey;
import io.mercury.common.param.ParamType;

public enum FtdcAdaptorParam implements ParamKey {

	/**
	 * CTP Params
	 */
	CTP_TraderAddr(201, "traderAddr", ParamType.STRING),

	CTP_MdAddr(202, "mdAddr", ParamType.STRING),

	CTP_AppId(203, "appId", ParamType.STRING),

	CTP_BrokerId(204, "brokerId", ParamType.STRING),

	CTP_InvestorId(205, "investorId", ParamType.STRING),

	CTP_AccountId(206, "accountId", ParamType.STRING),

	CTP_UserId(207, "userId", ParamType.STRING),

	CTP_Password(208, "password", ParamType.STRING),

	CTP_AuthCode(209, "authCode", ParamType.STRING),

	CTP_IpAddr(210, "ipAddr", ParamType.STRING),

	CTP_MacAddr(211, "macAddr", ParamType.STRING),

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
