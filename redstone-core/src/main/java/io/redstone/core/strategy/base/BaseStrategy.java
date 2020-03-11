package io.redstone.core.strategy.base;

import java.util.function.Supplier;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.slf4j.Logger;

import io.mercury.common.annotation.lang.ProtectedAbstractMethod;
import io.mercury.common.collections.ImmutableLists;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.util.StringUtil;
import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.instrument.InstrumentKeeper;
import io.mercury.polaris.financial.market.QuoteKeeper;
import io.mercury.polaris.financial.market.QuoteKeeper.AtomicQuote;
import io.mercury.polaris.financial.market.api.MarketData;
import io.mercury.polaris.financial.market.impl.BasicMarketData;
import io.redstone.core.account.AccountKeeper;
import io.redstone.core.adaptor.base.OutboundAdaptor;
import io.redstone.core.order.OrderExecutor;
import io.redstone.core.order.api.Order;
import io.redstone.core.order.enums.OrdSide;
import io.redstone.core.order.enums.OrdType;
import io.redstone.core.order.impl.ParentOrder;
import io.redstone.core.order.impl.StrategyOrder;
import io.redstone.core.order.structure.OrdPrice;
import io.redstone.core.order.structure.OrdQty;
import io.redstone.core.risk.CircuitBreaker;
import io.redstone.core.strategy.Strategy;
import io.redstone.core.strategy.StrategyControlEvent;
import io.redstone.core.trade.enums.TrdDirection;

public abstract class BaseStrategy<M extends MarketData> implements Strategy, CircuitBreaker {

	private int strategyId;

	private int subAccountId;

	private boolean isInitSuccess = false;

	protected Logger logger = CommonLoggerFactory.getLogger(getClass());

	protected String strategyName;

	private String defaultName;

	// 策略订阅的合约
	private ImmutableList<Instrument> instruments;

	// 记录当前策略所有订单
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
			logger.error("Initializer is null.");
		logger.info("Initialize result isInitSuccess==[{}]", isInitSuccess);
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
	public ImmutableList<Instrument> instruments() {
		return instruments;
	}

	protected void orderTarget(Instrument instrument, TrdDirection direction, long targetQty) {
		orderTarget(instrument, direction, targetQty, Order.Const.OrdMinPrice, Order.Const.OrdMaxPrice);
	}

	protected void orderTarget(Instrument instrument, TrdDirection direction, long targetQty, long minPrice,
			long maxPrice) {
		OrdSide ordSide;
		AtomicQuote quote = QuoteKeeper.Singleton.getQuote(instrument);
		long offerPrice = 0;
		switch (direction) {
		case Long:
			ordSide = OrdSide.Buy;
			// offerPrice = PriceUtil.longPriceToDouble(quote.getAskPrice1().get());
			break;
		case Short:
			ordSide = OrdSide.Sell;
			// offerPrice = PriceUtil.longPriceToDouble(quote.getBidPrice1().get());
			break;
		default:
			throw new IllegalArgumentException("TrdDirection is illegal");
		}
		StrategyOrder newVirtualOrder = StrategyOrder.newStrategyOrder(instrument, OrdQty.withOfferQty(targetQty),
				OrdPrice.withOffer(offerPrice), ordSide, OrdType.Limit, strategyId, subAccountId);
		strategyOrders.put(newVirtualOrder.ordSysId(), newVirtualOrder);

		ParentOrder parentOrder = OrderExecutor.onStrategyOrder(newVirtualOrder);

		//TODO 错误实现
		OutboundAdaptor outboundAdaptor = getOutboundAdaptor(instrument);

		outboundAdaptor.newOredr(parentOrder.toChildOrder());
	}

	/**
	 * 由策略自行决定在交易不同Instrument时使用哪个Adaptor
	 * 
	 * @param instrument
	 * @return
	 */
	@ProtectedAbstractMethod
	@Deprecated
	protected abstract OutboundAdaptor getOutboundAdaptor(Instrument instrument);

}
