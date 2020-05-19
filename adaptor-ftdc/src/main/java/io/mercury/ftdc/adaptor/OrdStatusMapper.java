package io.mercury.ftdc.adaptor;

import ctp.thostapi.thosttraderapiConstants;
import io.mercury.redstone.core.order.enums.OrdStatus;

public final class OrdStatusMapper {
	
	private static final char FTDC_OST_AllTraded = thosttraderapiConstants.THOST_FTDC_OST_AllTraded;

	public static final OrdStatus ofFtdcOrderStatus(char ftdcOrderStatus) {
		
		switch (ftdcOrderStatus) {

		/// 全部成交
		case FTDC_OST_AllTraded:
			break;
			/// 部分成交还在队列中
		case thosttraderapiConstants.THOST_FTDC_OST_PartTradedQueueing:
			/// 部分成交不在队列中
		case thosttraderapiConstants.THOST_FTDC_OST_PartTradedNotQueueing:
			/// 未成交还在队列中
		case thosttraderapiConstants.THOST_FTDC_OST_NoTradeQueueing:
			/// 未成交不在队列中
		case thosttraderapiConstants.THOST_FTDC_OST_NoTradeNotQueueing:
			/// 撤单
		case thosttraderapiConstants.THOST_FTDC_OST_Canceled:
			/// 未知
		case thosttraderapiConstants.THOST_FTDC_OST_Unknown:
			/// 尚未触发
		case thosttraderapiConstants.THOST_FTDC_OST_NotTouched:
			/// 已触发
		case thosttraderapiConstants.THOST_FTDC_OST_Touched:
			
			break;
		default:
			break;
		}

		return OrdStatus.Canceled;

	}
	
	

}
