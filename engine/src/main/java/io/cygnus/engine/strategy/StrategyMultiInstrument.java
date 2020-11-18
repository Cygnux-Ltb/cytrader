package io.apollo.engine.strategy;

import java.util.Set;

import org.eclipse.collections.api.list.ImmutableList;

import io.horizon.definition.adaptor.AdaptorEvent;
import io.horizon.definition.market.data.MarketData;
import io.horizon.definition.market.instrument.Instrument;
import io.mercury.common.collections.ImmutableLists;
import io.mercury.common.param.ImmutableParams;
import io.mercury.common.param.ParamKey;

public abstract class StrategyMultiInstrument<M extends MarketData, PK extends ParamKey>
		extends StrategyBaseImpl<M, PK> {

	// 策略订阅的合约
	protected ImmutableList<Instrument> instruments;

	protected StrategyMultiInstrument(int strategyId, int subAccountId, Set<Instrument> instruments,
			ImmutableParams<PK> params) {
		super(strategyId, subAccountId, params);
		this.instruments = ImmutableLists.newImmutableList(instruments);
	}

	@Override
	public void onAdaptorEvent(AdaptorEvent event) {
		// TODO Auto-generated method stub
	}

}
