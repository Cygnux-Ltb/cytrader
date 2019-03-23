package io.ffreedom.redstone.actor;

import org.eclipse.collections.api.map.primitive.MutableIntDoubleMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntDoubleHashMap;

import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.redstone.core.order.Order;

public class PositionsActor {

	private MutableIntDoubleMap instrumentPositionsMaxLimit = new IntDoubleHashMap();

	private MutableIntDoubleMap instrumentPositions = new IntDoubleHashMap();

	private MutableIntDoubleMap subAccountPositions = new IntDoubleHashMap();

	public final static PositionsActor Singleton = new PositionsActor();

	private PositionsActor() {
	}

	/**
	 * 增加仓位记录
	 * 
	 * @param order
	 */
	public void plusPositions(Order order) {
		int instrumentId = order.getInstrument().getInstrumentId();
		int subAccountId = order.getSubAccountId();
		double tradeQty = order.getTradeSet().lastTrade().getTradeQty();
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
	public void minusPositions(Order order) {
		int instrumentId = order.getInstrument().getInstrumentId();
		int subAccountId = order.getSubAccountId();
		double tradeQty = order.getTradeSet().lastTrade().getTradeQty();
		modifyInstrumentPositions(instrumentId, 0 - tradeQty);
		modifySubAccountPositions(subAccountId, 0 - tradeQty);
	}

	/**
	 * 修改instrumentId持仓数量
	 * 
	 * @param instrumentId
	 * @param qty
	 */
	private void modifyInstrumentPositions(int instrumentId, double qty) {
		instrumentPositions.put(instrumentId, instrumentPositions.get(instrumentId) + qty);

	}

	/**
	 * 修改subAccount持仓数量
	 * 
	 * @param subAccountId
	 * @param qty
	 */
	private void modifySubAccountPositions(int subAccountId, double qty) {
		subAccountPositions.put(subAccountId, subAccountPositions.get(subAccountId) + qty);
	}

	public double getPositionsCount(Instrument instrument) {
		return instrumentPositions.containsKey(instrument.getInstrumentId())
				? instrumentPositions.get(instrument.getInstrumentId())
				: 0;
	}

	public double getPositionsCount(int subAccount) {
		return subAccountPositions.containsKey(subAccount) ? subAccountPositions.get(subAccount) : 0;

	}

	public double getRemainPositions(Instrument instrument) {
		double positionsMaxLimit = getPositionsMaxLimit(instrument);
		return positionsMaxLimit > 0 ? positionsMaxLimit - getPositionsCount(instrument) : positionsMaxLimit;
	}

	public double getPositionsMaxLimit(Instrument instrument) {
		return instrumentPositionsMaxLimit.containsKey(instrument.getInstrumentId())
				? instrumentPositionsMaxLimit.get(instrument.getInstrumentId())
				: 0;
	}

	public void plusPositionsMaxLimit(Instrument instrument, double freedQty) {
		if (instrumentPositionsMaxLimit.containsKey(instrument.getInstrumentId())) {
			instrumentPositionsMaxLimit.put(instrument.getInstrumentId(),
					instrumentPositionsMaxLimit.get(instrument.getInstrumentId()) + freedQty);
		}
	}

	public void minusPositionsMaxLimit(Instrument instrument, double usedQty) {
		if (instrumentPositionsMaxLimit.containsKey(instrument.getInstrumentId())) {
			instrumentPositionsMaxLimit.put(instrument.getInstrumentId(),
					instrumentPositionsMaxLimit.get(instrument.getInstrumentId()) - usedQty);
		}
	}

	public static void main(String[] args) {

		System.out.println(0 - 7);
		System.out.println(10 + -7);

	}

}
