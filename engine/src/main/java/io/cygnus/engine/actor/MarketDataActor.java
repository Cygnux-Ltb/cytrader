package io.cygnus.engine.actor;

import io.horizon.market.api.MarketData;
import io.horizon.market.handler.MarketDataHandler;
import io.mercury.actors.CommonActorT1;

public abstract class MarketDataActor<M extends MarketData> extends CommonActorT1<M> implements MarketDataHandler<M> {

	@Override
	protected void onEvent(M t) {
		onMarketData(t);
	}

	@Override
	protected void handleUnknown0(Object t) {
		log.info("", t);
	}

}
