package io.mercury.ftdc.adaptor;

import javax.annotation.Nonnull;

import io.mercury.ftdc.adaptor.consts.FtdcDirection;
import io.mercury.ftdc.adaptor.consts.FtdcOffsetFlag;
import io.mercury.ftdc.adaptor.consts.FtdcOrderStatusType;
import io.mercury.redstone.core.order.enums.OrdStatus;
import io.mercury.redstone.core.order.enums.TrdAction;
import io.mercury.redstone.core.order.enums.TrdDirection;

public final class FtdcConstMapper {

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

	public static final TrdAction fromOffsetFlag(@Nonnull String combOffsetFlag) {
		return fromOffsetFlag(combOffsetFlag.charAt(0));
	}

	public static final TrdAction fromOffsetFlag(@Nonnull char offsetFlag) {
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

	public static final TrdDirection fromDirection(char direction) {
		if (FtdcDirection.Buy == direction)
			return TrdDirection.Long;
		else if (FtdcDirection.Sell == direction)
			return TrdDirection.Short;
		else
			return TrdDirection.Invalid;
	}

}
