package io.cygnux.engine.actor;

import io.horizon.market.data.MarketData;
import io.horizon.market.handler.MarketDataHandler;
import io.mercury.actors.def.BaseActorT1;

public abstract class MarketDataActor<M extends MarketData> extends BaseActorT1<M> implements MarketDataHandler<M> {

	@Override
	protected void onEvent(M t) {
		onMarketData(t);
	}

	@Override
	protected void handleUnknown0(Object t) {
		log.info("", t);
	}

}
