package io.mercury.ftdc.adaptor;

import javax.annotation.Nonnull;

import io.mercury.ftdc.adaptor.consts.FtdcDirection;
import io.mercury.ftdc.adaptor.consts.FtdcOffsetFlag;
import io.mercury.ftdc.adaptor.consts.FtdcOrderStatusType;
import io.mercury.redstone.core.order.enums.OrdStatus;
import io.mercury.redstone.core.order.enums.TrdAction;
import io.mercury.redstone.core.order.enums.TrdDirection;

public final class FtdcConstMapper {

	/**
	 * 根据<b> [FTDC返回] </b>订单状态, 映射<b> [系统自定义] </b>订单状态
	 * 
	 * @param orderStatus
	 * @return
	 */
	public static final OrdStatus fromOrderStatus(char orderStatus) {
		if (FtdcOrderStatusType.NoTradeNotQueueing == orderStatus || FtdcOrderStatusType.NoTradeQueueing == orderStatus)
			return OrdStatus.New;
		else if (FtdcOrderStatusType.PartTradedNotQueueing == orderStatus
				|| FtdcOrderStatusType.PartTradedQueueing == orderStatus)
			return OrdStatus.PartiallyFilled;
		else if (FtdcOrderStatusType.AllTraded == orderStatus)
			return OrdStatus.Filled;
		else if (FtdcOrderStatusType.Canceled == orderStatus)
			return OrdStatus.Canceled;
		else
			return OrdStatus.Invalid;

	}

	/**
	 * 根据<b> [FTDC返回] </b>开平仓类型, 映射<b> [系统自定义] </b>开平仓类型
	 * 
	 * @param combOffsetFlag
	 * @return
	 */
	public static final TrdAction fromOffsetFlag(@Nonnull String combOffsetFlag) {
		return fromOffsetFlag(combOffsetFlag.charAt(0));
	}

	/**
	 * 根据<b> [FTDC返回] </b>开平仓类型, 映射<b> [系统自定义] </b>开平仓类型
	 * 
	 * @param offsetFlag
	 * @return
	 */
	public static final TrdAction fromOffsetFlag(char offsetFlag) {
		if (FtdcOffsetFlag.Open == offsetFlag)
			return TrdAction.Open;
		else if (FtdcOffsetFlag.Close == offsetFlag)
			return TrdAction.Close;
		else if (FtdcOffsetFlag.CloseToday == offsetFlag)
			return TrdAction.CloseToday;
		else if (FtdcOffsetFlag.CloseYesterday == offsetFlag)
			return TrdAction.CloseYesterday;
		else
			return TrdAction.Invalid;
	}

	/**
	 * 根据<b> [FTDC返回] </b>买卖方向类型, 映射<b> [系统自定义] </b>买卖方向类型类型
	 * @param direction
	 * @return
	 */
	public static final TrdDirection fromDirection(char direction) {
		if (FtdcDirection.Buy == direction)
			return TrdDirection.Long;
		else if (FtdcDirection.Sell == direction)
			return TrdDirection.Short;
		else
			return TrdDirection.Invalid;
	}

}
