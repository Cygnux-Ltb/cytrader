package io.ffreedom.redstone.core.order;

import io.ffreedom.financial.Instrument;
import io.ffreedom.redstone.core.order.enums.OrdSide;
import io.ffreedom.redstone.core.order.enums.OrdStatus;
import io.ffreedom.redstone.core.order.enums.OrdType;

public abstract class SingleOrder implements Order {

	protected long ordSysId;
	protected Instrument instrument;
	protected OrdQtyPrice ordQtyPrice;
	protected OrdSide ordSide;
	protected OrdType ordType;
	protected OrdStatus ordStatus;
	protected OrdTimestamps ordTimestamps;
	protected int strategyId;
	protected int subAccountId;
	protected TradeList tradeList;

	public SingleOrder(Instrument instrument, OrdQtyPrice ordQtyPrice, OrdSide ordSide, OrdType ordType,
			OrdStatus ordStatus, OrdTimestamps ordTimestamps, int strategyId, int subAccountId) {
		this.ordSysId = OrdSysIdGenerate.next(strategyId);
		this.instrument = instrument;
		this.ordQtyPrice = ordQtyPrice;
		this.ordSide = ordSide;
		this.ordType = ordType;
		this.ordStatus = ordStatus;
		this.ordTimestamps = ordTimestamps;
		this.strategyId = strategyId;
		this.subAccountId = subAccountId;
		this.tradeList = new TradeList(ordSysId);
	}

	@Override
	public long getOrdSysId() {
		return ordSysId;
	}

	@Override
	public Instrument getInstrument() {
		return instrument;
	}

	@Override
	public OrdQtyPrice getQtyPrice() {
		return ordQtyPrice;
	}

	@Override
	public OrdSide getSide() {
		return ordSide;
	}

	@Override
	public OrdType getType() {
		return ordType;
	}

	@Override
	public OrdStatus getStatus() {
		return ordStatus;
	}

	@Override
	public OrdTimestamps getTimestamps() {
		return ordTimestamps;
	}

	@Override
	public Order setStatus(OrdStatus ordStatus) {
		this.ordStatus = ordStatus;
		return this;
	}

	@Override
	public int getStrategyId() {
		return strategyId;
	}

	@Override
	public int getSubAccountId() {
		return subAccountId;
	}

	@Override
	public TradeList getTradeList() {
		return tradeList;
	}

}
