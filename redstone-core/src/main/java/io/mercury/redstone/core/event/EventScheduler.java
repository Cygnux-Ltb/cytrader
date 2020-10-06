package io.mercury.redstone.core.event;

import io.mercury.financial.market.api.MarketData;
import io.mercury.redstone.core.adaptor.AdaptorEvent;
import io.mercury.redstone.core.order.structure.OrdReport;

public interface EventScheduler<M extends MarketData> {

	void onMarketData(M marketData);

	void onOrdReport(OrdReport report);

	void onAdaptorEvent(AdaptorEvent event);

}
