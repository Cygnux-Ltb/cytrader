package io.mercury.redstone.core.order;

import org.slf4j.Logger;

import io.mercury.financial.instrument.Instrument;
import io.mercury.redstone.core.order.enums.OrdStatus;
import io.mercury.redstone.core.order.enums.OrdType;
import io.mercury.redstone.core.order.enums.TrdDirection;
import io.mercury.redstone.core.order.structure.OrdPrice;
import io.mercury.redstone.core.order.structure.OrdQty;
import io.mercury.redstone.core.order.structure.OrdTimestamp;

public abstract class OrderBaseImpl implements Order {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3444258095612091354L;

	/**
	 * ordSysId
	 */
	private final long ordSysId;

	/**
	 * 策略Id
	 */
	private final int strategyId;

	/**
	 * 子账户Id
	 */
	private int accountId;

	/**
	 * 子账户Id
	 */
	private final int subAccountId;

	/**
	 * instrument
	 */
	private final Instrument instrument;

	/**
	 * 数量
	 */
	private final OrdQty ordQty;

	/**
	 * 价格
	 */
	private final OrdPrice ordPrice;

	/**
	 * 订单类型
	 */
	private final OrdType ordType;

	/**
	 * 订单方向
	 */
	private final TrdDirection direction;

	/**
	 * 时间戳
	 */
	private final OrdTimestamp ordTimestamp;

	/**
	 * 订单状态(可变)
	 */
	private OrdStatus ordStatus;

	/**
	 * 订单备注(可变)
	 */
	private String remark;

	private static final String defRemark = "none";

	protected OrderBaseImpl(int strategyId, int subAccountId, Instrument instrument, OrdQty ordQty, OrdPrice ordPrice,
			OrdType ordType, TrdDirection direction) {
		this.ordSysId = OrdSysIdSupporter.allocateId(strategyId);
		this.strategyId = strategyId;
		this.subAccountId = subAccountId;
		this.instrument = instrument;
		this.ordQty = ordQty;
		this.ordPrice = ordPrice;
		this.ordType = ordType;
		this.direction = direction;
		this.ordTimestamp = OrdTimestamp.generate();
		this.ordStatus = OrdStatus.PendingNew;
		this.remark = defRemark;
	}

	@Override
	public long ordSysId() {
		return ordSysId;
	}

	@Override
	public int strategyId() {
		return strategyId;
	}

	public int accountId() {
		return accountId;
	}

	@Override
	public int subAccountId() {
		return subAccountId;
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
	public TrdDirection direction() {
		return direction;
	}

	@Override
	public OrdType ordType() {
		return ordType;
	}

	@Override
	public OrdTimestamp ordTimestamp() {
		return ordTimestamp;
	}

	@Override
	public OrdStatus ordStatus() {
		return ordStatus;
	}

	@Override
	public void setOrdStatus(OrdStatus ordStatus) {
		this.ordStatus = ordStatus;
	}

	@Override
	public String remark() {
		return remark;
	}

	@Override
	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public void outputLog(Logger log, String objName, String msg) {
		log.info(OrderOutputText.OrderOutputText, objName, msg, ordSysId(), ordStatus(), direction(), ordType(),
				instrument(), ordPrice(), ordQty(), ordTimestamp());
	}

}
