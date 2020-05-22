package io.mercury.ftdc.adaptor.consts;

import ctp.thostapi.thosttraderapiConstants;

/**
 * ///TFtdcOrderStatusType是一个报单状态类型<br>
 * <br>
 * ///全部成交<br>
 * #define THOST_FTDC_OST_AllTraded '0'<br>
 * <br>
 * ///部分成交还在队列中<br>
 * #define THOST_FTDC_OST_PartTradedQueueing '1'<br>
 * <br>
 * ///部分成交不在队列中<br>
 * #define THOST_FTDC_OST_PartTradedNotQueueing '2'<br>
 * <br>
 * ///未成交还在队列中<br>
 * #define THOST_FTDC_OST_NoTradeQueueing '3'<br>
 * <br>
 * ///未成交不在队列中<br>
 * #define THOST_FTDC_OST_NoTradeNotQueueing '4'<br>
 * <br>
 * ///撤单<br>
 * #define THOST_FTDC_OST_Canceled '5'<br>
 * <br>
 * ///未知<br>
 * #define THOST_FTDC_OST_Unknown 'a'<br>
 * <br>
 * ///尚未触发<br>
 * #define THOST_FTDC_OST_NotTouched 'b'<br>
 * <br>
 * ///已触发<br>
 * #define THOST_FTDC_OST_Touched 'c'<br>
 * 
 * @author yellow013
 *
 */
public final class FtdcOrderStatusType {

	/**
	 * 全部成交
	 */
	public static final char AllTraded = thosttraderapiConstants.THOST_FTDC_OST_AllTraded;
	/**
	 * 部分成交还在队列中
	 */
	public static final char PartTradedQueueing = thosttraderapiConstants.THOST_FTDC_OST_PartTradedQueueing;
	/**
	 * 部分成交不在队列中
	 */
	public static final char PartTradedNotQueueing = thosttraderapiConstants.THOST_FTDC_OST_PartTradedNotQueueing;
	/**
	 * 未成交还在队列中
	 */
	public static final char NoTradeQueueing = thosttraderapiConstants.THOST_FTDC_OST_NoTradeQueueing;
	/**
	 * 未成交不在队列中
	 */
	public static final char NoTradeNotQueueing = thosttraderapiConstants.THOST_FTDC_OST_NoTradeNotQueueing;
	/**
	 * 撤单
	 */
	public static final char Canceled = thosttraderapiConstants.THOST_FTDC_OST_Canceled;
	/**
	 * 未知
	 */
	public static final char Unknown = thosttraderapiConstants.THOST_FTDC_OST_Unknown;
	/**
	 * 尚未触发
	 */
	public static final char NotTouched = thosttraderapiConstants.THOST_FTDC_OST_NotTouched;
	/**
	 * 已触发
	 */
	public static final char Touched = thosttraderapiConstants.THOST_FTDC_OST_Touched;

}
