package io.mercury.ctp.gateway.base;

import ctp.thostapi.CThostFtdcRspInfoField;

public final class CtpRspValidator {

	public static final void validateRspInfo(String spiMethodName, CThostFtdcRspInfoField RspInfo) {
		if (RspInfo != null && RspInfo.getErrorID() != 0)
			throw new CtpRspException(spiMethodName, RspInfo.getErrorID(), RspInfo.getErrorMsg());
	}

}
