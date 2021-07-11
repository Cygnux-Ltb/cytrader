package io.cygnus.engine.strategy.api;

import java.io.Closeable;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;

import io.horizon.market.data.MarketData;
import io.horizon.market.handler.MarketDataHandler;
import io.horizon.market.instrument.Instrument;
import io.horizon.trader.account.Account;
import io.horizon.trader.account.SubAccount;
import io.horizon.trader.adaptor.Adaptor;
import io.horizon.trader.handler.AdaptorEventHandler;
import io.horizon.trader.handler.OrderHandler;
import io.mercury.common.fsm.Enableable;

public interface Strategy<M extends MarketData> extends
		// 用于控制可用状态
		Enableable,
		// 用于确定优先级
		Comparable<Strategy<M>>,
		// 集成行情处理
		MarketDataHandler<M>,
		// 集成订单处理
		OrderHandler,
		// 集成AdaptorEvent处理
		AdaptorEventHandler,
		// 用于清理资源
		Closeable {

	int getStrategyId();

	String getStrategyName();

	@Nonnull
	SubAccount getSubAccount();

	@Nonnull
	Account getAccount();

	@Nonnull
	ImmutableIntObjectMap<Instrument> getInstruments();

	Strategy<M> initialize(@Nonnull Supplier<Boolean> initializer);

	Strategy<M> addAdaptor(@Nonnull Adaptor adaptor);

	void onStrategyEvent(@Nonnull StrategyEvent event);

	void onThrowable(Throwable throwable) throws StrategyException;

	@Override
	default int compareTo(Strategy<M> o) {
		return this.getStrategyId() < o.getStrategyId() ? -1 : this.getStrategyId() > o.getStrategyId() ? 1 : 0;
	}

}
