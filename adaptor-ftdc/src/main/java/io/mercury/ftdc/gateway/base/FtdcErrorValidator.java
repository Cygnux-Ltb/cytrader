package io.mercury.ftdc.gateway.base;

import org.slf4j.Logger;

import ctp.thostapi.CThostFtdcRspInfoField;
import io.mercury.common.log.CommonLoggerFactory;

public final class FtdcErrorValidator {

	private static final Logger log = CommonLoggerFactory.getLogger(FtdcErrorValidator.class);

	public static final boolean hasError(String spiFunctionName, CThostFtdcRspInfoField ftdcRspInfo) {
		if (ftdcRspInfo != null && ftdcRspInfo.getErrorID() != 0) {
			log.error("SPI ERROR -> {} : ErrorID == [{}], ErrorMsg == [{}]", spiFunctionName,
					ftdcRspInfo.getErrorID(), ftdcRspInfo.getErrorMsg());
			return true;
		} else {
			return false;
		}
		
	}

}
