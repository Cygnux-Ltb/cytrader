package io.redstone.engine.impl.strategy;

import java.util.function.Supplier;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.slf4j.Logger;

import io.mercury.common.annotation.lang.ProtectedAbstractMethod;
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
import io.redstone.core.adaptor.AdaptorStatus;
import io.redstone.core.order.Order;
import io.redstone.core.order.OrderKeeper;
import io.redstone.core.order.enums.OrdType;
import io.redstone.core.order.enums.TrdDirection;
import io.redstone.core.order.specific.ParentOrder;
import io.redstone.core.order.specific.StrategyOrder;
import io.redstone.core.order.structure.OrdPrice;
import io.redstone.core.order.structure.OrdQty;
import io.redstone.core.risk.CircuitBreaker;
import io.redstone.core.strategy.Strategy;
import io.redstone.core.strategy.StrategyEvent;

public abstract class StrategyBaseImpl<M extends MarketData> implements Strategy, CircuitBreaker {

	private int strategyId;

	private int subAccountId;

	private boolean isInitSuccess = false;

	private String strategyName;

	protected Logger log = CommonLoggerFactory.getLogger(getClass());

	// 记录当前策略所有的策略订单订单
	protected MutableLongObjectMap<StrategyOrder> strategyOrders = MutableMaps.newLongObjectHashMap();

	protected StrategyBaseImpl(int strategyId, String strategyName, int subAccountId) {
		this.strategyId = strategyId;
		this.strategyName = StringUtil.isNullOrEmpty(strategyName)
				? "strategyId[" + strategyId + "]subAccountId[" + subAccountId + "]"
				: strategyName;
		this.subAccountId = subAccountId;

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
		return strategyName;
	}

	@Override
	public int subAccountId() {
		return subAccountId;
	}

	@Override
	public void onMarketData(BasicMarketData marketData) {
		if (strategyOrders.notEmpty()) {
			log.info("{} : StrategyOrders not empty, doing...", strategyName);
		}
		handleMarketData(marketData);
	}

	@ProtectedAbstractMethod
	protected abstract void handleMarketData(BasicMarketData marketData);

	@Override
	public void onOrder(Order order) {
		log.info("handle order ordSysId==[{}]", order.ordSysId());
		OrderKeeper.updateOrder(order);
		handleOrder(order);
	}

	protected abstract void handleOrder(Order order);

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

	/**
	 * 
	 * @param instrument
	 * @param direction
	 * @param targetQty
	 */
	void orderTarget(Instrument instrument, TrdDirection direction, int targetQty) {
		orderTarget(instrument, direction, targetQty, -1L, -1);
	}

	/**
	 * 
	 * @param instrument
	 * @param direction
	 * @param targetQty
	 */
	void orderTarget(Instrument instrument, TrdDirection direction, int targetQty, long limitPrice) {
		orderTarget(instrument, direction, targetQty, limitPrice, 0);
	}

	/**
	 * 
	 * @param instrument 交易标的
	 * @param direction  交易方向
	 * @param targetQty  目标数量
	 * @param minPrice   限定价格
	 * @param maxPrice   允许浮动点差
	 */
	void orderTarget(Instrument instrument, TrdDirection direction, int targetQty, long limitPrice, int floatTick) {
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
				OrdPrice.withOffer(offerPrice), direction, OrdType.Limit, subAccountId);
		strategyOrders.put(strategyOrder.ordSysId(), strategyOrder);

		MutableList<ParentOrder> ParentOrders = OrderKeeper.onStrategyOrder(strategyOrder);

		ParentOrder first = ParentOrders.getFirst();

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
	protected abstract Adaptor getAdaptor(Instrument instrument);

	@Override
	public void onAdaptorStatus(int adaptorId, AdaptorStatus status) {
		// TODO Auto-generated method stub

	}

}
