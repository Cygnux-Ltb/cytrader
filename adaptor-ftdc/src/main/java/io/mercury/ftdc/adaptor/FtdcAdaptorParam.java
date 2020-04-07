package io.mercury.ftdc.adaptor;

import io.mercury.common.param.api.ParamKey;
import io.mercury.common.param.api.ParamType;

public enum FtdcAdaptorParam implements ParamKey {

	/**
	 * CTP Params
	 */
	CTP_TraderAddr(201, "ctp.trader.addr", ParamType.STRING),

	CTP_MdAddr(202, "ctp.md.addr", ParamType.STRING),

	CTP_BrokerId(203, "ctp.broker.id", ParamType.STRING),

	CTP_InvestorId(204, "ctp.investor.id", ParamType.STRING),

	CTP_UserId(205, "ctp.user.id", ParamType.STRING),

	CTP_AccountId(206, "ctp.account.id", ParamType.STRING),

	CTP_Password(207, "ctp.password", ParamType.STRING),

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
