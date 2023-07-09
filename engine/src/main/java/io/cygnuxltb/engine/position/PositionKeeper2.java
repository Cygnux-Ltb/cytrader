package io.cygnuxltb.engine.position;

import io.horizon.market.instrument.Instrument;
import io.horizon.trader.order.ChildOrder;
import io.horizon.trader.order.enums.TrdDirection;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.functional.Formatter;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.util.BitOperator;
import io.mercury.serialization.json.JsonWrapper;
import org.eclipse.collections.api.map.primitive.MutableLongIntMap;
import org.slf4j.Logger;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;

import static java.lang.Math.abs;

/**
 * 统一管理子账户仓位信息<br>
 * 1更新仓位的入口<br>
 * 2记录子账号的仓位信息<br>
 *
 * @author yellow013
 */
@NotThreadSafe
public final class PositionKeeper2 implements Serializable, Formatter<String> {

    @Serial
    private static final long serialVersionUID = -23036653515185236L;

    /**
     * Logger
     */
    private static final Logger log = Log4j2LoggerFactory.getLogger(PositionKeeper2.class);

    /**
     * [subAccount]的[instrument]持仓数量<br>
     * 使用mergeId作为主键<br>
     * 高位subAccountId<br>
     * 低位instrumentId
     */
    private static final MutableLongIntMap SubAccountInstrumentPos = MutableMaps.newLongIntHashMap();

    /**
     * [subAccount]的[instrument]最大多仓持仓限制<br>
     * 使用mergeId作为主键<br>
     * 高位subAccountId<br>
     * 低位instrumentId
     */
    private static final MutableLongIntMap SubAccountInstrumentLongLimit = MutableMaps.newLongIntHashMap();

    /**
     * [subAccount]的[instrument]最大空仓持仓限制<br>
     * 使用mergeId作为主键<br>
     * 高位subAccountId<br>
     * 低位instrumentId
     */
    private static final MutableLongIntMap SubAccountInstrumentShortLimit = MutableMaps.newLongIntHashMap();

    /**
     * [account]的[instrument]持仓数量<br>
     * 使用mergeId作为主键<br>
     * 高位accountId<br>
     * 低位instrumentId
     */
    private static final MutableLongIntMap AccountInstrumentPos = MutableMaps.newLongIntHashMap();

    /**
     * [account]的[instrument]最大多仓持仓限制<br>
     * 使用mergeId作为主键<br>
     * 高位accountId<br>
     * 低位instrumentId
     */
    private static final MutableLongIntMap AccountInstrumentLongLimit = MutableMaps.newLongIntHashMap();

    /**
     * [account]的[instrument]最大空仓持仓限制<br>
     * 使用mergeId作为主键<br>
     * 高位accountId<br>
     * 低位instrumentId
     */
    private static final MutableLongIntMap AccountInstrumentShortLimit = MutableMaps.newLongIntHashMap();

    private PositionKeeper2() {
    }

    /**
     * 合并持仓主键
     *
     * @param highPos    subAccountId or accountId
     * @param instrument Instrument impl
     * @return long
     */
    private static long mergePositionsKey(int highPos, Instrument instrument) {
        return BitOperator.merge(highPos, instrument.getInstrumentId());
    }

    /**
     * 设置子账户最大持仓限制, 需要分别设置多空两个方向的持仓限制
     *
     * @param subAccountId  子账户ID
     * @param instrument    交易标的
     * @param longLimitQty  多仓限制
     * @param shortLimitQty 空仓限制
     */
    public static void setSubAccountPositionsLimit(int subAccountId, Instrument instrument, int longLimitQty,
                                                   int shortLimitQty) {
        long key = mergePositionsKey(subAccountId, instrument);
        SubAccountInstrumentLongLimit.put(key, abs(longLimitQty));
        log.info("Set long positions limit -> subAccountId==[{}], instrument -> {}, longLimitQty==[{}]", subAccountId,
                instrument, SubAccountInstrumentLongLimit.get(key));
        SubAccountInstrumentShortLimit.put(key, -abs(shortLimitQty));
        log.info("Set short positions limit -> subAccountId==[{}], instrument -> {}, shortLimitQty==[{}]", subAccountId,
                instrument, SubAccountInstrumentShortLimit.get(key));
    }

    /**
     * 设置账户最大持仓限制, 需要分别设置多空两个方向的持仓限制
     *
     * @param accountId     账户ID
     * @param instrument    交易标的
     * @param longLimitQty  多仓限制
     * @param shortLimitQty 空仓限制
     */
    public static void setAccountPositionsLimit(int accountId, Instrument instrument, int longLimitQty,
                                                int shortLimitQty) {
        long key = mergePositionsKey(accountId, instrument);
        AccountInstrumentLongLimit.put(key, abs(longLimitQty));
        log.info("Set long positions limit -> accountId==[{}], instrument -> {}, longLimitQty==[{}]", accountId,
                instrument, AccountInstrumentLongLimit.get(key));
        AccountInstrumentShortLimit.put(key, -abs(shortLimitQty));
        log.info("Set short positions limit -> accountId==[{}], instrument -> {}, shortLimitQty==[{}]", accountId,
                instrument, AccountInstrumentShortLimit.get(key));
    }

    /**
     * 根据已有持仓计算子账户当前持仓限制
     *
     * @param subAccountId 子账户ID
     * @param instrument   交易标的
     * @param direction    交易方向
     * @return int
     */
    public static int getCurrentSubAccountPositionLimit(int subAccountId, Instrument instrument,
                                                        TrdDirection direction) {
        long key = mergePositionsKey(subAccountId, instrument);
        int currentQty = SubAccountInstrumentPos.get(key);
        return switch (direction) {
            case Long -> SubAccountInstrumentLongLimit.get(key) - currentQty;
            case Short -> SubAccountInstrumentShortLimit.get(key) - currentQty;
            default -> 0;
        };
    }

    /**
     * 根据已有持仓计算账户当前持仓限制
     *
     * @param accountId  账户ID
     * @param instrument 交易标的
     * @param direction  交易方向
     * @return int
     */
    public static int getCurrentAccountPositionLimit(int accountId, Instrument instrument, TrdDirection direction) {
        long key = mergePositionsKey(accountId, instrument);
        int currentQty = AccountInstrumentPos.get(key);
        return switch (direction) {
            case Long -> AccountInstrumentLongLimit.get(key) - currentQty;
            case Short -> AccountInstrumentShortLimit.get(key) - currentQty;
            default -> 0;
        };
    }

    /**
     * 获取子账户当前头寸数量
     *
     * @param subAccountId 子账户ID
     * @param instrument   交易标的
     * @return int
     */
    public static int getCurrentSubAccountPosition(int subAccountId, Instrument instrument) {
        long positionKey = mergePositionsKey(subAccountId, instrument);
        int currentPosition = SubAccountInstrumentPos.get(positionKey);
        log.info("Get current position, subAccountId==[{}], instrumentCode==[{}], currentPosition==[{}]", subAccountId,
                instrument.getInstrumentCode(), currentPosition);
        return currentPosition;
    }

    /**
     * 获取账户当前头寸数量
     *
     * @param accountId  账户ID
     * @param instrument 交易标的
     * @return int
     */
    public static int getCurrentAccountPosition(int accountId, Instrument instrument) {
        long positionKey = mergePositionsKey(accountId, instrument);
        int currentPosition = AccountInstrumentPos.get(positionKey);
        log.info("Get current position, accountId==[{}], instrumentCode==[{}], currentPosition==[{}]", accountId,
                instrument.getInstrumentCode(), currentPosition);
        return currentPosition;
    }

    private static final String UpdatePosLogTemplate = "Update position, subAccountId==[{}], instrumentCode==[{}], "
            + "beforePos==[{}], trdQty==[{}], currentPos==[{}]";

    /**
     * 根据子单状态变化更新持仓信息
     *
     * @param order 子订单
     */
    public static void updateCurrentPosition(ChildOrder order) {
        int subAccountId = order.getSubAccountId();
        Instrument instrument = order.getInstrument();
        int trdQty = order.getLastRecord().tradeQty();
        switch (order.getDirection()) {
            case Long -> {
                switch (order.getAction()) {
                    case Open -> trdQty = abs(trdQty);
                    case Close, CloseToday, CloseYesterday -> trdQty = -abs(trdQty);
                    case Invalid ->
                            log.error("Order action is [Invalid], subAccountId==[{}], ordSysId==[{}], instrumentCode==[{}]",
                                    subAccountId, order.getOrdSysId(), instrument.getInstrumentCode());
                }
            }
            case Short -> {
                switch (order.getAction()) {
                    case Open -> trdQty = -abs(trdQty);
                    case Close, CloseToday, CloseYesterday -> trdQty = abs(trdQty);
                    case Invalid ->
                            log.error("Order action is [Invalid], subAccountId==[{}], ordSysId==[{}], instrumentCode==[{}]",
                                    subAccountId, order.getOrdSysId(), instrument.getInstrumentCode());
                }
            }
            case Invalid ->
                    log.error("Order direction is [Invalid], subAccountId==[{}], ordSysId==[{}], instrumentCode==[{}]",
                            subAccountId, order.getOrdSysId(), instrument.getInstrumentCode());
        }
        long posKey = mergePositionsKey(subAccountId, instrument);
        int beforePos = SubAccountInstrumentPos.get(posKey);
        int currentPos = beforePos + trdQty;
        SubAccountInstrumentPos.put(posKey, currentPos);
        log.info(UpdatePosLogTemplate, subAccountId, instrument.getInstrumentCode(), beforePos, trdQty, currentPos);
    }

    private static final String AddPosLogTemplate = "Add current position, subAccountId==[{}], "
            + "instrumentCode==[{}], beforePos==[{}], qty==[{}], currentPos==[{}]";

    /**
     * 添加当前头寸
     *
     * @param subAccountId 子账户ID
     * @param instrument   交易标的
     * @param direction    方向
     * @param qty          仓位数量
     */
    public static void addCurrentPosition(int subAccountId, Instrument instrument, TrdDirection direction, int qty) {
        switch (direction) {
            case Long -> qty = abs(qty);
            case Short -> qty = -abs(qty);
            case Invalid ->
                    log.warn("Add position, direction is [Invalid], subAccountId==[{}], instrumentCode==[{}], qty==[{}]",
                            subAccountId, instrument.getInstrumentCode(), qty);
        }
        long key = mergePositionsKey(subAccountId, instrument);
        int beforePos = SubAccountInstrumentPos.get(key);
        int currentPos = beforePos + qty;
        SubAccountInstrumentPos.put(key, currentPos);
        log.info(AddPosLogTemplate, subAccountId, instrument.getInstrumentCode(), beforePos, qty, currentPos);
    }

    @Override
    public String toString() {
        var map = new HashMap<String, Object>();
        map.put("SubAccountInstrumentPos", SubAccountInstrumentPos);
        map.put("SubAccountInstrumentLongLimit", SubAccountInstrumentLongLimit);
        map.put("SubAccountInstrumentShortLimit", SubAccountInstrumentShortLimit);
        return JsonWrapper.toJson(map);
    }

    @Override
    public String format() {
        return toString();
    }

}
