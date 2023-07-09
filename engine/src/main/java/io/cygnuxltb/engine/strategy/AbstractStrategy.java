package io.cygnuxltb.engine.strategy;

import io.cygnuxltb.engine.position.PositionKeeper;
import io.cygnuxltb.engine.trader.OrderKeeper;
import io.horizon.market.data.MarketData;
import io.horizon.market.data.MarketDataKeeper;
import io.horizon.market.data.MarketDataKeeper.MarketDataSnapshot;
import io.horizon.market.instrument.Instrument;
import io.horizon.market.instrument.InstrumentKeeper;
import io.horizon.trader.account.Account;
import io.horizon.trader.account.Account.AccountException;
import io.horizon.trader.account.AccountFinder;
import io.horizon.trader.account.SubAccount;
import io.horizon.trader.adaptor.Adaptor;
import io.horizon.trader.order.ChildOrder;
import io.horizon.trader.order.OrdSysIdAllocator;
import io.horizon.trader.order.Order;
import io.horizon.trader.order.enums.OrdType;
import io.horizon.trader.order.enums.TrdAction;
import io.horizon.trader.order.enums.TrdDirection;
import io.horizon.trader.risk.CircuitBreaker;
import io.horizon.trader.strategy.Strategy;
import io.horizon.trader.strategy.StrategyEvent;
import io.horizon.trader.transport.inbound.TdxQueryBalance;
import io.horizon.trader.transport.inbound.TdxQueryPositions;
import io.mercury.common.annotation.AbstractFunction;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.fsm.EnableableComponent;
import io.mercury.common.lang.Asserter;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.param.ParamKey;
import io.mercury.common.param.Params;
import io.mercury.common.sequence.SnowflakeAlgo;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

import static java.lang.Math.abs;

public abstract class AbstractStrategy<M extends MarketData, K extends ParamKey>
        extends EnableableComponent
        implements Strategy<M>, CircuitBreaker {

    private final static Logger log = Log4j2LoggerFactory.getLogger(AbstractStrategy.class);

    // 策略ID
    protected final int strategyId;

    // 策略名称
    protected final String strategyName;

    // 子账号
    protected final SubAccount subAccount;
    protected final int subAccountId;

    // 实际账号
    protected final Account account;
    protected final int accountId;

    // 是否初始化成功
    private boolean initSuccess = false;

    // 记录当前策略所有的订单
    protected final MutableLongObjectMap<Order> orders = MutableMaps.newLongObjectHashMap();

    // 策略参数
    protected final Params<K> params;

    // TODO
    protected TdxQueryPositions queryPositionsReq;
    // TODO
    protected TdxQueryBalance queryBalanceReq;

    private final OrdSysIdAllocator allocator;

    protected AbstractStrategy(int strategyId, @Nonnull String strategyName, @Nonnull SubAccount subAccount,
                               @Nullable Params<K> params) {
        Asserter.nonNull(subAccount, "subAccount");
        Asserter.atWithinRange(strategyId, 1, Strategy.MAX_STRATEGY_ID, "strategyId");
        Asserter.nonEmpty(strategyName, "strategyName");
        this.strategyId = strategyId;
        this.strategyName = strategyName;
        this.subAccount = subAccount;
        this.subAccountId = subAccount.getSubAccountId();
        final Account account = AccountFinder.getAccountBySubAccountId(subAccount.getSubAccountId());
        if (account == null)
            throw new AccountException(subAccount.getSubAccountName() + " is not found Account");
        this.account = account;
        this.accountId = account.getAccountId();
        this.params = params;
        this.queryPositionsReq = TdxQueryPositions.newBuilder().setAccountId(accountId).setBrokerId(account.getBrokerId())
                .setOperatorId(strategyName).setStrategyId(strategyId).setSubAccountId(subAccountId).build();
        this.queryBalanceReq = TdxQueryBalance.newBuilder().setAccountId(accountId).setBrokerId(account.getBrokerId())
                .setOperatorId(strategyName).setStrategyId(strategyId).setSubAccountId(subAccountId).build();
        var snowflake = new SnowflakeAlgo(strategyId);
        this.allocator = snowflake::next;
    }

    public int getStrategyId() {
        return strategyId;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public SubAccount getSubAccount() {
        return subAccount;
    }

    public Account getAccount() {
        return account;
    }

    public MutableLongObjectMap<Order> getOrders() {
        return orders;
    }

    public Params<K> getParams() {
        return params;
    }

    @Override
    public Strategy<M> initialize(@Nonnull Supplier<Boolean> initializer) {
        Asserter.nonNull(initializer, "initializer");
        this.initSuccess = initializer.get();
        log.info("Initialize result initSuccess==[{}]", initSuccess);
        // TODO 设置StrategyKeeper
        // StrategyKeeper.putStrategy(this);
        return this;
    }

    @Override
    public void onMarketData(@Nonnull M marketData) {
        if (orders.notEmpty()) {
            log.info("{} :: strategyOrders not empty, doing....", getStrategyName());
            // TODO
        }
        handleMarketData(marketData);
    }

    @AbstractFunction
    protected abstract void handleMarketData(M marketData);

    @Override
    public void onOrder(@Nonnull Order order) {
        order.writeLog(log, "Call onOrder function");
        handleOrder(order);
    }

    @AbstractFunction
    protected abstract void handleOrder(Order order);

    @Override
    public void onStrategyEvent(@Nonnull StrategyEvent event) {
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
        AccountFinder.setAccountTradable(accountId);
    }

    @Override
    public void disableAccount(int accountId) {
        AccountFinder.setAccountNotTradable(accountId);
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
        log.error("StrategyId -> [{}] throw exception -> [{}]",
                strategyId, throwable.getMessage(), throwable);
    }

    /**
     * 做市策略使用, 维持指定价位的挂单数量
     *
     * @param instrument Instrument
     * @param direction  TrdDirection
     * @param targetQty  int
     */
    void orderWatermark(Instrument instrument, TrdDirection direction, int targetQty) {
        orderWatermark(instrument, direction, targetQty, -1L, -1);
    }

    /**
     * 做市策略使用, 维持指定价位的挂单数量
     *
     * @param instrument Instrument
     * @param direction  TrdDirection
     * @param targetQty  int
     * @param limitPrice long
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
     * @param limitPrice 限定价格
     * @param floatTick  允许浮动点差
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
     * @param instrument Instrument
     * @param direction  TrdDirection
     * @return double
     */
    protected double getLevel1Price(Instrument instrument, TrdDirection direction) {
        MarketDataSnapshot snapshot = MarketDataKeeper.getSnapshot(instrument);
        return switch (direction) {
            // 获取当前卖一价
            case Long -> snapshot.getAskPrice1();
            // 获取当前买一价
            case Short -> snapshot.getBidPrice1();
            // 状态错误
            default -> throw new IllegalArgumentException("Direction is [Invalid]");
        };
    }

    /**
     * @param subAccountId int
     * @param instrument   Instrument
     * @return int
     */
    protected int getCurrentPosition(int subAccountId, Instrument instrument) {
        int position = PositionKeeper.getCurrentSubAccountPosition(subAccountId, instrument);
        if (position == 0)
            log.warn("{} :: No position, subAccountId==[{}], instrument -> {}", getStrategyName(), subAccountId,
                    instrument);
        return position;
    }

    protected void openPosition(Instrument instrument, int offerQty, TrdDirection direction) {
        openPosition(instrument, offerQty, getLevel1Price(instrument, direction), OrdType.Limited, direction);
    }

    /**
     * @param instrument 交易标的
     * @param offerQty   委托数量
     * @param ordType    订单类型
     * @param direction  多空方向
     */
    protected void openPosition(Instrument instrument, int offerQty, OrdType ordType, TrdDirection direction) {
        openPosition(instrument, offerQty, getLevel1Price(instrument, direction), ordType, direction);
    }

    /**
     * @param instrument 交易标的
     * @param offerQty   委托数量
     * @param offerPrice 委托价格
     * @param ordType    订单类型
     * @param direction  多空方向
     */
    protected void openPosition(Instrument instrument, int offerQty, double offerPrice, OrdType ordType,
                                TrdDirection direction) {
        final ChildOrder childOrder = OrderKeeper.createAndSaveChildOrder(allocator, strategyId, subAccount, account,
                instrument, abs(offerQty), offerPrice, ordType, direction, TrdAction.Open);
        childOrder.writeLog(log, getStrategyName() + " :: Open position generate [ChildOrder]");
        saveOrder(childOrder);

        getAdaptor(instrument).newOrder(childOrder.toNewOrder());
        childOrder.writeLog(log, getStrategyName() + " :: Open position [ChildOrder] has been sent");
    }

    /**
     * @param instrument 交易标的
     */
    protected void closeAllPosition(Instrument instrument) {
        closeAllPosition(instrument, OrdType.Limited);
    }

    /**
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
            double offerPrice;
            if (position > 0)
                offerPrice = getLevel1Price(instrument, TrdDirection.Long);
            else
                offerPrice = getLevel1Price(instrument, TrdDirection.Short);
            closePosition(instrument, position, offerPrice, ordType);
        }
    }

    /**
     * @param instrument Instrument
     * @param offerPrice double
     */
    protected void closeAllPosition(Instrument instrument, double offerPrice) {
        final int position = getCurrentPosition(subAccountId, instrument);
        if (position == 0) {
            log.warn(
                    "{} :: Terminate execution close all positions, subAccountId==[{}], instrumentCode==[{}], position==[{}]",
                    getStrategyName(), subAccountId, instrument.getInstrumentCode(), position);
        } else {
            log.info("{} :: Execution close all positions, subAccountId==[{}], instrumentCode==[{}], position==[{}]",
                    getStrategyName(), subAccountId, instrument.getInstrumentCode(), position);
            closePosition(instrument, position, offerPrice, OrdType.Limited);
        }
    }

    /**
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
     * @param instrument 交易标的
     * @param offerQty   委托数量
     * @param offerPrice 委托价格
     */
    protected void closePosition(Instrument instrument, int offerQty, long offerPrice) {
        closePosition(instrument, offerQty, offerPrice, OrdType.Limited);
    }

    /**
     * @param instrument 交易标的
     * @param offerQty   委托数量
     * @param offerPrice 委托价格
     * @param ordType    订单类型
     */
    protected void closePosition(Instrument instrument, int offerQty, double offerPrice, OrdType ordType) {
        final ChildOrder childOrder = OrderKeeper.createAndSaveChildOrder(allocator, strategyId, subAccount, account,
                instrument, abs(offerQty), offerPrice, ordType, offerQty > 0 ? TrdDirection.Long : TrdDirection.Short,
                TrdAction.Close);

        childOrder.writeLog(log, "Close position generate [ChildOrder]");
        saveOrder(childOrder);

        getAdaptor(instrument).newOrder(childOrder.toNewOrder());
        childOrder.writeLog(log, "Close position [ChildOrder] has been sent");
    }

    /**
     * 订单写入策略
     *
     * @param order Order
     */
    private void saveOrder(@Nonnull Order order) {
        orders.put(order.getOrdSysId(), order);
    }

    /**
     * 由策略自行决定在交易不同Instrument时使用哪个Adaptor
     *
     * @param instrument Instrument
     * @return Adaptor
     */
    @AbstractFunction
    protected abstract Adaptor getAdaptor(@Nonnull Instrument instrument);

}
