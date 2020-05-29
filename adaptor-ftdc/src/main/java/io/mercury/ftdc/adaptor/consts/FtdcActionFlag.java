package io.mercury.ftdc.adaptor.consts;

import ctp.thostapi.thosttraderapiConstants;

/**
 * 
 * ///TFtdcActionFlagType是一个操作标志类型<br>
 * <br>
 * ///删除<br>
 * #define THOST_FTDC_AF_Delete '0'<br>
 * <br>
 * ///修改<br>
 * #define THOST_FTDC_AF_Modify '3'<br>
 *
 */
public interface FtdcActionFlag {

	char Delete = thosttraderapiConstants.THOST_FTDC_AF_Delete;

	char Modify = thosttraderapiConstants.THOST_FTDC_AF_Modify;

}
