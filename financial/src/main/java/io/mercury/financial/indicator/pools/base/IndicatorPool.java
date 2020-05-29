package io.mercury.financial.indicator.pools.base;

import org.eclipse.collections.api.list.MutableList;
import org.slf4j.Logger;

import io.mercury.common.collections.MutableLists;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.financial.indicator.api.Indicator;
import io.mercury.financial.market.impl.BasicMarketData;

public abstract class IndicatorPool<I extends Indicator<?, ?>> {

	protected Logger log = CommonLoggerFactory.getLogger(getClass());

	protected MutableList<I> indicators = MutableLists.newFastList();

	public void onMarketDate(BasicMarketData marketData) {
		indicators.forEach(indicator -> indicator.onMarketData(marketData));
	}

}
