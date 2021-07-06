package io.cygnus.repository.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import io.cygnus.repository.dao.OrderDao;
import io.cygnus.repository.dao.OrderEventDao;
import io.cygnus.repository.entity.OrderEntity;
import io.cygnus.repository.entity.OrderEventEntity;
import io.mercury.common.util.StringUtil;

@Service
public class OrderService {

	@Resource
	private OrderDao orderDao;

	@Resource
	private OrderEventDao orderEventDao;

	/**
	 * 
	 * @return
	 */
	public boolean putOrder() {
		return false;
	}

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
		if (checkParams(strategyId, startTradingDay, endTradingDay, 0L, investorId, instrumentCode))
			throw new IllegalArgumentException("missing or invalid query params");

		return null;
	}
	
	
	/**
	 * 
	 * @param ordSysId
	 * @return
	 */
	public List<OrderEventEntity> getOrderEvent(long ordSysId){
		
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
		if (strategyId <= 0 
				&& startTradingDay <= 0 
				&& endTradingDay < startTradingDay 
				&& ordSysId < 0L
				&& StringUtil.isNullOrEmpty(investorId) 
				&& StringUtil.isNullOrEmpty(instrumentCode))
			return true;
		return false;
	}

	/**
	 * 
	 * @param order
	 * @return
	 */
	public boolean putOrder(OrderEntity order) {

		return true;
	}

}
