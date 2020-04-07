package io.redstone.core.strategy.base;

import java.util.function.Supplier;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.slf4j.Logger;

import io.mercury.common.annotation.lang.ProtectedAbstractMethod;
import io.mercury.common.collections.ImmutableLists;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.util.StringUtil;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.instrument.InstrumentKeeper;
import io.mercury.financial.market.LastMarkerDataKeeper;
import io.mercury.financial.market.LastMarkerDataKeeper.LastMarkerData;
import io.mercury.financial.market.api.MarketData;
import io.mercury.financial.market.impl.BasicMarketData;
import io.redstone.core.account.AccountKeeper;
import io.redstone.core.adaptor.Adaptor;
import io.redstone.core.order.Order;
import io.redstone.core.order.OrderExecutor;
import io.redstone.core.order.enums.OrdType;
import io.redstone.core.order.enums.TrdDirection;
import io.redstone.core.order.specific.ParentOrder;
import io.redstone.core.order.specific.StrategyOrder;
import io.redstone.core.order.structure.OrdPrice;
import io.redstone.core.order.structure.OrdQty;
import io.redstone.core.risk.CircuitBreaker;
import io.redstone.core.strategy.Strategy;
import io.redstone.core.strategy.StrategyEvent;

public abstract class BaseStrategy<M extends MarketData> implements Strategy, CircuitBreaker {

	private int strategyId;

	private int subAccountId;

	private boolean isInitSuccess = false;

	protected Logger log = CommonLoggerFactory.getLogger(getClass());

	protected String strategyName;

	private String defaultName;

	// 策略订阅的合约
	private ImmutableList<Instrument> instruments;

	// 记录当前策略所有的策略订单订单
	protected MutableLongObjectMap<StrategyOrder> strategyOrders = MutableMaps.newLongObjectHashMap();

	protected BaseStrategy(int strategyId, int subAccountId, Instrument... instruments) {
		this.strategyId = strategyId;
		this.subAccountId = subAccountId;
		this.defaultName = "strategyId[" + strategyId + "]subAccountId[" + subAccountId + "]";
		this.instruments = ImmutableLists.newList(instruments);
	}

	@Override
	public void initialize(Supplier<Boolean> initializer) {
		if (initializer != null)
			isInitSuccess = initializer.get();
		else
			log.error("Initializer is null.");
		log.info("Initialize result isInitSuccess==[{}]", isInitSuccess);
	}

	@Override
	public int strategyId() {
		return strategyId;
	}

	@Override
	public String strategyName() {
		if (StringUtil.isNullOrEmpty(strategyName))
			return defaultName;
		return strategyName;
	}

	@Override
	public int subAccountId() {
		return subAccountId;
	}

	@Override
	public void onMarketData(BasicMarketData marketData) {
		if (strategyOrders.notEmpty()) {
			
		}
		handleMarketData(marketData);
	}

	@ProtectedAbstractMethod
	protected abstract void handleMarketData(BasicMarketData marketData);

	@Override
	public void onOrder(Order order) {
		updateOrder(order);
	}

	protected abstract boolean updateOrder(Order order);

	@Override
	public void onStrategyEvent(StrategyEvent event) {
		log.info("Handle StrategyControlEvent -> {}", event);
	}

	private boolean isEnable = false;

	@Override
	public void enable() {
		if (isInitSuccess)
			this.isEnable = true;
		log.info("Enable strategy -> strategyId==[{}], isInitSuccess==[{}], isEnable==[]", strategyId, isInitSuccess,
				isEnable);
	}

	@Override
	public void disable() {
		this.isEnable = false;
		log.info("Disable strategy -> strategyId==[{}]", strategyId);
	}

	@Override
	public boolean isEnabled() {
		return isEnable;
	}

	@Override
	public boolean isDisabled() {
		return !isEnable;
	}

	@Override
	public void enableAccount(int accountId) {
		AccountKeeper.setAccountTradable(accountId);
	}

	@Override
	public void disableAccount(int accountId) {
		AccountKeeper.setAccountNotTradable(accountId);
	}

	@Override
	public void enableInstrument(int instrumentId) {
		InstrumentKeeper.setTradable(instrumentId);
	}

	@Override
	public void disableInstrument(int instrumentId) {
		InstrumentKeeper.setNotTradable(instrumentId);
	}

	@Override
	public void onError(Throwable throwable) {
		log.error("StrategyId -> [{}] throw exception -> [{}]", strategyId, throwable);
	}

	@Override
	public ImmutableList<Instrument> instruments() {
		return instruments;
	}

	protected void orderTarget(Instrument instrument, TrdDirection direction, long targetQty) {
		orderTarget(instrument, direction, targetQty, Order.Const.OrdMinPrice, Order.Const.OrdMaxPrice);
	}

	protected void orderTarget(Instrument instrument, TrdDirection direction, long targetQty, long minPrice,
			long maxPrice) {
		LastMarkerData lastMarkerData = LastMarkerDataKeeper.get(instrument);
		long offerPrice = 0;
		switch (direction) {
		case Long:
			offerPrice = lastMarkerData.askPrice1().get();
			break;
		case Short:
			offerPrice = lastMarkerData.bidPrice1().get();
			break;
		default:
			throw new IllegalArgumentException("TrdDirection is illegal");
		}
		StrategyOrder strategyOrder = new StrategyOrder(strategyId, instrument, OrdQty.withOfferQty(targetQty),
				OrdPrice.withOffer(offerPrice), direction, OrdType.Limit,  subAccountId);
		strategyOrders.put(strategyOrder.ordSysId(), strategyOrder);

		MutableList<ParentOrder> ParentOrders = OrderExecutor.onStrategyOrder(strategyOrder);

		ParentOrder first = ParentOrders.getFirst();
		
		// TODO 错误实现
		Adaptor adaptor = getAdaptor(instrument);

		adaptor.newOredr(first.toChildOrder());
	}

	/**
	 * 由策略自行决定在交易不同Instrument时使用哪个Adaptor
	 * 
	 * @param instrument
	 * @return
	 */
	@ProtectedAbstractMethod
	@Deprecated
	protected abstract Adaptor getAdaptor(Instrument instrument);

}
