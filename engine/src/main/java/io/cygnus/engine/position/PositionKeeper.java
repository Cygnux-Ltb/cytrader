package io.cygnus.engine.position;

import static java.lang.Math.abs;

import java.io.Serializable;

import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.map.primitive.MutableLongIntMap;
import org.slf4j.Logger;

import io.horizon.market.instrument.Instrument;
import io.horizon.trader.order.ChildOrder;
import io.horizon.trader.order.attr.TrdDirection;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.functional.Formattable;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.util.BitOperator;

/**
 * 统一管理子账户仓位信息<br>
 * 1更新仓位的入口<br>
 * 2记录子账号的仓位信息<br>
 * 
 * @author yellow013
 */

@NotThreadSafe
public final class PositionKeeper implements Serializable, Formattable<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -23036653515185236L;

	/**
	 * Logger
	 */
	private static final Logger log = CommonLoggerFactory.getLogger(PositionKeeper.class);

	/**
	 * [subAccount]的[instrument]最大多仓持仓限制<br>
	 * 使用jointId作为主键<br>
	 * 高位subAccountId<br>
	 * 低位instrumentId
	 */
	private static final MutableLongIntMap SubAccountInstrumentLongLimit = MutableMaps.newLongIntHashMap();

	/**
	 * [subAccount]的[instrument]最大空仓持仓限制<br>
	 * 使用jointId作为主键<br>
	 * 高位subAccountId<br>
	 * 低位instrumentId
	 */
	private static final MutableLongIntMap SubAccountInstrumentShortLimit = MutableMaps.newLongIntHashMap();

	/**
	 * [subAccount]的[instrument]持仓数量<br>
	 * 使用jointId作为主键<br>
	 * 高位subAccountId<br>
	 * 低位instrumentId
	 */
	private static final MutableLongIntMap SubAccountInstrumentPos = MutableMaps.newLongIntHashMap();

	private PositionKeeper() {
	}

	/**
	 * 合并持仓主键
	 * 
	 * @param subAccountId
	 * @param instrument
	 * @return
	 */
	private static long mergePositionKey(int subAccountId, Instrument instrument) {
		return BitOperator.merge(subAccountId, instrument.getInstrumentId());
	}

	/**
	 * 在初始化时设置子账户最大持仓限制<br>
	 * 需要分别设置多空两个方向的持仓限制
	 * 
	 * @param subAccountId  子账户ID
	 * @param instrument    交易标的
	 * @param limitLongQty  多仓限制
	 * @param limitShortQty 空仓限制
	 */
	public static void setPositionsLimit(int subAccountId, Instrument instrument, int limitLongQty, int limitShortQty) {
		long positionKey = mergePositionKey(subAccountId, instrument);
		SubAccountInstrumentLongLimit.put(positionKey, abs(limitLongQty));
		log.info("Set long positions limit -> subAccountId==[{}], instrument -> {}, longLimitQty==[{}]", subAccountId,
				instrument, SubAccountInstrumentLongLimit.get(positionKey));
		SubAccountInstrumentShortLimit.put(positionKey, -abs(limitShortQty));
		log.info("Set short positions limit -> subAccountId==[{}], instrument -> {}, shortLimitQty==[{}]", subAccountId,
				instrument, SubAccountInstrumentShortLimit.get(positionKey));
	}

	/**
	 * 根据已有持仓计算, 子账户的最大持仓限制<br>
	 * 
	 * @param subAccountId 子账户ID
	 * @param instrument   交易标的
	 * @param direction    交易方向
	 * @return
	 */
	public static int getCurrentPositionLimit(int subAccountId, Instrument instrument, TrdDirection direction) {
		long positionKey = mergePositionKey(subAccountId, instrument);
		int currentQty = SubAccountInstrumentPos.get(positionKey);
		switch (direction) {
		case Long:
			return SubAccountInstrumentLongLimit.get(positionKey) - currentQty;
		case Short:
			return SubAccountInstrumentShortLimit.get(positionKey) - currentQty;
		default:
			return 0;
		}
	}

	/**
	 * 获取当前头寸数量
	 * 
	 * @param subAccountId 子账户ID
	 * @param instrument   交易标的
	 * @return
	 */
	public static int getCurrentPosition(int subAccountId, Instrument instrument) {
		long positionKey = mergePositionKey(subAccountId, instrument);
		int currentPosition = SubAccountInstrumentPos.get(positionKey);
		log.info("Get current position, subAccountId==[{}], instrumentCode==[{}], currentPosition==[{}]", subAccountId,
				instrument.getInstrumentCode(), currentPosition);
		return currentPosition;
	}

	/**
	 * 根据子单状态变化更新持仓信息
	 * 
	 * @param order 子订单
	 */
	public static void updateCurrentPosition(ChildOrder order) {
		int subAccountId = order.getSubAccountId();
		Instrument instrument = order.getInstrument();
		int trdQty = order.getLastTrdRecord().getTrdQty();
		switch (order.getDirection()) {
		case Long:
			switch (order.getAction()) {
			case Open:
				trdQty = abs(trdQty);
				break;
			case Close:
			case CloseToday:
			case CloseYesterday:
				trdQty = -abs(trdQty);
				break;
			case Invalid:
				log.error("Order action is [Invalid], subAccountId==[{}], ordId==[{}], instrumentCode==[{}]",
						subAccountId, order.getOrdSysId(), instrument.getInstrumentCode());
				break;
			}
			break;
		case Short:
			switch (order.getAction()) {
			case Open:
				trdQty = -abs(trdQty);
				break;
			case Close:
			case CloseToday:
			case CloseYesterday:
				trdQty = abs(trdQty);
				break;
			case Invalid:
				log.error("Order action is [Invalid], subAccountId==[{}], ordId==[{}], instrumentCode==[{}]",
						subAccountId, order.getOrdSysId(), instrument.getInstrumentCode());
				break;
			}
			break;
		case Invalid:
			log.error("Order direction is [Invalid], subAccountId==[{}], ordId==[{}], instrumentCode==[{}]",
					subAccountId, order.getOrdSysId(), instrument.getInstrumentCode());
			break;
		}
		long key = mergePositionKey(subAccountId, instrument);
		int currentQty = SubAccountInstrumentPos.get(key);
		log.info("Update position, subAccountId==[{}], instrumentCode==[{}], currentQty==[{}], trdQty==[{}]",
				subAccountId, instrument.getInstrumentCode(), currentQty, trdQty);
		SubAccountInstrumentPos.put(key, currentQty + trdQty);
	}

	/**
	 * 添加当前头寸
	 * 
	 * @param subAccountId 子账户ID
	 * @param instrument   交易标的
	 * @param qty          仓位数量
	 */
	public static void addCurrentPosition(int subAccountId, Instrument instrument, TrdDirection direction, int qty) {
		long positionKey = mergePositionKey(subAccountId, instrument);
		int beforePosition = SubAccountInstrumentPos.get(positionKey);
		switch (direction) {
		case Long:
			qty = abs(qty);
			break;
		case Short:
			qty = -abs(qty);
			break;
		case Invalid:
			log.info(
					"Add current position, direction is [Invalid], subAccountId==[{}], instrumentCode==[{}], qty==[{}]",
					subAccountId, instrument.getInstrumentCode(), qty);
			break;
		}
		SubAccountInstrumentPos.put(positionKey, beforePosition + qty);
		log.info(
				"Add current position, subAccountId==[{}], instrumentCode==[{}], beforePosition==[{}], qty==[{}], afterPosition==[{}]",
				subAccountId, instrument.getInstrumentCode(), beforePosition, qty,
				SubAccountInstrumentPos.get(positionKey));
	}

	@Override
	public String toString() {
		return "";
	}

	@Override
	public String format() {
		// TODO Auto-generated method stub
		return null;
	}

}
