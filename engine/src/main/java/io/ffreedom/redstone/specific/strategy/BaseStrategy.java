package io.ffreedom.redstone.specific.strategy;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.slf4j.Logger;

import io.ffreedom.common.collect.ECollections;
import io.ffreedom.common.functional.Initializer;
import io.ffreedom.common.log.CommonLoggerFactory;
import io.ffreedom.common.mark.ProtectedAbstractMethod;
import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.polaris.market.BasicMarketData;
import io.ffreedom.redstone.actor.OrderActor;
import io.ffreedom.redstone.actor.QuoteActor;
import io.ffreedom.redstone.actor.QuoteActor.AtomicQuote;
import io.ffreedom.redstone.core.adaptor.OutboundAdaptor;
import io.ffreedom.redstone.core.order.VirtualOrder;
import io.ffreedom.redstone.core.order.api.Order;
import io.ffreedom.redstone.core.order.enums.OrdSide;
import io.ffreedom.redstone.core.order.enums.OrdType;
import io.ffreedom.redstone.core.order.structure.OrdQtyPrice;
import io.ffreedom.redstone.core.order.structure.OrdTimestamps;
import io.ffreedom.redstone.core.order.structure.StopLoss;
import io.ffreedom.redstone.core.strategy.CircuitBreaker;
import io.ffreedom.redstone.core.strategy.Strategy;
import io.ffreedom.redstone.core.strategy.StrategyControlEvent;
import io.ffreedom.redstone.core.trade.enums.TrdDirection;
import io.ffreedom.redstone.storage.AccountKeeper;
import io.ffreedom.redstone.storage.InstrumentKeeper;

public abstract class BaseStrategy<M extends BasicMarketData> implements Strategy, CircuitBreaker {

	private int strategyId;

	private int subAccountId;

	private boolean isInitSuccess = false;

	protected Logger logger = CommonLoggerFactory.getLogger(getClass());

	// 策略订阅的合约
	protected Instrument instrument;

	// 记录当前策略所有订单
	protected MutableLongObjectMap<VirtualOrder> strategyOrders = ECollections.newLongObjectHashMap();

	protected BaseStrategy(int strategyId, int subAccountId, Instrument instrument) {
		this.strategyId = strategyId;
		this.subAccountId = subAccountId;
		this.instrument = instrument;
	}

	@Override
	public void init(Initializer<Boolean> initializer) {
		if (initializer != null)
			isInitSuccess = initializer.initialize();
		else
			logger.error("Initializer is null.");
		logger.info("Initialize result isInitSuccess==[{}]", isInitSuccess);
	}

	@Override
	public int getStrategyId() {
		return strategyId;
	}

	@Override
	public void onMarketData(BasicMarketData marketData) {
		if (strategyOrders.notEmpty()) {

		}
		specificOnMarketData(marketData);
	}

	@ProtectedAbstractMethod
	protected abstract void specificOnMarketData(BasicMarketData marketData);

	@Override
	public void onOrder(Order order) {
		OrderActor.Singleton.onOrder(order);
	}

	@Override
	public void onControlEvent(StrategyControlEvent event) {
		logger.info("Handle StrategyControlEvent -> {}", event);
	}

	private boolean isEnable = false;

	@Override
	public void enable() {
		if (isInitSuccess)
			this.isEnable = true;
		logger.info("Enable strategy -> strategyId==[{}], isInitSuccess==[{}], isEnable==[]", strategyId, isInitSuccess,
				isEnable);
	}

	@Override
	public void disable() {
		this.isEnable = false;
		logger.info("Disable strategy -> strategyId==[{}]", strategyId);
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
		logger.error("StrategyId -> [{}] throw exception -> [{}]", strategyId, throwable);
	}

	@Override
	public Instrument getInstrument() {
		return instrument;
	}

	protected void orderTarget(TrdDirection direction, double targetQty) {
		orderTarget(instrument, direction, targetQty);
	}

	protected void orderTarget(Instrument instrument, TrdDirection direction, double targetQty) {
		orderTarget(instrument, direction, targetQty, Order.Constant.OrdMinPrice, Order.Constant.OrdMaxPrice);
	}

	protected void orderTarget(Instrument instrument, TrdDirection direction, double targetQty, double minPrice,
			double maxPrice) {
		OrdSide ordSide;
		AtomicQuote quote = QuoteActor.Singleton.getQuote(instrument);
		double offerPrice;
		switch (direction) {
		case Long:
			ordSide = OrdSide.Buy;
			offerPrice = quote.getAskPrice1().get();
			break;
		case Short:
			ordSide = OrdSide.Sell;
			offerPrice = quote.getBidPrice1().get();
			break;
		default:
			throw new IllegalArgumentException("TrdDirection is illegal");
		}
		VirtualOrder newVirtualOrder = VirtualOrder.newVirtualOrder(instrument,
				OrdQtyPrice.withOffer(targetQty, offerPrice), ordSide, OrdType.Limit, strategyId, subAccountId);
		strategyOrders.put(newVirtualOrder.getOrdSysId(), newVirtualOrder);

		OutboundAdaptor outboundAdaptor = getOutboundAdaptor(instrument);

	}

	/**
	 * 由策略自行决定在交易不同Instrument时使用哪个Adaptor
	 * 
	 * @param instrument
	 * @return
	 */
	protected abstract OutboundAdaptor getOutboundAdaptor(Instrument instrument);

}
