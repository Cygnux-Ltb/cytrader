package io.mercury.ftdc.adaptor;

import javax.annotation.Nonnull;

import io.mercury.ftdc.adaptor.consts.FtdcCombOffsetFlag;
import io.mercury.ftdc.adaptor.consts.FtdcDirection;
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

	public static final TrdAction fromCombOffsetFlag(@Nonnull String combOffsetFlag) {
		char charAt = combOffsetFlag.charAt(0);
		if (FtdcCombOffsetFlag.Open == charAt)
			return TrdAction.Open;
		else if (FtdcCombOffsetFlag.Close == charAt)
			return TrdAction.Close;
		else if (FtdcCombOffsetFlag.CloseToday == charAt)
			return TrdAction.CloseToday;
		else if (FtdcCombOffsetFlag.CloseYesterday == charAt)
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
