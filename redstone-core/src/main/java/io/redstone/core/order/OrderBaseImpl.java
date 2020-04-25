package io.redstone.core.order;

import io.mercury.financial.instrument.Instrument;
import io.redstone.core.order.enums.OrdStatus;
import io.redstone.core.order.enums.OrdType;
import io.redstone.core.order.enums.TrdDirection;
import io.redstone.core.order.structure.OrdPrice;
import io.redstone.core.order.structure.OrdQty;
import io.redstone.core.order.structure.OrdTimestamps;
import io.redstone.core.order.utils.OrdSysIdGenerate;

public abstract class OrderImpl implements Order {

	/**
	 * ordSysId
	 */
	private long ordSysId;

	/**
	 * 策略Id
	 */
	private int strategyId;

	/**
	 * instrument
	 */
	private Instrument instrument;

	/**
	 * 数量
	 */
	private OrdQty ordQty;

	/**
	 * 价格
	 */
	private OrdPrice ordPrice;

	/**
	 * 订单方向
	 */
	private TrdDirection trdDirection;

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
	 * 子账户Id
	 */
	private int subAccountId;

	protected OrderImpl(int strategyId, Instrument instrument, OrdQty ordQty, OrdPrice ordPrice, TrdDirection trdDirection,
			OrdType ordType, int subAccountId) {
		this.ordSysId = OrdSysIdGenerate.next(strategyId);
		this.strategyId = strategyId;
		this.instrument = instrument;
		this.ordQty = ordQty;
		this.ordPrice = ordPrice;
		this.trdDirection = trdDirection;
		this.ordType = ordType;
		this.ordStatus = OrdStatus.PendingNew;
		this.ordTimestamps = OrdTimestamps.generate();
		this.subAccountId = subAccountId;

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
	public OrdQty ordQty() {
		return ordQty;
	}

	@Override
	public OrdPrice ordPrice() {
		return ordPrice;
	}

	@Override
	public TrdDirection trdDirection() {
		return trdDirection;
	}

	@Override
	public OrdType ordType() {
		return ordType;
	}

	@Override
	public OrdStatus ordStatus() {
		return ordStatus;
	}

	@Override
	public OrdTimestamps ordTimestamps() {
		return ordTimestamps;
	}

	@Override
	public OrdStatus updateOrdStatus(OrdStatus ordStatus) {
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

}
