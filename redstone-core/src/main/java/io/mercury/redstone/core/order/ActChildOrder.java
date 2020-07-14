package io.mercury.redstone.core.order;

import org.slf4j.Logger;

import io.mercury.financial.instrument.Instrument;
import io.mercury.redstone.core.account.SubAccount;
import io.mercury.redstone.core.order.enums.OrdType;
import io.mercury.redstone.core.order.enums.TrdAction;
import io.mercury.redstone.core.order.enums.TrdDirection;
import io.mercury.redstone.core.order.structure.OrdPrice;
import io.mercury.redstone.core.order.structure.OrdQty;
import io.mercury.redstone.core.order.structure.TrdRecord;
import io.mercury.redstone.core.order.structure.TrdRecordList;
import io.mercury.redstone.core.strategy.Strategy;

/**
 * 实际执行订单的最小执行单元, 可能根据合规, 账户情况等由ActParentOrder拆分出多个ActChildOrder
 * 
 * @author yellow013
 * @creation 2018年1月14日
 */
public final class ActChildOrder extends ActualOrder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3863592977001402228L;

	/**
	 * 经纪商提供的唯一码, 可能有多个, 使用数组实现
	 */
	private final String[] brokerIdentifier = new String[4];

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
	ActChildOrder(int strategyId, int accountId, int subAccountId, Instrument instrument, int offerQty, long offerPrice,
			OrdType ordType, TrdDirection direction, TrdAction action, long ownerOrdId) {
		super(OrdSysIdSupporter.allocateId(strategyId), strategyId, accountId, subAccountId, instrument,
				OrdQty.withOffer(offerQty), OrdPrice.withOffer(offerPrice), ordType, direction, action, ownerOrdId);
		this.trdRecordList = new TrdRecordList(ordSysId());
	}

	/**
	 * 
	 * 用于构建外部来源的订单
	 * 
	 * @param ordSysId   外部传入的ordSysId, 用于处理非系统订单
	 * @param accountId  实际账户Id
	 * @param instrument 交易标的
	 * @param ordQty     委托数量
	 * @param ordPrice   委托价格
	 * @param ordType    订单类型
	 * @param direction  交易方向
	 * @param action     交易动作
	 */
	ActChildOrder(long ordSysId, int accountId, Instrument instrument, int offerQty, long offerPrice,
			TrdDirection direction, TrdAction action) {
		super(ordSysId, Strategy.ExternalStrategyId, accountId, SubAccount.ExternalSubAccountId, instrument,
				OrdQty.withOffer(offerQty), OrdPrice.withOffer(offerPrice), OrdType.Limit, direction, action, 0L);
		this.trdRecordList = new TrdRecordList(ordSysId());
	}

	@Override
	public int ordLevel() {
		return 0;
	}

	public String[] brokerIdentifier() {
		return brokerIdentifier;
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

	private static final String ChildOrderText = "{} :: {}, ChildOrder : ordSysId==[{}], ownerOrdId==[{}], "
			+ "ordStatus==[{}], direction==[{}], action==[{}], ordType==[{}], instrument -> {}, ordPrice -> {}, "
			+ "ordQty -> {}, ordTimestamps -> {}, trdRecordList -> {}";

	@Override
	public void writeLog(Logger logger, String objName, String msg) {
		logger.info(ChildOrderText, objName, msg, ordSysId(), ownerOrdId(), ordStatus(), direction(), action(),
				ordType(), instrument(), ordPrice(), ordQty(), ordTimestamp(), trdRecordList);
	}

}
