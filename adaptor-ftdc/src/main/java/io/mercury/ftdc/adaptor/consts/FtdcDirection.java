package io.mercury.ftdc.adaptor.consts;

import ctp.thostapi.thosttraderapiConstants;

/**
 * TFtdcDirectionType是一个买卖方向类型 <br>
 * <br>
 * ///买<br>
 * #define THOST_FTDC_D_Buy '0'<br>
 * <br>
 * ///卖<br>
 * #define THOST_FTDC_D_Sell '1'<br>
 */
public interface FtdcDirection {

	/**
	 * 买
	 */
	char Buy = thosttraderapiConstants.THOST_FTDC_D_Buy;

	/**
	 * 卖
	 */
	char Sell = thosttraderapiConstants.THOST_FTDC_D_Sell;

}
