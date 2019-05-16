package io.ffreedom.redstone.specific.strategy;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.slf4j.Logger;

import io.ffreedom.common.annotations.lang.ProtectedAbstractMethod;
import io.ffreedom.common.collect.ImmutableLists;
import io.ffreedom.common.collect.MutableMaps;
import io.ffreedom.common.functional.Initializer;
import io.ffreedom.common.log.CommonLoggerFactory;
import io.ffreedom.common.utils.StringUtil;
import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.polaris.market.BasicMarketData;
import io.ffreedom.redstone.actor.OrderExecutionActor;
import io.ffreedom.redstone.actor.QuoteActor;
import io.ffreedom.redstone.actor.QuoteActor.AtomicQuote;
import io.ffreedom.redstone.core.adaptor.impl.OutboundAdaptor;
import io.ffreedom.redstone.core.order.api.Order;
import io.ffreedom.redstone.core.order.enums.OrdSide;
import io.ffreedom.redstone.core.order.enums.OrdType;
import io.ffreedom.redstone.core.order.impl.ParentOrder;
import io.ffreedom.redstone.core.order.impl.VirtualOrder;
import io.ffreedom.redstone.core.order.structure.OrdQtyPrice;
import io.ffreedom.redstone.core.strategy.CircuitBreaker;
import io.ffreedom.redstone.core.strategy.Strategy;
import io.ffreedom.redstone.core.strategy.StrategyControlEvent;
import io.ffreedom.redstone.core.trade.enums.TrdDirection;
import io.ffreedom.redstone.storage.AccountKeeper;
import io.ffreedom.redstone.storage.InstrumentKeeper;
import io.ffreedom.redstone.storage.OrderKeeper;

public abstract class BaseStrategy<M extends BasicMarketData> implements Strategy, CircuitBreaker {

	private int strategyId;

	private int subAccountId;

	private boolean isInitSuccess = false;

	protected Logger logger = CommonLoggerFactory.getLogger(getClass());

	protected String strategyName;

	private String defaultStrategyName;

	// 策略订阅的合约
	private ImmutableList<Instrument> instruments;

	// 记录当前策略所有订单
	protected MutableLongObjectMap<VirtualOrder> strategyVirtualOrders = MutableMaps.newLongObjectHashMap();

	protected BaseStrategy(int strategyId, int subAccountId, Instrument... instruments) {
		this.strategyId = strategyId;
		this.subAccountId = subAccountId;
		this.defaultStrategyName = "strategyId[" + strategyId + "]subAccountId[" + subAccountId + "]";
		this.instruments = ImmutableLists.newImmutableList(instruments);
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
	public String getStrategyName() {
		if (StringUtil.isNullOrEmpty(strategyName))
			return defaultStrategyName;
		return strategyName;
	}

	@Override
	public int getSubAccountId() {
		return subAccountId;
	}

	@Override
	public void onMarketData(BasicMarketData marketData) {
		if (strategyVirtualOrders.notEmpty()) {

		}
		handleMarketData(marketData);
	}

	@ProtectedAbstractMethod
	protected abstract void handleMarketData(BasicMarketData marketData);

	@Override
	public void onOrder(Order order) {
		logger.info("handle order ordSysId==[{}]", order.getOrdSysId());
		if (OrderKeeper.containsOrder(order.getOrdSysId())) {
			OrderKeeper.updateOrder(order);
		} else {
			OrderKeeper.insertOrder(order);
		}
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
	public ImmutableList<Instrument> getInstruments() {
		return instruments;
	}

	protected void orderTarget(Instrument instrument, TrdDirection direction, long targetQty) {
		orderTarget(instrument, direction, targetQty, Order.Constant.OrdMinPrice, Order.Constant.OrdMaxPrice);
	}

	protected void orderTarget(Instrument instrument, TrdDirection direction, long targetQty, double minPrice,
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
		strategyVirtualOrders.put(newVirtualOrder.getOrdSysId(), newVirtualOrder);

		ParentOrder parentOrder = OrderExecutionActor.Singleton.virtualOrderToActual(newVirtualOrder);

		OutboundAdaptor outboundAdaptor = getOutboundAdaptor(instrument);

		outboundAdaptor.newOredr(parentOrder.toChildOrder());
	}

	/**
	 * 由策略自行决定在交易不同Instrument时使用哪个Adaptor
	 * 
	 * @param instrument
	 * @return
	 */
	protected abstract OutboundAdaptor getOutboundAdaptor(Instrument instrument);

}
