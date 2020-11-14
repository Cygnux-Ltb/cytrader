package io.apollo.engine.strategy;

import static java.lang.Math.abs;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.slf4j.Logger;

import io.apollo.core.risk.CircuitBreaker;
import io.gemini.definition.account.Account;
import io.gemini.definition.account.AccountKeeper;
import io.gemini.definition.account.SubAccount;
import io.gemini.definition.adaptor.Adaptor;
import io.gemini.definition.market.data.MarkerDataKeeper;
import io.gemini.definition.market.data.MarkerDataKeeper.LastMarkerData;
import io.gemini.definition.market.data.MarketData;
import io.gemini.definition.market.instrument.Instrument;
import io.gemini.definition.market.instrument.InstrumentManager;
import io.gemini.definition.order.Order;
import io.gemini.definition.order.OrderKeeper;
import io.gemini.definition.order.actual.ChildOrder;
import io.gemini.definition.order.actual.ParentOrder;
import io.gemini.definition.order.enums.OrdType;
import io.gemini.definition.order.enums.TrdAction;
import io.gemini.definition.order.enums.TrdDirection;
import io.gemini.definition.position.PositionKeeper;
import io.mercury.common.annotation.lang.AbstractFunction;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.param.ParamKey;
import io.mercury.common.param.Params;
import io.mercury.common.util.Assertor;

public abstract class StrategyBaseImpl<M extends MarketData, PK extends ParamKey>
		implements Strategy<M>, CircuitBreaker {

	/**
	 * Logger
	 */
	private final Logger log = CommonLoggerFactory.getLogger(this.getClass());

	/**
	 * 策略ID
	 */
	private final int strategyId;

	/**
	 * 子账号和ID
	 */
	protected final SubAccount subAccount;
	protected final int subAccountId;

	/**
	 * 实际账号和ID
	 */
	protected final Account account;
	protected final int accountId;

	/**
	 * 是否初始化成功
	 */
	private boolean initSuccess = false;

	/**
	 * 是否激活
	 */
	private boolean isEnable = false;

	/**
	 * 记录当前策略所有的实际订单
	 */
	protected final MutableLongObjectMap<Order> orders = MutableMaps.newLongObjectHashMap();

	/**
	 * 策略参数
	 */
	protected final Params<PK> params;

	protected StrategyBaseImpl(int strategyId, int subAccountId, Params<PK> params) {
		this.strategyId = strategyId;
		this.subAccount = AccountKeeper.getSubAccount(subAccountId);
		this.subAccountId = subAccountId;
		this.account = AccountKeeper.getAccountBySubAccountId(subAccountId);
		this.accountId = account.accountId();
		this.params = params;
	}

	@Override
	public void initialize(@Nonnull Supplier<Boolean> initializer) {
		Assertor.nonNull(initializer, "initializer");
		this.initSuccess = initializer.get();
		log.info("Initialize result initSuccess==[{}]", initSuccess);
		// TODO 设置StrategyKeeper
		// StrategyKeeper.putStrategy(this);
	}

	@Override
	public int strategyId() {
		return strategyId;
	}

	@Override
	public SubAccount getSubAccount() {
		return subAccount;
	}

	@Override
	public Account getAccount() {
		return account;
	}

	@Override
	public void onMarketData(M marketData) {
		if (orders.notEmpty()) {
			log.info("{} :: strategyOrders not empty, doing....", strategyName());
			// TODO
		}
		handleMarketData(marketData);
	}

	@AbstractFunction
	protected abstract void handleMarketData(M marketData);

	@Override
	public void onOrder(Order order) {
		order.writeLog(log, strategyName(), "On order callback");
		handleOrder(order);
	}

	@AbstractFunction
	protected abstract void handleOrder(Order order);

	@Override
	public void onStrategyEvent(StrategyEvent event) {
		// TODO
		log.info("{} :: Handle StrategyControlEvent -> {}", strategyName(), event);
	}

	@Override
	public Strategy<M> enable() {
		if (initSuccess) {
			this.isEnable = true;
			log.info("{} :: Enable strategy success, strategyId==[{}], initSuccess==[{}], isEnable==[{}]",
					strategyName(), strategyId, initSuccess, isEnable);
			return this;
		} else {
			log.info("{} :: Enable strategy fail, strategyId==[{}], initSuccess==[{}], isEnable==[{}]", strategyName(),
					strategyId, initSuccess, isEnable);
			throw new IllegalStateException("Strategy has been initialized");
		}
	}

	@Override
	public Strategy<M> disable() {
		this.isEnable = false;
		log.info("{} :: Disable strategy -> strategyId==[{}], isEnable==[{}]", strategyName(), strategyId, isEnable);
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
		InstrumentManager.setTradable(instrumentId);
	}

	@Override
	public void disableInstrument(int instrumentId) {
		InstrumentManager.setNotTradable(instrumentId);
	}

	@Override
	public void onThrowable(Throwable throwable) {
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
//		long offerPrice = 0L;
//		if (limitPrice > 0)
//			offerPrice = limitPrice;
//		else
//			offerPrice = getLevel1Price(instrument, direction);

		// 创建策略订单
//		StrategyOrder strategyOrder = new StrategyOrder(strategyId, accountId, subAccountId, instrument,
//				OrdQty.withOffer(targetQty), OrdPrice.withOffer(offerPrice), OrdType.Limit, direction);
//		orders.put(strategyOrder.uniqueId(), strategyOrder);

		// 转换为实际订单
//		MutableList<ActParentOrder> parentOrders = strategyOrderConverter.apply(strategyOrder);

		// 存储订单
		// TODO 未完成全部逻辑
//		ActParentOrder parentOrder = parentOrders.getFirst();
//		orders.put(parentOrder.uniqueId(), parentOrder);

		// 转为实际执行的子订单
		// ActChildOrder childOrder = parentOrder.toChildOrder();
		// orders.put(childOrder.uniqueId(), childOrder);

		// getAdaptor(instrument).newOredr(childOrder);

	}

	/**
	 * 将StrategyOrder转换为需要执行的实际订单
	 */
//	private Function<StrategyOrder, MutableList<ActParentOrder>> strategyOrderConverter = strategyOrder -> {
//		MutableList<ActParentOrder> parentOrders = MutableLists.newFastList();
//		OrderBook instrumentOrderBook = OrderKeeper.getInstrumentOrderBook(strategyOrder.instrument());
//		int offerQty = strategyOrder.ordQty().offerQty();
//		switch (strategyOrder.direction()) {
//		case Long:
//			MutableLongObjectMap<Order> activeShortOrders = instrumentOrderBook.activeShortOrders();
//			if (activeShortOrders.notEmpty()) {
//				// TODO 当有活动的反向订单时选择撤单
//			}
//			// TODO 检查当前头寸, 如果有反向头寸, 选择平仓
//			// TODO 计算平仓后还需要开仓的数量
//			int needOpenLong = offerQty - 0;
//			ActParentOrder openLongOrder = strategyOrder.toActualOrder(TrdDirection.Long, needOpenLong, OrdType.Limit);
//			parentOrders.add(openLongOrder);
//			break;
//		case Short:
//			MutableLongObjectMap<Order> activeLongOrders = instrumentOrderBook.activeLongOrders();
//			if (activeLongOrders.notEmpty()) {
//				// TODO 当有活动的反向订单时选择撤单
//			}
//			// TODO 检查当前头寸, 如果有反向头寸, 选择平仓
//			// TODO 计算平仓后还需要开仓的数量
//			int needOpenShort = offerQty - 0;
//			ActParentOrder openShortOrder = strategyOrder.toActualOrder(TrdDirection.Short, needOpenShort,
//					OrdType.Limit);
//			parentOrders.add(openShortOrder);
//			break;
//		default:
//			break;
//		}
//		return parentOrders;
//	};

	/**
	 * 
	 * @param instrument
	 * @param direction
	 * @return
	 */
	protected long getLevel1Price(Instrument instrument, TrdDirection direction) {
		LastMarkerData markerData = MarkerDataKeeper.getLast(instrument);
		switch (direction) {
		case Long:
			// 获取当前卖一价
			return markerData.askPrice1();
		case Short:
			// 获取当前买一价
			return markerData.bidPrice1();
		default:
			throw new IllegalArgumentException("TrdDirection is [Invalid]");
		}
	}

	/**
	 * 
	 * @param subAccountId
	 * @param instrument
	 * @return
	 */
	protected int getCurrentPosition(int subAccountId, Instrument instrument) {
		int position = PositionKeeper.getCurrentPosition(subAccountId, instrument);
		if (position == 0)
			log.warn("{} :: No position, subAccountId==[{}], instrument -> {}", strategyName(), subAccountId,
					instrument);
		return position;
	}

	protected void openPosition(Instrument instrument, int offerQty, TrdDirection direction) {
		openPosition(instrument, offerQty, getLevel1Price(instrument, direction), OrdType.Limit, direction);
	}

	/**
	 * 
	 * @param instrument 交易标的
	 * @param offerQty   委托数量
	 * @param ordType    订单类型
	 * @param direction  多空方向
	 */
	protected void openPosition(Instrument instrument, int offerQty, OrdType ordType, TrdDirection direction) {
		openPosition(instrument, offerQty, getLevel1Price(instrument, direction), ordType, direction);
	}

	/**
	 * 
	 * @param instrument 交易标的
	 * @param offerQty   委托数量
	 * @param offerPrice 委托价格
	 * @param ordType    订单类型
	 * @param direction  多空方向
	 */
	protected void openPosition(Instrument instrument, int offerQty, long offerPrice, OrdType ordType,
			TrdDirection direction) {
		ParentOrder parentOrder = OrderKeeper.createParentOrder(strategyId, accountId, subAccountId, instrument,
				abs(offerQty), offerPrice, ordType, direction, TrdAction.Open);
		parentOrder.writeLog(log, strategyName(), "Open position generate [ParentOrder]");
		saveOrder(parentOrder);

		ChildOrder childOrder = OrderKeeper.toChildOrder(parentOrder);
		childOrder.writeLog(log, strategyName(), "Open position generate [ChildOrder]");
		saveOrder(childOrder);

		getAdaptor(instrument).newOredr(childOrder);
		childOrder.writeLog(log, strategyName(), "Open position [ChildOrder] has been sent");
	}

	/**
	 * 
	 * @param instrument 交易标的
	 */
	protected void closeAllPosition(Instrument instrument) {
		closeAllPosition(instrument, OrdType.Limit);
	}

	/**
	 * 
	 * @param instrument 交易标的
	 * @param ordType    订单类型
	 */
	protected void closeAllPosition(Instrument instrument, OrdType ordType) {
		int position = getCurrentPosition(subAccountId, instrument);
		if (position == 0) {
			log.warn(
					"{} :: Terminate execution close all positions, subAccountId==[{}], instrumentCode==[{}], position==[{}]",
					strategyName(), subAccountId, instrument.code(), position);
		} else {
			log.info("{} :: Execution close all positions, subAccountId==[{}], instrumentCode==[{}], position==[{}]",
					strategyName(), subAccountId, instrument.code(), position);
			long offerPrice = 0L;
			if (position > 0)
				offerPrice = getLevel1Price(instrument, TrdDirection.Long);
			else
				offerPrice = getLevel1Price(instrument, TrdDirection.Short);
			closePosition(instrument, position, offerPrice, ordType);
		}
	}

	/**
	 * 
	 * @param instrument
	 * @param offerPrice
	 */
	protected void closeAllPosition(Instrument instrument, long offerPrice) {
		int position = getCurrentPosition(subAccountId, instrument);
		if (position == 0) {
			log.warn(
					"{} :: Terminate execution close all positions, subAccountId==[{}], instrumentCode==[{}], position==[{}]",
					strategyName(), subAccountId, instrument.code(), position);
		} else {
			log.info("{} :: Execution close all positions, subAccountId==[{}], instrumentCode==[{}], position==[{}]",
					strategyName(), subAccountId, instrument.code(), position);
			closePosition(instrument, position, offerPrice, OrdType.Limit);
		}
	}

	/**
	 * 
	 * @param instrument 交易标的
	 * @param offerPrice 委托价格
	 * @param ordType    订单类型
	 */
	protected void closeAllPosition(Instrument instrument, long offerPrice, OrdType ordType) {
		int position = getCurrentPosition(subAccountId, instrument);
		if (position == 0) {
			log.warn(
					"{} :: Terminate execution close all positions, subAccountId==[{}], instrumentCode==[{}], position==[{}]",
					strategyName(), subAccountId, instrument.code(), position);
		} else {
			log.info("{} :: Execution close all positions, subAccountId==[{}], instrumentCode==[{}], position==[{}]",
					strategyName(), subAccountId, instrument.code(), position);
			closePosition(instrument, position, offerPrice, ordType);
		}
	}

	/**
	 * 
	 * @param instrument 交易标的
	 * @param offerQty   委托数量
	 * @param offerPrice 委托价格
	 */
	protected void closePosition(Instrument instrument, int offerQty, long offerPrice) {
		closePosition(instrument, offerQty, offerPrice, OrdType.Limit);
	}

	/**
	 * 
	 * @param instrument 交易标的
	 * @param offerQty   委托数量
	 * @param offerPrice 委托价格
	 * @param ordType    订单类型
	 */
	protected void closePosition(Instrument instrument, int offerQty, long offerPrice, OrdType ordType) {
		ParentOrder parentOrder = OrderKeeper.createParentOrder(strategyId, accountId, subAccountId, instrument,
				abs(offerQty), offerPrice, ordType, offerQty > 0 ? TrdDirection.Long : TrdDirection.Short,
				TrdAction.Close);
		parentOrder.writeLog(log, strategyName(), "Close position generate [ParentOrder]");
		saveOrder(parentOrder);

		ChildOrder childOrder = OrderKeeper.toChildOrder(parentOrder);
		childOrder.writeLog(log, strategyName(), "Close position generate [ChildOrder]");
		saveOrder(childOrder);

		getAdaptor(instrument).newOredr(childOrder);
		childOrder.writeLog(log, strategyName(), "Close position [ChildOrder] has been sent");
	}

	/**
	 * 订单写入策略
	 * 
	 * @param order
	 */
	private void saveOrder(Order order) {
		orders.put(order.uniqueId(), order);
	}

	/**
	 * 由策略自行决定在交易不同Instrument时使用哪个Adaptor
	 * 
	 * @param instrument
	 * @return
	 */
	@AbstractFunction
	protected abstract Adaptor getAdaptor(Instrument instrument);

}
