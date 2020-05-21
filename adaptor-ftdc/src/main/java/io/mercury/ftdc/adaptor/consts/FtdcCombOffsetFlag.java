package io.mercury.ftdc.adaptor.consts;

import ctp.thostapi.thosttraderapiConstants;
import io.mercury.common.util.StringUtil;

/**
 * /////////////////////////////////////////////////////////////////////////
 * ///TFtdcOffsetFlagType是一个开平标志类型
 * /////////////////////////////////////////////////////////////////////////
 * ///开仓<br>
 * #define THOST_FTDC_OF_Open '0' <br>
 * ///平仓<br>
 * #define THOST_FTDC_OF_Close '1'<br>
 * ///强平<br>
 * #define THOST_FTDC_OF_ForceClose '2' <br>
 * ///平今<br>
 * #define THOST_FTDC_OF_CloseToday '3'<br>
 * ///平昨<br>
 * #define THOST_FTDC_OF_CloseYesterday '4' <br>
 * ///强减<br>
 * #define THOST_FTDC_OF_ForceOff '5'<br>
 * ///本地强平<br>
 * #define THOST_FTDC_OF_LocalForceClose '6'
 */
public interface FtdcCombOffsetFlag {
	
	/**
	 * 组合开平标识, 开仓, [char]
	 */
	char Open = thosttraderapiConstants.THOST_FTDC_OF_Open;
	/**
	 * 组合开平标识, 开仓, [String]
	 */
	String OpenStr = StringUtil.toString(thosttraderapiConstants.THOST_FTDC_OF_Open);

	/**
	 * 组合开平标识, 平仓, [char]
	 */
	char Close = thosttraderapiConstants.THOST_FTDC_OF_Close;
	/**
	 * 组合开平标识, 平仓, [String]
	 */
	String CloseStr = StringUtil.toString(thosttraderapiConstants.THOST_FTDC_OF_Close);

	/**
	 * 组合开平标识, 平今, [char]
	 */
	char CloseToday = thosttraderapiConstants.THOST_FTDC_OF_CloseToday;
	/**
	 * 组合开平标识, 平今, [String]
	 */
	String CloseTodayStr = StringUtil.toString(thosttraderapiConstants.THOST_FTDC_OF_CloseToday);

	/**
	 * 组合开平标识, 平昨, [char]
	 */
	char CloseYesterday = thosttraderapiConstants.THOST_FTDC_OF_CloseYesterday;
	/**
	 * 组合开平标识, 平昨, [String]
	 */
	String CloseYesterdayStr = StringUtil.toString(thosttraderapiConstants.THOST_FTDC_OF_CloseYesterday);


}
