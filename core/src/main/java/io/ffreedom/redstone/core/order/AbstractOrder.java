package io.ffreedom.redstone.core.order;

import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.redstone.core.order.api.Order;
import io.ffreedom.redstone.core.order.enums.OrdSide;
import io.ffreedom.redstone.core.order.enums.OrdStatus;
import io.ffreedom.redstone.core.order.enums.OrdType;
import io.ffreedom.redstone.core.order.structure.OrdQtyPrice;
import io.ffreedom.redstone.core.order.structure.OrdTimestamps;
import io.ffreedom.redstone.core.order.structure.StopLoss;
import io.ffreedom.redstone.core.order.structure.TradeSet;
import io.ffreedom.redstone.core.order.utils.OrdSysIdGenerate;

public abstract class AbstractOrder implements Order {

	private long ordSysId;
	private Instrument instrument;
	private OrdQtyPrice ordQtyPrice;
	private OrdSide ordSide;
	private OrdType ordType;
	private OrdStatus ordStatus;
	private OrdTimestamps ordTimestamps;
	/**
	 * 策略Id
	 */
	private int strategyId;
	/**
	 * 子账户Id
	 */
	private int subAccountId;
	/**
	 * 止损
	 */
	private StopLoss stopLoss;
	/**
	 * 成交列表
	 */
	private TradeSet tradeSet;

	protected AbstractOrder(Instrument instrument, OrdQtyPrice ordQtyPrice, OrdSide ordSide, OrdType ordType,
			int strategyId, int subAccountId, StopLoss stopLoss) {
		this.ordSysId = OrdSysIdGenerate.next(strategyId);
		this.instrument = instrument;
		this.ordQtyPrice = ordQtyPrice;
		this.ordSide = ordSide;
		this.ordType = ordType;
		this.ordStatus = OrdStatus.PendingNew;
		this.ordTimestamps = OrdTimestamps.generate();
		this.strategyId = strategyId;
		this.subAccountId = subAccountId;
		if (stopLoss == null)
			this.stopLoss = new StopLoss(ordSysId);
		else
			this.stopLoss = stopLoss;
		this.tradeSet = new TradeSet(ordSysId);
	}

	protected AbstractOrder(Instrument instrument, OrdQtyPrice ordQtyPrice, OrdSide ordSide, OrdType ordType,
			int strategyId, int subAccountId) {
		this(instrument, ordQtyPrice, ordSide, ordType, strategyId, subAccountId, null);
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
	public void setStatus(OrdStatus ordStatus) {
		this.ordStatus = ordStatus;
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
	public TradeSet getTradeSet() {
		return tradeSet;
	}

	@Override
	public StopLoss getStopLoss() {
		return stopLoss;
	}

}
