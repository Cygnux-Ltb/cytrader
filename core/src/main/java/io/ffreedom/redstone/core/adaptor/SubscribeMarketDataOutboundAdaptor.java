package io.ffreedom.redstone.core.adaptor;

import io.ffreedom.redstone.core.adaptor.dto.SubscribeMarketData;

public interface SubscribeMarketDataOutboundAdaptor extends OutboundAdaptor {

	boolean subscribeMarketData(SubscribeMarketData subscribeMarketData);

}
