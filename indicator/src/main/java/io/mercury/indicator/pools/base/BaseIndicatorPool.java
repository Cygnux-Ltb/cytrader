package io.mercury.indicator.pools.base;

import org.eclipse.collections.api.list.MutableList;
import org.slf4j.Logger;

import io.mercury.common.collections.MutableLists;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.indicator.base.BaseIndicator;
import io.mercury.polaris.financial.market.impl.BasicMarketData;

public abstract class BaseIndicatorPool<I extends BaseIndicator<?, ?>> {

	protected Logger logger = CommonLoggerFactory.getLogger(getClass());

	protected MutableList<I> indicators = MutableLists.newFastList();

	public void onMarketDate(BasicMarketData marketData) {
		indicators.forEach(indicator -> indicator.onMarketData(marketData));
	}

}
