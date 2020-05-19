package io.mercury.ftdc.adaptor;

import ctp.thostapi.thosttraderapiConstants;

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

}
