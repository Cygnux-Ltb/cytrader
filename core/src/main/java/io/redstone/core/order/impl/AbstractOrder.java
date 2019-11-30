package io.redstone.core.order.impl;

import io.polaris.financial.instrument.Instrument;
import io.redstone.core.order.api.Order;
import io.redstone.core.order.enums.OrdSide;
import io.redstone.core.order.enums.OrdStatus;
import io.redstone.core.order.enums.OrdType;
import io.redstone.core.order.structure.OrdQtyPrice;
import io.redstone.core.order.structure.OrdTimestamps;
import io.redstone.core.order.structure.StopLoss;
import io.redstone.core.order.utils.OrdSysIdGenerate;

public abstract class AbstractOrder implements Order {

	private long ordSysId;
	private Instrument instrument;
	/**
	 * 数量和价格
	 */
	private OrdQtyPrice ordQtyPrice;
	/**
	 * 订单方向
	 */
	private OrdSide ordSide;
	/**
	 * 订单类型
	 */
	private OrdType ordType;
	/**
	 * 订单状态
	 */
	private OrdStatus ordStatus;
	/**
	 * 时间戳
	 */
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
			this.stopLoss = new StopLoss(ordSysId, ordSide.direction());
		else
			this.stopLoss = stopLoss;
	}

	protected AbstractOrder(Instrument instrument, OrdQtyPrice ordQtyPrice, OrdSide ordSide, OrdType ordType,
			int strategyId, int subAccountId) {
		this(instrument, ordQtyPrice, ordSide, ordType, strategyId, subAccountId, null);
	}

	@Override
	public long ordSysId() {
		return ordSysId;
	}

	@Override
	public Instrument instrument() {
		return instrument;
	}

	@Override
	public OrdQtyPrice qtyPrice() {
		return ordQtyPrice;
	}

	@Override
	public OrdSide side() {
		return ordSide;
	}

	@Override
	public OrdType type() {
		return ordType;
	}

	@Override
	public OrdStatus status() {
		return ordStatus;
	}

	@Override
	public OrdTimestamps timestamps() {
		return ordTimestamps;
	}

	@Override
	public OrdStatus status(OrdStatus ordStatus) {
		this.ordStatus = ordStatus;
		return this.ordStatus;
	}

	@Override
	public int strategyId() {
		return strategyId;
	}

	@Override
	public int subAccountId() {
		return subAccountId;
	}

	@Override
	public StopLoss stopLoss() {
		return stopLoss;
	}

}
