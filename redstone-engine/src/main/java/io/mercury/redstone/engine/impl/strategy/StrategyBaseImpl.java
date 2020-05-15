package io.mercury.redstone.engine.impl.strategy;

import static java.lang.Math.abs;

import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.slf4j.Logger;

import io.mercury.common.annotation.lang.ProtectedAbstractMethod;
import io.mercury.common.collections.MutableLists;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.util.Assertor;
import io.mercury.common.util.StringUtil;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.api.MarketData;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.redstone.core.account.SubAccount;
import io.mercury.redstone.core.adaptor.Adaptor;
import io.mercury.redstone.core.keeper.AccountKeeper;
import io.mercury.redstone.core.keeper.InstrumentKeeper;
import io.mercury.redstone.core.keeper.LastMarkerDataKeeper;
import io.mercury.redstone.core.keeper.OrderKeeper;
import io.mercury.redstone.core.keeper.PositionKeeper;
import io.mercury.redstone.core.keeper.LastMarkerDataKeeper.LastMarkerData;
import io.mercury.redstone.core.order.Order;
import io.mercury.redstone.core.order.OrderBook;
import io.mercury.redstone.core.order.enums.OrdType;
import io.mercury.redstone.core.order.enums.TrdAction;
import io.mercury.redstone.core.order.enums.TrdDirection;
import io.mercury.redstone.core.order.specific.ChildOrder;
import io.mercury.redstone.core.order.specific.ParentOrder;
import io.mercury.redstone.core.order.specific.StrategyOrder;
import io.mercury.redstone.core.order.structure.OrdPrice;
import io.mercury.redstone.core.order.structure.OrdQty;
import io.mercury.redstone.core.risk.CircuitBreaker;
import io.mercury.redstone.core.strategy.Strategy;
import io.mercury.redstone.core.strategy.StrategyEvent;

public abstract class StrategyBaseImpl<M extends MarketData> implements Strategy, CircuitBreaker {

	private final int strategyId;

	private final String strategyName;

	private final int subAccountId;

	private boolean initSuccess = false;

	private boolean isEnable = false;

	protected final SubAccount subAccount;

	protected final Logger log = CommonLoggerFactory.getLogger(getClass());

	/**
	 * 记录当前策略所有的实际订单
	 */
	protected final MutableLongObjectMap<Order> orders = MutableMaps.newLongObjectHashMap();

	protected StrategyBaseImpl(int strategyId, String strategyName, int subAccountId) {
		this.strategyId = strategyId;
		this.strategyName = StringUtil.isNullOrEmpty(strategyName)
				? "strategyId[" + strategyId + "]-subAccountId[" + subAccountId + "]"
				: strategyName;
		this.subAccountId = subAccountId;
		this.subAccount = AccountKeeper.getSubAccount(subAccountId);
	}

	@Override
	public void initialization(@Nonnull Supplier<Boolean> initializer) {
		initSuccess = Assertor.nonNull(initializer, "initializer").get();
		log.info("Initialize result initSuccess==[{}]", initSuccess);
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
		if (orders.notEmpty()) {
			log.info("{} :: strategyOrders not empty, doing...", strategyName);
		}
		handleMarketData(marketData);
	}

	@ProtectedAbstractMethod
	protected abstract void handleMarketData(BasicMarketData marketData);

	@Override
	public void onOrder(Order order) {
		order.outputInfoLog(log, strategyName, "On order callback");
		handleOrder(order);
	}

	@ProtectedAbstractMethod
	protected abstract void handleOrder(Order order);

	@Override
	public void onStrategyEvent(StrategyEvent event) {
		log.info("{} :: Handle StrategyControlEvent -> {}", strategyName, event);
	}

	@Override
	public Strategy enable() {
		if (initSuccess) {
			this.isEnable = true;
			log.info("{} :: Enable strategy success, strategyId==[{}], initSuccess==[{}], isEnable==[{}]", strategyName,
					strategyId, initSuccess, isEnable);
		} else {
			log.info("{} :: Enable strategy fail, strategyId==[{}], initSuccess==[{}], isEnable==[{}]", strategyName,
					strategyId, initSuccess, isEnable);
		}
		return this;
	}

	@Override
	public Strategy disable() {
		this.isEnable = false;
		log.info("{} :: Disable strategy -> strategyId==[{}], isEnable==[{}]", strategyName, strategyId, isEnable);
		return this;
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
	 * 做市策略使用, 维持指定价位的挂单数量
	 * 
	 * @param instrument
	 * @param direction
	 * @param targetQty
	 */
	void orderWatermark(Instrument instrument, TrdDirection direction, int targetQty) {
		orderWatermark(instrument, direction, targetQty, -1L, -1);
	}

	/**
	 * 做市策略使用, 维持指定价位的挂单数量
	 * 
	 * @param instrument
	 * @param direction
	 * @param targetQty
	 */
	void orderWatermark(Instrument instrument, TrdDirection direction, int targetQty, long limitPrice) {
		orderWatermark(instrument, direction, targetQty, limitPrice, 0);
	}

	/**
	 * 做市策略使用, 维持指定价位的挂单数量
	 * 
	 * @param instrument 交易标的
	 * @param direction  交易方向
	 * @param targetQty  目标数量
	 * @param minPrice   限定价格
	 * @param maxPrice   允许浮动点差
	 */
	void orderWatermark(Instrument instrument, TrdDirection direction, int targetQty, long limitPrice, int floatTick) {
		long offerPrice = 0L;
		if (limitPrice > 0) {
			offerPrice = limitPrice;
		} else {
			offerPrice = getLevel1Price(instrument, direction);
		}
		StrategyOrder strategyOrder = new StrategyOrder(strategyId, subAccountId, instrument,
				OrdQty.withOffer(targetQty), OrdPrice.withOffer(offerPrice), OrdType.Limit, direction);

		orders.put(strategyOrder.ordSysId(), strategyOrder);

		MutableList<ParentOrder> parentOrders = strategyOrderConverter.apply(strategyOrder);

		// TODO 未完成全部逻辑
		ParentOrder first = parentOrders.getFirst();

		getAdaptor(instrument).newOredr(first.toChildOrder());
	}

	/**
	 * 将StrategyOrder转换为需要执行的实际订单
	 */
	private Function<StrategyOrder, MutableList<ParentOrder>> strategyOrderConverter = strategyOrder -> {
		MutableList<ParentOrder> parentOrders = MutableLists.newFastList();
		OrderBook instrumentOrderBook = OrderKeeper.getInstrumentOrders(strategyOrder.instrument());
		int offerQty = strategyOrder.ordQty().offerQty();
		switch (strategyOrder.direction()) {
		case Long:
			MutableLongObjectMap<Order> activeShortOrders = instrumentOrderBook.activeShortOrders();
			if (activeShortOrders.notEmpty()) {
				// TODO 当有活动的反向订单时选择撤单
			}
			// TODO 检查当前头寸, 如果有反向头寸, 选择平仓
			// TODO 计算平仓后还需要开仓的数量
			int needOpenLong = offerQty - 0;
			ParentOrder openLongOrder = strategyOrder.toActualOrder(TrdDirection.Long, needOpenLong, OrdType.Limit);
			parentOrders.add(openLongOrder);
			break;
		case Short:
			MutableLongObjectMap<Order> activeLongOrders = instrumentOrderBook.activeLongOrders();
			if (activeLongOrders.notEmpty()) {
				// TODO 当有活动的反向订单时选择撤单
			}
			// TODO 检查当前头寸, 如果有反向头寸, 选择平仓
			// TODO 计算平仓后还需要开仓的数量
			int needOpenShort = offerQty - 0;
			ParentOrder openShortOrder = strategyOrder.toActualOrder(TrdDirection.Short, needOpenShort, OrdType.Limit);
			parentOrders.add(openShortOrder);
			break;
		default:
			break;
		}
		return parentOrders;
	};

	/**
	 * 
	 * @param instrument
	 * @param direction
	 * @return
	 */
	private long getLevel1Price(Instrument instrument, TrdDirection direction) {
		LastMarkerData markerData = LastMarkerDataKeeper.get(instrument);
		switch (direction) {
		case Long:
			return markerData.askPrice1();
		case Short:
			return markerData.bidPrice1();
		default:
			throw new IllegalArgumentException("TrdDirection is [Invalid]");
		}
	}

	/**
	 * 
	 * @param instrument
	 * @param offerQty
	 * @param ordType
	 * @param direction
	 */
	void openPosition(Instrument instrument, int offerQty, OrdType ordType, TrdDirection direction) {
		openPosition(instrument, offerQty, getLevel1Price(instrument, direction), ordType, direction);
	}

	/**
	 * 
	 * @param instrument
	 * @param offerQty
	 * @param offerPrice
	 * @param ordType
	 * @param direction
	 */
	void openPosition(Instrument instrument, int offerQty, long offerPrice, OrdType ordType, TrdDirection direction) {
		ParentOrder parentOrder = new ParentOrder(strategyId, subAccountId, instrument, abs(offerQty), offerPrice,
				ordType, direction, TrdAction.Open);
		parentOrder.outputInfoLog(log, strategyName, "Open position generate ParentOrder");
		putOrder(parentOrder);

		ChildOrder childOrder = parentOrder.toChildOrder();
		childOrder.outputInfoLog(log, strategyName, "Open position generate ChildOrder");
		putOrder(childOrder);

		getAdaptor(instrument).newOredr(childOrder);

	}

	/**
	 * 
	 * @param instrument
	 */
	void closeAllPosition(Instrument instrument) {
		int position = PositionKeeper.getCurrentPosition(subAccountId, instrument);
		if (position == 0) {
			log.warn("{} :: No position, subAccountId==[{}], instrument -> {}", strategyName, subAccountId, instrument);
			return;
		} else {
			log.info("{} :: Execution close all positions, subAccountId==[{}] instrumentCode==[{}], position==[{}]",
					strategyName, instrument.code(), position);
			LastMarkerData markerData = LastMarkerDataKeeper.get(instrument);
			long offerPrice = 0L;
			if (position > 0) {
				// 获取当前卖一价
				offerPrice = markerData.askPrice1();
			} else {
				// 获取当前买一价
				offerPrice = markerData.bidPrice1();
			}
			closePosition(instrument, position, offerPrice);
		}
	}

	/**
	 * 
	 * @param instrument
	 * @param offerPrice
	 */
	void closeAllPosition(Instrument instrument, long offerPrice) {
		int position = PositionKeeper.getCurrentPosition(subAccountId, instrument);
		if (position == 0) {
			log.warn("{} :: No position, subAccountId==[{}], instrument -> {}", strategyName, subAccountId, instrument);
			return;
		} else {
			log.info("{} :: Execution close all positions, subAccountId==[{}] instrumentCode==[{}], position==[{}]",
					strategyName, instrument.code(), position);
			closePosition(instrument, position, offerPrice);
		}
	}

	/**
	 * 
	 * @param instrument
	 * @param offerQty
	 * @param offerPrice
	 */
	void closePosition(Instrument instrument, final int offerQty, long offerPrice) {
		ParentOrder parentOrder = new ParentOrder(strategyId, subAccountId, instrument, abs(offerQty), offerPrice,
				OrdType.Limit, offerQty > 0 ? TrdDirection.Long : TrdDirection.Short, TrdAction.Close);
		parentOrder.outputInfoLog(log, strategyName, "Close position generate ParentOrder");
		putOrder(parentOrder);

		ChildOrder childOrder = parentOrder.toChildOrder();
		childOrder.outputInfoLog(log, strategyName, "Close position generate ChildOrder");
		putOrder(childOrder);

		getAdaptor(instrument).newOredr(childOrder);

	}

	private void putOrder(Order order) {
		orders.put(order.ordSysId(), order);
	}

	/**
	 * 由策略自行决定在交易不同Instrument时使用哪个Adaptor
	 * 
	 * @param instrument
	 * @return
	 */
	@ProtectedAbstractMethod
	protected abstract Adaptor getAdaptor(Instrument instrument);

}
