package io.redstone.engine.impl.strategy;

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
import io.redstone.core.account.SubAccount;
import io.redstone.core.adaptor.Adaptor;
import io.redstone.core.keeper.AccountKeeper;
import io.redstone.core.keeper.InstrumentKeeper;
import io.redstone.core.keeper.LastMarkerDataKeeper;
import io.redstone.core.keeper.LastMarkerDataKeeper.LastMarkerData;
import io.redstone.core.keeper.OrderKeeper;
import io.redstone.core.keeper.PositionsKeeper;
import io.redstone.core.order.Order;
import io.redstone.core.order.OrderBook;
import io.redstone.core.order.enums.OrdType;
import io.redstone.core.order.enums.TrdAction;
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

	private String strategyName;

	private int subAccountId;

	private boolean initSuccess = false;

	private boolean isEnable = false;

	protected final Logger log = CommonLoggerFactory.getLogger(getClass());

	protected final SubAccount subAccount;

	// 记录当前策略所有的策略订单订单
	protected final MutableLongObjectMap<StrategyOrder> strategyOrders = MutableMaps.newLongObjectHashMap();

	protected StrategyBaseImpl(int strategyId, String strategyName, int subAccountId) {
		this.strategyId = strategyId;
		this.strategyName = StringUtil.isNullOrEmpty(strategyName)
				? "strategyId[" + strategyId + "]subAccountId[" + subAccountId + "]"
				: strategyName;
		this.subAccountId = subAccountId;
		this.subAccount = AccountKeeper.getSubAccount(subAccountId);
	}

	@Override
	public void initialize(@Nonnull Supplier<Boolean> initializer) {
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
		if (strategyOrders.notEmpty()) {
			log.info("{} :: StrategyOrders not empty, doing...", strategyName);
		}
		handleMarketData(marketData);
	}

	@ProtectedAbstractMethod
	protected abstract void handleMarketData(BasicMarketData marketData);

	@Override
	public void onOrder(Order order) {
		log.info(
				"{} :: On order callback, ordSysId==[{}], ordStatus==[{}], trdDirection==[{}], ordPrice -> {}, ordQty -> {}, instrument -> {}",
				strategyName, order.ordSysId(), order.ordStatus(), order.trdDirection(), order.ordPrice(),
				order.ordQty(), order.instrument());
		handleOrder(order);
	}

	protected abstract void handleOrder(Order order);

	@Override
	public void onStrategyEvent(StrategyEvent event) {
		log.info("Handle StrategyControlEvent -> {}", event);
	}

	@Override
	public Strategy enable() {
		if (initSuccess) {
			this.isEnable = true;
			log.info("{} :: Enable strategy, strategyId==[{}], initSuccess==[{}], isEnable==[{}]", strategyName,
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
	 * 
	 * @param instrument
	 * @param direction
	 * @param targetQty
	 */
	void orderWatermark(Instrument instrument, TrdDirection direction, int targetQty) {
		orderWatermark(instrument, direction, targetQty, -1L, -1);
	}

	/**
	 * 
	 * @param instrument
	 * @param direction
	 * @param targetQty
	 */
	void orderWatermark(Instrument instrument, TrdDirection direction, int targetQty, long limitPrice) {
		orderWatermark(instrument, direction, targetQty, limitPrice, 0);
	}

	/**
	 * 
	 * @param instrument 交易标的
	 * @param direction  交易方向
	 * @param targetQty  目标数量
	 * @param minPrice   限定价格
	 * @param maxPrice   允许浮动点差
	 */
	void orderWatermark(Instrument instrument, TrdDirection direction, int targetQty, long limitPrice, int floatTick) {
		LastMarkerData lastMarkerData = LastMarkerDataKeeper.get(instrument);
		long offerPrice = 0L;
		if (limitPrice > 0) {
			offerPrice = limitPrice;
		} else {
			switch (direction) {
			case Long:
				offerPrice = lastMarkerData.askPrice1();
				break;
			case Short:
				offerPrice = lastMarkerData.bidPrice1();
				break;
			case Invalid:
				throw new IllegalArgumentException("TrdDirection is invalid");
			}
		}
		StrategyOrder strategyOrder = new StrategyOrder(strategyId, instrument, OrdQty.withOfferQty(targetQty),
				OrdPrice.withOffer(offerPrice), direction, OrdType.Limit, subAccountId);
		strategyOrders.put(strategyOrder.ordSysId(), strategyOrder);

		MutableList<ParentOrder> ParentOrders = strategyOrderConverter.apply(strategyOrder);

		ParentOrder first = ParentOrders.getFirst();

		Adaptor adaptor = getAdaptor(instrument);

		adaptor.newOredr(first.toChildOrder());
	}

	/**
	 * 将StrategyOrder转换为需要执行的实际订单
	 */
	private Function<StrategyOrder, MutableList<ParentOrder>> strategyOrderConverter = strategyOrder -> {
		MutableList<ParentOrder> parentOrders = MutableLists.newFastList();
		OrderBook instrumentOrderBook = OrderKeeper.getInstrumentOrders(strategyOrder.instrument());
		int offerQty = strategyOrder.ordQty().offerQty();
		switch (strategyOrder.trdDirection()) {
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

	void closeAllPositions(Instrument instrument) {
		int position = PositionsKeeper.getPosition(subAccountId, instrument);
		if (position == 0) {
			log.warn("StrategyBaseImpl :: No position, subAccountId==[{}], instrument -> {}", subAccountId, instrument);
			return;
		} else if (position > 0) {
			
		} else {

		}
	}

	void closePositions(Instrument instrument, int closeQty) {
		
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
