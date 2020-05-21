package io.mercury.ftdc.adaptor;

import ctp.thostapi.thosttraderapiConstants;
import io.mercury.common.util.StringUtil;

public interface FtdcConst {

	/// 全部成交
	char FTDC_OST_AllTraded = thosttraderapiConstants.THOST_FTDC_OST_AllTraded;
	/// 部分成交还在队列中
	char OST_PartTradedQueueing = thosttraderapiConstants.THOST_FTDC_OST_PartTradedQueueing;
	/// 部分成交不在队列中
	char OST_PartTradedNotQueueing = thosttraderapiConstants.THOST_FTDC_OST_PartTradedNotQueueing;
	/// 未成交还在队列中
	char OST_NoTradeQueueing = thosttraderapiConstants.THOST_FTDC_OST_NoTradeQueueing;
	/// 未成交不在队列中
	char OST_NoTradeNotQueueing = thosttraderapiConstants.THOST_FTDC_OST_NoTradeNotQueueing;
	/// 撤单
	char OST_Canceled = thosttraderapiConstants.THOST_FTDC_OST_Canceled;
	/// 未知
	char OST_Unknown = thosttraderapiConstants.THOST_FTDC_OST_Unknown;
	/// 尚未触发
	char OST_NotTouched = thosttraderapiConstants.THOST_FTDC_OST_NotTouched;
	/// 已触发
	char OST_Touched = thosttraderapiConstants.THOST_FTDC_OST_Touched;
	
	
	
	

	
	
	
	char TimeCondition = thosttraderapiConstants.THOST_FTDC_TC_GFD;

	/**
	 * 组合开平标识, 开仓, [char]
	 */
	char CombOffsetFlagOpen = thosttraderapiConstants.THOST_FTDC_OF_Open;
	/**
	 * 组合开平标识, 开仓, [String]
	 */
	String CombOffsetFlagOpenStr = StringUtil.toString(thosttraderapiConstants.THOST_FTDC_OF_Open);

	/**
	 * 组合开平标识, 平仓, [char]
	 */
	char CombOffsetFlagClose = thosttraderapiConstants.THOST_FTDC_OF_Close;
	/**
	 * 组合开平标识, 平仓, [String]
	 */
	String CombOffsetFlagCloseStr = StringUtil.toString(thosttraderapiConstants.THOST_FTDC_OF_Close);

	/**
	 * 组合开平标识, 平今, [char]
	 */
	char CombOffsetFlagCloseToday = thosttraderapiConstants.THOST_FTDC_OF_CloseToday;
	/**
	 * 组合开平标识, 平今, [String]
	 */
	String CombOffsetFlagCloseTodayStr = StringUtil.toString(thosttraderapiConstants.THOST_FTDC_OF_CloseToday);

	/**
	 * 组合开平标识, 平昨, [char]
	 */
	char CombOffsetFlagCloseYesterday = thosttraderapiConstants.THOST_FTDC_OF_CloseYesterday;
	/**
	 * 组合开平标识, 平昨, [String]
	 */
	String CombOffsetFlagCloseYesterdayStr = StringUtil.toString(thosttraderapiConstants.THOST_FTDC_OF_CloseYesterday);

	/**
	 * 组合投机套保标识, 投机, [char]
	 */
	char CombHedgeFlagSpeculation = thosttraderapiConstants.THOST_FTDC_HF_Speculation;
	/**
	 * 组合投机套保标识, 投机, [String]
	 */
	String CombHedgeFlagSpeculationStr = StringUtil.toString(thosttraderapiConstants.THOST_FTDC_HF_Speculation);

}
