package io.mercury.redstone.core.adaptor;

import java.io.Closeable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.mercury.common.fsm.Enable;
import io.mercury.financial.instrument.Instrument;
import io.mercury.redstone.core.account.Account;
import io.mercury.redstone.core.order.specific.ChildOrder;

public interface Adaptor extends Closeable, Enable<Adaptor> {

	int adaptorId();

	String adaptorName();

	Account account();

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
	default boolean queryOrder() {
		return queryOrder(null);
	}

	/**
	 * 查询持仓
	 * 
	 * @param account
	 * @return
	 */
	boolean queryOrder(@Nullable Account account);

	/**
	 * 查询持仓
	 * 
	 * @param account
	 * @return
	 */
	default boolean queryPositions() {
		return queryPositions(null);
	}

	/**
	 * 查询持仓
	 * 
	 * @param account
	 * @return
	 */
	boolean queryPositions(@Nullable Account account);

	/**
	 * 查询余额
	 * 
	 * @return
	 */
	default boolean queryBalance() {
		return queryBalance(null);
	}

	/**
	 * 查询余额
	 * 
	 * @param account
	 * @return
	 */
	boolean queryBalance(@Nullable Account account);

	/**
	 * @author yellow013
	 */
	public static enum AdaptorStatus {
		MdEnable, MdDisable, TraderEnable, TraderDisable
	}

}