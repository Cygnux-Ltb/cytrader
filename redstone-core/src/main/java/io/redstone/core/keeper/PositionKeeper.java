package io.redstone.core.keeper;

import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.map.primitive.MutableLongIntMap;
import org.slf4j.Logger;

import io.mercury.common.collections.MutableMaps;
import io.mercury.common.io.Dumper;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.param.JointIdSupporter;
import io.mercury.financial.instrument.Instrument;
import io.redstone.core.order.enums.TrdDirection;
import io.redstone.core.order.specific.ChildOrder;

/**
 * 统一管理仓位信息<br>
 * 1更新仓位的入口<br>
 * 2记录子账号的仓位信息<br>
 * 
 * @author yellow013
 */

@NotThreadSafe
public final class PositionKeeper implements Dumper<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -23036653515185236L;

	/**
	 * Logger
	 */
	private static final Logger log = CommonLoggerFactory.getLogger(PositionKeeper.class);

	/**
	 * subAccount的instrument的最大长仓持仓限制<br>
	 * 使用jointId作为主键, 高位subAccountId, 低位instrumentId
	 */
	private static final MutableLongIntMap SubAccountInstrumentLongLimit = MutableMaps.newLongIntHashMap();

	/**
	 * subAccount的instrument最大短仓持仓限制<br>
	 * 使用jointId作为主键, 高位subAccountId, 低位instrumentId
	 */
	private static final MutableLongIntMap SubAccountInstrumentShortLimit = MutableMaps.newLongIntHashMap();

	/**
	 * subAccount的instrument持仓数量<br>
	 * 使用jointId作为主键, 高位subAccountId, 低位instrumentId
	 */
	private static final MutableLongIntMap SubAccountInstrumentPosition = MutableMaps.newLongIntHashMap();

	private PositionKeeper() {
	}

	private static long mergePositionKey(int subAccountId, Instrument instrument) {
		return JointIdSupporter.jointId(subAccountId, instrument.id());
	}

	/**
	 * 在初始化时设置子账户最大持仓限制<br>
	 * 需要分别设置多空两个方向的持仓限制
	 * 
	 * @param subAccountId
	 * @param instrumentId
	 * @param side
	 * @param limitQty
	 */
	public static void setPositionsLimit(int subAccountId, Instrument instrument, int limitLongQty, int limitShortQty) {
		long positionKey = mergePositionKey(subAccountId, instrument);
		SubAccountInstrumentLongLimit.put(positionKey, limitLongQty < 0 ? -limitLongQty : limitLongQty);
		log.info("PositionsKeeper :: Set long positions limit -> subAccountId==[{}], instrument -> {}, limitQty==[{}]",
				subAccountId, instrument, SubAccountInstrumentLongLimit.get(positionKey));
		SubAccountInstrumentShortLimit.put(positionKey, limitShortQty > 0 ? -limitShortQty : limitShortQty);
		log.info("PositionsKeeper :: Set short positions limit -> subAccountId==[{}], instrument -> {}, limitQty==[{}]",
				subAccountId, instrument, SubAccountInstrumentShortLimit.get(positionKey));
	}

	/**
	 * 根据已有持仓计算, 子账户的最大持仓限制<br>
	 * 
	 * @param subAccountId
	 * @param instrumentId
	 * @param side
	 * @return
	 */
	public int getPositionLimit(int subAccountId, Instrument instrument, TrdDirection direction) {
		long positionKey = mergePositionKey(subAccountId, instrument);
		int currentQty = SubAccountInstrumentPosition.get(positionKey);
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
	 * 根据子单状态变化更新持仓信息
	 * 
	 * @param order
	 */
	public static void updatePosition(ChildOrder order) {
		int subAccountId = order.subAccountId();
		Instrument instrument = order.instrument();
		long positionsKey = mergePositionKey(subAccountId, instrument);
		int currentPositions = SubAccountInstrumentPosition.get(positionsKey);
		int trdQty = order.lastTrdRecord().trdQty();
		log.info(
				"PositionsKeeper :: Update position, subAccountId==[{}], instrumentCode==[{}], currentPositions==[{}], trdQty==[{}]",
				subAccountId, instrument.code(), currentPositions, trdQty);
		SubAccountInstrumentPosition.put(positionsKey, currentPositions + trdQty);
	}
	
	public static int setCurrentPosition(int subAccountId, Instrument instrument, int positionQty) {
		long positionKey = mergePositionKey(subAccountId, instrument);
		int currentPosition = SubAccountInstrumentPosition.get(positionKey);
		log.info("PositionsKeeper :: Get position, subAccountId==[{}], instrumentCode==[{}], currentPositions==[{}]",
				subAccountId, instrument.code(), currentPosition);
		return currentPosition;
	}

	public static int getCurrentPosition(int subAccountId, Instrument instrument) {
		long positionKey = mergePositionKey(subAccountId, instrument);
		int currentPosition = SubAccountInstrumentPosition.get(positionKey);
		log.info("PositionsKeeper :: Get position, subAccountId==[{}], instrumentCode==[{}], currentPositions==[{}]",
				subAccountId, instrument.code(), currentPosition);
		return currentPosition;
	}

	public static void main(String[] args) {
		System.out.println(-10 + -5);
	}

	@Override
	public String dump() {
		// TODO Auto-generated method stub
		return null;
	}

}
