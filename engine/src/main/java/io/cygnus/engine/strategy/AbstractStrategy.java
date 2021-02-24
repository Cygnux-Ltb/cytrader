package io.cygnus.engine.strategy;

import static java.lang.Math.abs;

import java.util.function.Supplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.slf4j.Logger;

import io.cygnus.engine.strategy.api.Strategy;
import io.cygnus.engine.strategy.api.StrategyEvent;
import io.cygnus.engine.strategy.api.StrategySign;
import io.horizon.structure.account.Account;
import io.horizon.structure.account.AccountKeeper;
import io.horizon.structure.account.SubAccount;
import io.horizon.structure.adaptor.Adaptor;
import io.horizon.structure.market.data.MarkerDataKeeper;
import io.horizon.structure.market.data.MarkerDataKeeper.LastMarkerData;
import io.horizon.structure.market.data.MarketData;
import io.horizon.structure.market.instrument.Instrument;
import io.horizon.structure.market.instrument.InstrumentKeeper;
import io.horizon.structure.order.Order;
import io.horizon.structure.order.OrderBookKeeper;
import io.horizon.structure.order.actual.ChildOrder;
import io.horizon.structure.order.enums.OrdType;
import io.horizon.structure.order.enums.TrdAction;
import io.horizon.structure.order.enums.TrdDirection;
import io.horizon.structure.position.PositionKeeper;
import io.horizon.structure.risk.CircuitBreaker;
import io.mercury.common.annotation.lang.AbstractFunction;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.fsm.EnableableComponent;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.param.Params;
import io.mercury.common.param.Params.ParamKey;
import io.mercury.common.util.Assertor;
import lombok.Getter;

public abstract class AbstractStrategy<M extends MarketData, PK extends ParamKey> extends EnableableComponent
		implements Strategy<M>, CircuitBreaker {

	// Logger
	private final static Logger log = CommonLoggerFactory.getLogger(AbstractStrategy.class);

	// 策略ID
	@Getter
	protected final int strategyId;

	// 策略名称
	@Getter
	protected final String strategyName;

	// 子账号
	@Getter
	protected final SubAccount subAccount;
	protected final int subAccountId;

	// 实际账号
	@Getter
	protected final Account account;
	protected final int accountId;

	// 是否初始化成功
	private boolean initSuccess = false;

	// 记录当前策略所有的订单
	protected final MutableLongObjectMap<Order> orders = MutableMaps.newLongObjectHashMap();

	// 策略参数
	protected final Params<PK> params;

	protected AbstractStrategy(@Nonnull StrategySign sign, @Nonnull SubAccount subAccount,
			@Nullable Params<PK> params) {
		this.strategyId = sign.getStrategyId();
		this.strategyName = sign.getStrategyName();
		Assertor.nonNull(subAccount, "subAccount");
		this.subAccount = subAccount;
		this.subAccountId = subAccount.getSubAccountId();
		final Account account = AccountKeeper.getAccountBySubAccountId(subAccount.getSubAccountId());
		if (account == null)
			throw new IllegalArgumentException(subAccount.getSubAccountName() + " is not found Account");
		this.account = account;
		this.accountId = account.getAccountId();
		this.params = params;
	}

	@Override
	public Strategy<M> initialize(@Nonnull Supplier<Boolean> initializer) {
		Assertor.nonNull(initializer, "initializer");
		this.initSuccess = initializer.get();
		log.info("Initialize result initSuccess==[{}]", initSuccess);
		// TODO 设置StrategyKeeper
		// StrategyKeeper.putStrategy(this);
		return this;
	}

	@Override
	public void onMarketData(M marketData) {
		if (orders.notEmpty()) {
			log.info("{} :: strategyOrders not empty, doing....", getStrategyName());
			// TODO
		}
		handleMarketData(marketData);
	}

	@AbstractFunction
	protected abstract void handleMarketData(M marketData);

	@Override
	public void onOrder(Order order) {
		order.writeLog(log, "Call onOrder function");
		handleOrder(order);
	}

	@AbstractFunction
	protected abstract void handleOrder(Order order);

	@Override
	public void onStrategyEvent(StrategyEvent event) {
		// TODO
		log.info("{} :: Handle StrategyControlEvent -> {}", getStrategyName(), event);
	}

	@Override
	public boolean enable() {
		if (initSuccess) {
			boolean enable = super.enable();
			if (enable) {
				log.info("{} :: enable strategy success. strategyId==[{}], initSuccess==[{}], isEnable==[{}]",
						getStrategyName(), strategyId, initSuccess, isEnabled());
			} else {
				log.info(
						"{} :: enable strategy failure, strategy is enabled. strategyId==[{}], initSuccess==[{}], isEnable==[{}]",
						getStrategyName(), strategyId, initSuccess, isEnabled());
			}
			return enable;
		} else {
			log.info(
					"{} :: enable strategy failure, strategy is not initialized. strategyId==[{}], initSuccess==[{}], isEnable==[{}]",
					getStrategyName(), strategyId, initSuccess, isEnabled());
			throw new IllegalStateException("Strategy has been initialized");
		}
	}

	@Override
	public boolean disable() {
		boolean disable = super.disable();
		if (disable) {
			log.info("{} :: disable strategy success. strategyId==[{}], isEnable==[{}]", getStrategyName(), strategyId,
					isEnabled());
		} else {
			log.info("{} :: disable strategy failure, strategy is disabled. strategyId==[{}], isEnable==[{}]",
					getStrategyName(), strategyId, isEnabled());
		}
		return disable;
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
			return markerData.getAskPrice1();
		case Short:
			// 获取当前买一价
			return markerData.getBidPrice1();
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
			log.warn("{} :: No position, subAccountId==[{}], instrument -> {}", getStrategyName(), subAccountId,
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
		final ChildOrder childOrder = OrderBookKeeper.createAndSaveChildOrder(strategyId, subAccount, account,
				instrument, abs(offerQty), offerPrice, ordType, direction, TrdAction.Open);
		childOrder.writeLog(log, getStrategyName() + " :: Open position generate [ChildOrder]");
		saveOrder(childOrder);

		getAdaptor(instrument).newOredr(childOrder);
		childOrder.writeLog(log, getStrategyName() + " :: Open position [ChildOrder] has been sent");
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
		final int position = getCurrentPosition(subAccountId, instrument);
		if (position == 0) {
			log.warn(
					"{} :: Terminate execution close all positions, subAccountId==[{}], instrumentCode==[{}], position==[{}]",
					getStrategyName(), subAccountId, instrument.getInstrumentCode(), position);
		} else {
			log.info("{} :: Execution close all positions, subAccountId==[{}], instrumentCode==[{}], position==[{}]",
					getStrategyName(), subAccountId, instrument.getInstrumentCode(), position);
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
		final int position = getCurrentPosition(subAccountId, instrument);
		if (position == 0) {
			log.warn(
					"{} :: Terminate execution close all positions, subAccountId==[{}], instrumentCode==[{}], position==[{}]",
					getStrategyName(), subAccountId, instrument.getInstrumentCode(), position);
		} else {
			log.info("{} :: Execution close all positions, subAccountId==[{}], instrumentCode==[{}], position==[{}]",
					getStrategyName(), subAccountId, instrument.getInstrumentCode(), position);
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
		final int position = getCurrentPosition(subAccountId, instrument);
		if (position == 0) {
			log.warn(
					"{} :: Terminate execution close all positions, subAccountId==[{}], instrumentCode==[{}], position==[{}]",
					getStrategyName(), subAccountId, instrument.getInstrumentCode(), position);
		} else {
			log.info("{} :: Execution close all positions, subAccountId==[{}], instrumentCode==[{}], position==[{}]",
					getStrategyName(), subAccountId, instrument.getInstrumentCode(), position);
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
		final ChildOrder childOrder = OrderBookKeeper.createAndSaveChildOrder(strategyId, subAccount, account,
				instrument, abs(offerQty), offerPrice, ordType, offerQty > 0 ? TrdDirection.Long : TrdDirection.Short,
				TrdAction.Close);

		childOrder.writeLog(log, "Close position generate [ChildOrder]");
		saveOrder(childOrder);

		getAdaptor(instrument).newOredr(childOrder);
		childOrder.writeLog(log, "Close position [ChildOrder] has been sent");
	}

	/**
	 * 订单写入策略
	 * 
	 * @param order
	 */
	private void saveOrder(Order order) {
		orders.put(order.getOrdSysId(), order);
	}

	/**
	 * 由策略自行决定在交易不同Instrument时使用哪个Adaptor
	 * 
	 * @param instrument
	 * @return
	 */
	@AbstractFunction
	protected abstract Adaptor getAdaptor(@Nonnull Instrument instrument);

}
