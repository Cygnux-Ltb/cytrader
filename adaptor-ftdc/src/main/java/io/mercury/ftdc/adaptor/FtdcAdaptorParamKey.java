package io.mercury.ftdc.adaptor;

import io.mercury.common.param.AdaptorParamKey;
import io.mercury.common.param.ParamType;

public enum FtdcAdaptorParamKey implements AdaptorParamKey {

	/**
	 * CTP Params
	 */
	TraderAddr(101, "traderAddr", ParamType.STRING),

	MdAddr(102, "mdAddr", ParamType.STRING),

	AppId(103, "appId", ParamType.STRING),

	BrokerId(104, "brokerId", ParamType.STRING),

	InvestorId(105, "investorId", ParamType.STRING),

	AccountId(106, "accountId", ParamType.STRING),

	UserId(107, "userId", ParamType.STRING),

	Password(108, "password", ParamType.STRING),

	AuthCode(109, "authCode", ParamType.STRING),

	IpAddr(110, "ipAddr", ParamType.STRING),

	MacAddr(111, "macAddr", ParamType.STRING),

	CurrencyId(112, "currencyId", ParamType.STRING),

	;

	private int id;
	private String key;
	private ParamType type;

	private FtdcAdaptorParamKey(int id, String key, ParamType type) {
		this.id = id;
		this.key = key;
		this.type = type;
	}

	@Override
	public int id() {
		return id;
	}

	@Override
	public String key() {
		return key;
	}

	@Override
	public ParamType type() {
		return type;
	}

	@Override
	public String adaptorName() {
		return "FtdcAdaptor";
	}

}
