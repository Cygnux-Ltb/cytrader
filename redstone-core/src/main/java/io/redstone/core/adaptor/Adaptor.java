package io.redstone.core.adaptor.api;

import javax.annotation.Nonnull;

import io.mercury.polaris.financial.instrument.Instrument;
import io.redstone.core.account.InvestorAccount;
import io.redstone.core.order.impl.ChildOrder;

public interface Adaptor extends AutoCloseable {

	int adaptorId();

	String adaptorName();

	boolean startup();

	/**
	 * 订阅行情
	 * 
	 * @param subscribeMarketData
	 * @return
	 */
	boolean subscribeMarketData(@Nonnull Instrument... instruments);

	/**
	 * 发送新订单
	 * 
	 * @param order
	 * @return
	 */
	boolean newOredr(@Nonnull ChildOrder order);

	/**
	 * 发送撤单请求
	 * 
	 * @param order
	 * @return
	 */
	boolean cancelOrder(@Nonnull ChildOrder order);

	/**
	 * 查询持仓
	 * 
	 * @param account
	 * @return
	 */
	boolean queryPositions(@Nonnull InvestorAccount account);

	/**
	 * 查询余额
	 * 
	 * @param account
	 * @return
	 */
	boolean queryBalance(@Nonnull InvestorAccount account);

}
