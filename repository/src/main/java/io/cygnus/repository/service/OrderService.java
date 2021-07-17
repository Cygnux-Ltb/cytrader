package io.cygnus.repository.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import io.cygnus.repository.dao.OrderDao;
import io.cygnus.repository.dao.OrderEventDao;
import io.cygnus.repository.entity.OrderEntity;
import io.cygnus.repository.entity.OrderEventEntity;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.util.StringUtil;

@Service
public class OrderService {

	@Resource
	private OrderDao orderDao;

	@Resource
	private OrderEventDao orderEventDao;

	private static final Logger log = CommonLoggerFactory.getLogger(OrderService.class);

	/**
	 * 
	 * @param strategyId
	 * @param tradingDay
	 * @param investorId
	 * @param instrumentCode
	 * @return
	 */
	public List<OrderEntity> getOrders(int strategyId, int tradingDay, String investorId, String instrumentCode) {
		return getOrders(strategyId, tradingDay, tradingDay, investorId, instrumentCode);
	}

	/**
	 * 
	 * @param strategyId
	 * @param startTradingDay
	 * @param endTradingDay
	 * @param investorId
	 * @param instrumentCode
	 * @return
	 */
	public List<OrderEntity> getOrders(int strategyId, int startTradingDay, int endTradingDay, String investorId,
			String instrumentCode) {
		if (checkParams(strategyId, startTradingDay, endTradingDay, 0L, investorId, instrumentCode)) {
			throw new IllegalArgumentException("missing or invalid query params");
		}
		return null;
	}

	/**
	 * 
	 * @param ordSysId
	 * @return
	 */
	public List<OrderEventEntity> getOrderEvents(long ordSysId) {

		return null;
	}

	/**
	 * 
	 * @param strategyId
	 * @param startTradingDay
	 * @param endTradingDay
	 * @param ordSysId
	 * @param investorId
	 * @param instrumentCode
	 * @return
	 */
	private boolean checkParams(int strategyId, int startTradingDay, int endTradingDay, long ordSysId,
			String investorId, String instrumentCode) {
		if (strategyId <= 0 && startTradingDay <= 0 && endTradingDay < startTradingDay && ordSysId < 0L
				&& StringUtil.isNullOrEmpty(investorId) && StringUtil.isNullOrEmpty(instrumentCode)) {
			log.error(
					"illegal params, strategyId -> {}, startTradingDay -> {}, endTradingDay -> {}, "
							+ "ordSysId -> {}, investorId -> {}, instrumentCode -> {}",
					strategyId, startTradingDay, endTradingDay, ordSysId, investorId, instrumentCode);
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param order
	 * @return
	 */
	public boolean putOrder(OrderEntity order) {
		orderDao.save(order);
		return true;
	}

	/**
	 * 
	 * @param orderEvent
	 * @return
	 */
	public boolean putOrderEvent(OrderEventEntity orderEvent) {
		orderEventDao.save(orderEvent);
		return false;
	}

}
