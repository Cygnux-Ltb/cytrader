package io.mercury.redstone.core.adaptor;

import java.io.Closeable;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.mercury.common.fsm.Enable;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.api.MarketData;
import io.mercury.redstone.core.account.Account;
import io.mercury.redstone.core.order.ActualChildOrder;

public interface Adaptor<M extends MarketData> extends Closeable, Enable<Adaptor<M>> {

	int adaptorId();

	String adaptorName();

	Account account();

	List<Account> accounts();

	/**
	 * Adaptor 启动函数
	 * 
	 * @return
	 */
	boolean startup() throws IllegalStateException;

	/**
	 * 
	 * @param command
	 * @return
	 */
	boolean sendCommand(Command command);

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
	default boolean newOredr(@Nonnull ActualChildOrder order) {
		return newOredr(null, order);
	}

	/**
	 * 发送新订单
	 * 
	 * @param order
	 * @return
	 */
	boolean newOredr(@Nullable Account account, @Nonnull ActualChildOrder order);

	/**
	 * 发送撤单请求
	 * 
	 * @param order
	 * @return
	 */
	default boolean cancelOrder(@Nonnull ActualChildOrder order) {
		return cancelOrder(null, order);
	}

	/**
	 * 发送撤单请求
	 * 
	 * @param order
	 * @return
	 */
	boolean cancelOrder(@Nullable Account account, @Nonnull ActualChildOrder order);

	/**
	 * 查询订单
	 * 
	 * @param account
	 * @return
	 */
	default boolean queryOrder(@Nonnull Instrument instrument) {
		return queryOrder(null, instrument);
	}

	/**
	 * 查询订单
	 * 
	 * @param account
	 * @return
	 */
	boolean queryOrder(@Nullable Account account, @Nonnull Instrument instrument);

	/**
	 * 查询持仓
	 * 
	 * @param account
	 * @return
	 */
	default boolean queryPositions(@Nonnull Instrument instrument) {
		return queryPositions(null, instrument);
	}

	/**
	 * 查询持仓
	 * 
	 * @param account
	 * @return
	 */
	boolean queryPositions(@Nullable Account account, @Nonnull Instrument instrument);

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

}
