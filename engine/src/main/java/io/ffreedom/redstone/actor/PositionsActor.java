package io.ffreedom.redstone.actor;

import org.eclipse.collections.api.map.primitive.MutableIntLongMap;
import org.eclipse.collections.api.map.primitive.MutableLongLongMap;

import io.ffreedom.common.collect.MutableMaps;
import io.ffreedom.common.utils.JointIdUtil;
import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.redstone.core.order.enums.OrdSide;
import io.ffreedom.redstone.core.order.enums.OrdStatus;
import io.ffreedom.redstone.core.order.impl.ChildOrder;

/**
 * 统一管理仓位信息<br>
 * 1更新仓位的入口<br>
 * 2...<br>
 * 
 * @author yellow013
 */
public class PositionsActor {

	/**
	 * subAccount的instrument的最大长仓持仓限制<br>
	 * 使用jointId作为主键, 高位subAccountId, 低位instrumentId
	 */
	private MutableLongLongMap subAccountInstrumentLongPositionsMaxLimit = MutableMaps.newLongLongHashMap();

	/**
	 * subAccount的instrument最大短仓持仓限制<br>
	 * 使用jointId作为主键, 高位subAccountId, 低位instrumentId
	 */
	private MutableLongLongMap subAccountInstrumentShortPositionsMaxLimit = MutableMaps.newLongLongHashMap();

	/**
	 * subAccount的instrument持仓数量<br>
	 * 使用jointId作为主键, 高位subAccountId, 低位instrumentId
	 */
	private MutableLongLongMap subAccountInstrumentPositions = MutableMaps.newLongLongHashMap();

	public final static PositionsActor Singleton = new PositionsActor();

	private PositionsActor() {
	}

	public void onPositions() {
	}

	public void onChildOrder(ChildOrder order) {
		int instrumentId = order.getInstrument().getInstrumentId();
		int subAccountId = order.getSubAccountId();
		OrdSide side = order.getSide();
		OrdStatus status = order.getStatus();
		long jointId = JointIdUtil.jointId(subAccountId, instrumentId);
		long tradeQty = order.getTradeSet().lastTrade().getTradeQty();
		switch (side.direction()) {
		case Long:
			switch (status) {
			case PartiallyFilled:

			case Filled:
				break;
			default:
				break;
			}
			break;
		case Short:
			break;
		default:
			throw new IllegalArgumentException("OrdSysId -> " + order.getOrdSysId());
		}

	}

	private static void main(String[] args) {

	}

}
