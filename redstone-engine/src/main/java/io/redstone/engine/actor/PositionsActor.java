package io.redstone.engine.actor;

import org.eclipse.collections.api.map.primitive.MutableLongLongMap;
import org.slf4j.Logger;

import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.param.JointIdUtil;
import io.redstone.core.order.enums.OrdSide;
import io.redstone.core.order.impl.ChildOrder;

/**
 * 统一管理仓位信息<br>
 * 1更新仓位的入口<br>
 * 2记录子账号的仓位信息<br>
 * 
 * @author yellow013
 */
public class PositionsActor {

	private Logger Log = CommonLoggerFactory.getLogger(getClass());

	/**
	 * subAccount的instrument的最大长仓持仓限制<br>
	 * 使用jointId作为主键, 高位subAccountId, 低位instrumentId
	 */
	private MutableLongLongMap subAccountInstrumentLongLimit = MutableMaps.newLongLongHashMap();

	/**
	 * subAccount的instrument最大短仓持仓限制<br>
	 * 使用jointId作为主键, 高位subAccountId, 低位instrumentId
	 */
	private MutableLongLongMap subAccountInstrumentShortLimit = MutableMaps.newLongLongHashMap();

	/**
	 * subAccount的instrument持仓数量<br>
	 * 使用jointId作为主键, 高位subAccountId, 低位instrumentId
	 */
	private MutableLongLongMap subAccountInstrumentPositions = MutableMaps.newLongLongHashMap();

	public final static PositionsActor Singleton = new PositionsActor();

	private PositionsActor() {
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
	public void setSubAccountPositionsLimit(int subAccountId, int instrumentId, long limitLongQty, long limitShortQty) {
		long jointId = JointIdUtil.jointId(subAccountId, instrumentId);
		subAccountInstrumentLongLimit.put(jointId, limitLongQty < 0 ? -limitLongQty : limitLongQty);
		Log.info("Set long positions limit -> subAccountId==[{}], instrumentId==[{}], limitQty==[{}]", subAccountId,
				instrumentId, subAccountInstrumentLongLimit.get(jointId));
		subAccountInstrumentShortLimit.put(jointId, limitShortQty > 0 ? -limitShortQty : limitShortQty);
		Log.info("Set short positions limit -> subAccountId==[{}], instrumentId==[{}], limitQty==[{}]", subAccountId,
				instrumentId, subAccountInstrumentShortLimit.get(jointId));
	}

	/**
	 * 根据已有持仓计算, 子账户的最大持仓限制<br>
	 * 
	 * @param subAccountId
	 * @param instrumentId
	 * @param side
	 * @return
	 */
	public long getSubAccountPositionsLimit(int subAccountId, int instrumentId, OrdSide side) {
		long jointId = JointIdUtil.jointId(subAccountId, instrumentId);
		long currentQty = subAccountInstrumentPositions.get(jointId);
		switch (side.direction()) {
		case Long:
			return subAccountInstrumentLongLimit.get(jointId) - currentQty;
		case Short:
			return subAccountInstrumentShortLimit.get(jointId) - currentQty;
		default:
			return 0L;
		}
	}

	/**
	 * 根据子单状态变化更新持仓信息
	 * 
	 * @param order
	 */
	public void onChildOrder(ChildOrder order) {
		long jointId = JointIdUtil.jointId(order.instrument().id(), order.subAccountId());
		subAccountInstrumentPositions.put(jointId,
				subAccountInstrumentPositions.get(jointId) + order.ordTradeSet().lastTrade().get().tradeQty());
	}

	public static void main(String[] args) {
		System.out.println(-10 - 5);
	}

}
