package io.ffreedom.redstone.actor;

import org.eclipse.collections.api.map.primitive.MutableIntLongMap;

import io.ffreedom.common.collect.MutableMaps;
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
	 * instrument最大持仓限制
	 */
	private MutableIntLongMap instrumentPositionsMaxLimit = MutableMaps.newIntLongHashMap();

	/**
	 * 当前instrument持仓数量
	 */
	private MutableIntLongMap instrumentPositions = MutableMaps.newIntLongHashMap();

	/**
	 * 当前subAccount持仓数量
	 */
	private MutableIntLongMap subAccountPositions = MutableMaps.newIntLongHashMap();

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
		switch (side) {
		case Buy:
		case MarginBuy:
			switch (status) {
			case PartiallyFilled:
			case Filled:
				break;
			default:
				break;
			}
			break;
		case Sell:
		case ShortSell:
			break;

		default:

			break;
		}

		long tradeQty = order.getTradeSet().lastTrade().getTradeQty();
		if (instrumentPositions.containsKey(instrumentId))
			modifyInstrumentPositions(instrumentId, tradeQty);
		else
			instrumentPositions.put(instrumentId, tradeQty);
		if (subAccountPositions.containsKey(subAccountId))
			modifySubAccountPositions(subAccountId, tradeQty);
		else
			subAccountPositions.put(subAccountId, tradeQty);
	}

	/**
	 * 增加仓位记录
	 * 
	 * @param order
	 */
	private void plusPositions(ChildOrder order) {
		int instrumentId = order.getInstrument().getInstrumentId();
		int subAccountId = order.getSubAccountId();
		long tradeQty = order.getTradeSet().lastTrade().getTradeQty();
		if (instrumentPositions.containsKey(instrumentId))
			modifyInstrumentPositions(instrumentId, tradeQty);
		else
			instrumentPositions.put(instrumentId, tradeQty);
		if (subAccountPositions.containsKey(subAccountId))
			modifySubAccountPositions(subAccountId, tradeQty);
		else
			subAccountPositions.put(subAccountId, tradeQty);
	}

	/**
	 * 减少仓位记录
	 * 
	 * @param order
	 */
	private void minusPositions(ChildOrder order) {
		int instrumentId = order.getInstrument().getInstrumentId();
		int subAccountId = order.getSubAccountId();
		long tradeQty = order.getTradeSet().lastTrade().getTradeQty();
		modifyInstrumentPositions(instrumentId, 0 - tradeQty);
		modifySubAccountPositions(subAccountId, 0 - tradeQty);
	}

	/**
	 * 修改instrumentId持仓数量
	 * 
	 * @param instrumentId
	 * @param qty
	 */
	private void modifyInstrumentPositions(int instrumentId, long qty) {
		instrumentPositions.put(instrumentId, instrumentPositions.get(instrumentId) + qty);

	}

	/**
	 * 修改subAccount持仓数量
	 * 
	 * @param subAccountId
	 * @param qty
	 */
	private void modifySubAccountPositions(int subAccountId, long qty) {
		subAccountPositions.put(subAccountId, subAccountPositions.get(subAccountId) + qty);
	}

	private long getPositionsCount(Instrument instrument) {
		return instrumentPositions.containsKey(instrument.getInstrumentId())
				? instrumentPositions.get(instrument.getInstrumentId())
				: 0;
	}

	private long getPositionsCount(int subAccount) {
		return subAccountPositions.containsKey(subAccount) ? subAccountPositions.get(subAccount) : 0;

	}

	private long getRemainPositions(Instrument instrument) {
		long positionsMaxLimit = getPositionsMaxLimit(instrument);
		return positionsMaxLimit > 0 ? positionsMaxLimit - getPositionsCount(instrument) : positionsMaxLimit;
	}

	private long getPositionsMaxLimit(Instrument instrument) {
		return instrumentPositionsMaxLimit.containsKey(instrument.getInstrumentId())
				? instrumentPositionsMaxLimit.get(instrument.getInstrumentId())
				: 0;
	}

	private void plusPositionsMaxLimit(Instrument instrument, long freedQty) {
		if (instrumentPositionsMaxLimit.containsKey(instrument.getInstrumentId())) {
			instrumentPositionsMaxLimit.put(instrument.getInstrumentId(),
					instrumentPositionsMaxLimit.get(instrument.getInstrumentId()) + freedQty);
		}
	}

	private void minusPositionsMaxLimit(Instrument instrument, long usedQty) {
		if (instrumentPositionsMaxLimit.containsKey(instrument.getInstrumentId())) {
			instrumentPositionsMaxLimit.put(instrument.getInstrumentId(),
					instrumentPositionsMaxLimit.get(instrument.getInstrumentId()) - usedQty);
		}
	}

	private static void main(String[] args) {

		System.out.println(10 - 7);
		System.out.println(10 + -7);

	}

}
