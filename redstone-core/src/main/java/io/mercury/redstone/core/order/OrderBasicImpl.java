package io.mercury.redstone.core.order;

import org.slf4j.Logger;

import io.mercury.financial.instrument.Instrument;
import io.mercury.redstone.core.order.enums.OrdStatus;
import io.mercury.redstone.core.order.enums.OrdType;
import io.mercury.redstone.core.order.enums.TrdDirection;
import io.mercury.redstone.core.order.structure.OrdPrice;
import io.mercury.redstone.core.order.structure.OrdQty;
import io.mercury.redstone.core.order.structure.OrdTimestamp;

public abstract class OrderBasicImpl implements Order {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3444258095612091354L;

	/**
	 * uniqueId
	 */
	private final long uniqueId;

	/**
	 * 策略Id
	 */
	private final int strategyId;

	/**
	 * 子账户Id
	 */
	private final int subAccountId;

	/**
	 * 实际账户Id
	 */
	private final int accountId;

	/**
	 * instrument
	 */
	private final Instrument instrument;

	/**
	 * 数量
	 */
	private final OrdQty qty;

	/**
	 * 价格
	 */
	private final OrdPrice price;

	/**
	 * 订单类型
	 */
	private final OrdType type;

	/**
	 * 订单方向
	 */
	private final TrdDirection direction;

	/**
	 * 时间戳
	 */
	private final OrdTimestamp timestamp;

	/**
	 * 订单状态(可变)
	 */
	private OrdStatus status;

	/**
	 * 订单备注(可变)
	 */
	private String remark;

	private static final String DefRemark = "NONE";

	protected OrderBasicImpl(long uniqueId, int strategyId, int subAccountId, int accountId, Instrument instrument,
			OrdQty qty, OrdPrice price, OrdType type, TrdDirection direction) {
		this.uniqueId = uniqueId;
		this.strategyId = strategyId;
		this.subAccountId = subAccountId;
		this.accountId = accountId;
		this.instrument = instrument;
		this.qty = qty;
		this.price = price;
		this.type = type;
		this.direction = direction;
		this.timestamp = OrdTimestamp.generate();
		this.status = OrdStatus.PendingNew;
		this.remark = DefRemark;
	}

	@Override
	public long uniqueId() {
		return uniqueId;
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
	public int accountId() {
		return accountId;
	}

	@Override
	public Instrument instrument() {
		return instrument;
	}

	@Override
	public OrdQty qty() {
		return qty;
	}

	@Override
	public OrdPrice price() {
		return price;
	}

	@Override
	public OrdType type() {
		return type;
	}

	@Override
	public OrdTimestamp timestamp() {
		return timestamp;
	}

	@Override
	public TrdDirection direction() {
		return direction;
	}

	@Override
	public OrdStatus status() {
		return status;
	}

	@Override
	public void setStatus(OrdStatus status) {
		this.status = status;
	}

	@Override
	public String remark() {
		return remark;
	}

	@Override
	public void setRemark(String remark) {
		this.remark = remark;
	}

	private static final String OrderOutputText = "{} :: {}, Order : uniqueId==[{}], status==[{}], "
			+ "direction==[{}], type==[{}], instrument -> {}, price -> {}, qty -> {}, timestamp -> {}";

	@Override
	public void writeLog(Logger log, String objName, String msg) {
		log.info(OrderOutputText, objName, msg, uniqueId(), status(), direction(), type(), instrument(), price(), qty(),
				timestamp());
	}

}
