package io.mercury.redstone.core.strategy;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import org.eclipse.collections.api.list.ImmutableList;

import io.mercury.common.fsm.Enable;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.api.MarketData;
import io.mercury.redstone.core.account.Account;
import io.mercury.redstone.core.account.SubAccount;
import io.mercury.redstone.core.adaptor.Adaptor;
import io.mercury.redstone.core.event.AdaptorEventHandler;
import io.mercury.redstone.core.event.MarketDataHandler;
import io.mercury.redstone.core.event.OrderHandler;

public interface Strategy<M extends MarketData> extends
		// 用于控制可用状态
		Enable<Strategy<M>>,
		// 用于确定优先级
		Comparable<Strategy<M>>,
		// 集成行情处理
		MarketDataHandler<M>,
		// 集成订单处理
		OrderHandler,
		// 集成Adaptor处理
		AdaptorEventHandler {

	public static interface StrategyIdConst {
		int MaxStrategyId = 899;
		int ExternalStrategyId = 910;
	}

	int strategyId();

	String strategyName();

	SubAccount getSubAccount();

	Account getAccount();

	ImmutableList<Instrument> instruments();

	void initialize(@Nonnull Supplier<Boolean> initializer);

	void addAdaptor(@Nonnull Adaptor adaptor);

	void onStrategyEvent(StrategyEvent event);

	void onThrowable(Throwable throwable) throws StrategyException;

	@Override
	default int compareTo(Strategy<M> o) {
		return this.strategyId() < o.strategyId() ? -1 : this.strategyId() > o.strategyId() ? 1 : 0;
	}

}
