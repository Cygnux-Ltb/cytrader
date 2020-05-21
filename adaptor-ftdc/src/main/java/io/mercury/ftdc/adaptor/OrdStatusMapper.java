package io.mercury.ftdc.adaptor;

import static io.mercury.ftdc.adaptor.consts.FtdcOrderStatusType.AllTraded;
import static io.mercury.ftdc.adaptor.consts.FtdcOrderStatusType.Canceled;
import static io.mercury.ftdc.adaptor.consts.FtdcOrderStatusType.NoTradeNotQueueing;
import static io.mercury.ftdc.adaptor.consts.FtdcOrderStatusType.NoTradeQueueing;
import static io.mercury.ftdc.adaptor.consts.FtdcOrderStatusType.PartTradedNotQueueing;
import static io.mercury.ftdc.adaptor.consts.FtdcOrderStatusType.PartTradedQueueing;

import io.mercury.redstone.core.order.enums.OrdStatus;

public final class OrdStatusMapper {

	public static final OrdStatus ofFtdcOrderStatus(int ftdcOrderStatus) {
		if (NoTradeNotQueueing == ftdcOrderStatus || NoTradeQueueing == ftdcOrderStatus) {
			return OrdStatus.New;
		} else if (PartTradedNotQueueing == ftdcOrderStatus || PartTradedQueueing == ftdcOrderStatus) {
			return OrdStatus.PartiallyFilled;
		} else if (AllTraded == ftdcOrderStatus) {
			return OrdStatus.Filled;
		} else if (Canceled == ftdcOrderStatus) {
			return OrdStatus.Canceled;
		} else {
			return OrdStatus.Invalid;
		}
	}

}
