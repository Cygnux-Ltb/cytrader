package io.redstone.adaptor.simulator.converter;

import io.ffreedom.common.functional.Converter;
import io.polaris.financial.market.impl.BasicMarketData;
import io.redstone.persistence.avro.entity.MarketDataLevel1;

public class MarketDataConverter implements Converter<MarketDataLevel1, BasicMarketData>{

	@Override
	public BasicMarketData convert(MarketDataLevel1 from) {
		// TODO Auto-generated method stub
		return null;
	}

}
