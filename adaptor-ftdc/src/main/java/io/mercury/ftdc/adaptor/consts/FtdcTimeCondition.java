package io.mercury.ftdc.adaptor.consts;

import ctp.thostapi.thosttraderapiConstants;

/**
 * ///TFtdcTimeConditionType是一个有效期类型类型<br>
 * <br>
 * ///立即完成，否则撤销<br>
 * #define THOST_FTDC_TC_IOC '1'<br>
 * <br>
 * ///本节有效<br>
 * #define THOST_FTDC_TC_GFS '2'<br>
 * <br>
 * ///当日有效<br>
 * #define THOST_FTDC_TC_GFD '3'<br>
 * <br>
 * ///指定日期前有效<br>
 * #define THOST_FTDC_TC_GTD '4'<br>
 * <br>
 * ///撤销前有效<br>
 * #define THOST_FTDC_TC_GTC '5'<br>
 * <br>
 * ///集合竞价有效<br>
 * #define THOST_FTDC_TC_GFA '6'<br>
 */
public interface FtdcTimeCondition {

	/**
	 * 立即完成, 否则撤销
	 */
	char IOC = thosttraderapiConstants.THOST_FTDC_TC_IOC;

	/**
	 * 当日有效
	 */
	char GFD = thosttraderapiConstants.THOST_FTDC_TC_GFD;

}
