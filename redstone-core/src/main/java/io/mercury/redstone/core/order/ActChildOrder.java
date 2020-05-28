package io.mercury.redstone.core.order.specific;

import org.slf4j.Logger;

import io.mercury.financial.instrument.Instrument;
import io.mercury.redstone.core.order.OrderOutputText;
import io.mercury.redstone.core.order.enums.OrdType;
import io.mercury.redstone.core.order.enums.TrdAction;
import io.mercury.redstone.core.order.enums.TrdDirection;
import io.mercury.redstone.core.order.structure.OrdPrice;
import io.mercury.redstone.core.order.structure.OrdQty;
import io.mercury.redstone.core.order.structure.TrdRecordList;
import io.mercury.redstone.core.order.structure.TrdRecord;

/**
 * 实际执行订单的最小执行单元, 可能根据合规, 账户情况等由ParentOrder拆分出多个ChildOrder
 * 
 * @author yellow013
 * @creation 2018年1月14日
 */
public final class ChildOrder extends ActualOrder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3863592977001402228L;

	/**
	 * 子订单成交列表
	 */
	private final TrdRecordList trdRecordList;

	/**
	 * 
	 * @param strategyId   策略Id
	 * @param accountId    实际账户Id
	 * @param subAccountId 子账户Id
	 * @param instrument   交易标的
	 * @param ordQty       委托数量
	 * @param ordPrice     委托价格
	 * @param ordType      订单类型
	 * @param direction    交易方向
	 * @param action       交易动作
	 * @param ownerOrdId   所属上级订单
	 */
	public ChildOrder(int strategyId, int accountId, int subAccountId, Instrument instrument, int offerQty,
			long offerPrice, OrdType ordType, TrdDirection direction, TrdAction action, long ownerOrdId) {
		super(strategyId, accountId, subAccountId, instrument, OrdQty.withOffer(offerQty),
				OrdPrice.withOffer(offerPrice), ordType, direction, action, ownerOrdId);
		this.trdRecordList = new TrdRecordList(ordSysId());
	}

	@Override
	public int ordLevel() {
		return 0;
	}

	public TrdRecordList trdRecordList() {
		return trdRecordList;
	}
	
	public TrdRecord firstTrdRecord() {
		return trdRecordList.first().get();
	}

	public TrdRecord lastTrdRecord() {
		return trdRecordList.last().get();
	}

	@Override
	public void outputLog(Logger log, String objName, String msg) {
		log.info(OrderOutputText.ChildOrderOutputText, objName, msg, ordSysId(), ownerOrdId(), ordStatus(), direction(),
				action(), ordType(), instrument(), ordPrice(), ordQty(), ordTimestamp(), trdRecordList);
	}

}
