package io.cygnux.console.service;

import static io.mercury.common.functional.Functions.exec;
import static io.mercury.common.functional.Functions.execBool;

import java.util.List;

import javax.annotation.Resource;

import org.eclipse.collections.impl.list.mutable.FastList;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import io.cygnux.repository.dao.OrderDao;
import io.cygnux.repository.dao.OrderEventDao;
import io.cygnux.repository.entities.ItOrder;
import io.cygnux.repository.entities.ItOrderEvent;
import io.mercury.common.lang.Throws;
import io.mercury.common.log.Log4j2LoggerFactory;

@Service
public class OrderService {

	private static final Logger log = Log4j2LoggerFactory.getLogger(OrderService.class);

	@Resource
	private OrderDao dao;

	@Resource
	private OrderEventDao eventDao;

	/**
	 * 
	 * @param strategyId
	 * @param tradingDay
	 * @param investorId
	 * @param instrumentCode
	 * @return
	 */
	public List<ItOrder> getOrders(int strategyId, String investorId, String instrumentCode, int tradingDay) {
		return getOrders(strategyId, investorId, instrumentCode, tradingDay, tradingDay);
	}

	private final String queryOrderParamErrorMsg = "[OrderService::getOrders] param error";

	/**
	 * 
	 * @param strategyId
	 * @param investorId
	 * @param instrumentCode
	 * @param startTradingDay
	 * @param endTradingDay
	 * @return
	 */
	public List<ItOrder> getOrders(int strategyId, String investorId, String instrumentCode, int startTradingDay,
								   int endTradingDay) {
		if (checkStrategyId(strategyId, log, queryOrderParamErrorMsg))
			Throws.illegalArgument("strategyId");
		if (checkTradingDay(startTradingDay, endTradingDay, log, queryOrderParamErrorMsg))
			Throws.illegalArgument("startTradingDay & endTradingDay");
		if (checkInvestorId(investorId, log, queryOrderParamErrorMsg))
			Throws.illegalArgument("investorId");
		if (checkInstrumentCode(instrumentCode, log, queryOrderParamErrorMsg))
			Throws.illegalArgument("instrumentCode");
		return exec(() -> dao.query(strategyId, investorId, instrumentCode, startTradingDay, endTradingDay), list -> {
			log.info(
					"[OrderDao::query] return {} row, strategyId=={}, investorId=={}, instrumentCode=={}, startTradingDay=={}, endTradingDay=={}",
					list.size(), strategyId, investorId, instrumentCode, startTradingDay, endTradingDay);
			return list;
		}, e -> {
			log.error(
					"[OrderDao::query] exception, strategyId=={}, investorId=={}, instrumentCode=={}, startTradingDay=={}, endTradingDay=={}",
					strategyId, investorId, instrumentCode, startTradingDay, endTradingDay, e);
		});
	}

	/**
	 * 
	 * @param ordSysId
	 * @return
	 */
	public List<ItOrderEvent> getOrderEventsByOrderSysId(long ordSysId) {
		if (checkOrdSysId(ordSysId, log, "[OrderService::getOrderEventsByOrderSysId] param error"))
			return new FastList<>();
		return exec(() -> eventDao.queryByOrdSysId(ordSysId), list -> {
			log.info("[OrderEventDao::queryByOrdSysId] return {} row, ordSysId=={}", list.size(), ordSysId);
			return list;
		}, e -> {
			log.error("[OrderEventDao::queryByOrdSysId] exception, ordSysId=={}", ordSysId, e);
		});
	}

	/**
	 * 
	 * @param tradingDay
	 * @return
	 */
	public List<ItOrderEvent> getOrderEventsByTradingDay(int tradingDay) {
		if (checkTradingDay(tradingDay, log, "[OrderService::getOrderEventsByTradingDay] param error"))
			return new FastList<>();
		return exec(() -> eventDao.queryByTradingDay(tradingDay), list -> {
			log.info("[OrderEventDao::queryByTradingDay] return {} row, tradingDay=={}, ", list.size(), tradingDay);
			return list;
		}, e -> {
			log.error("[OrderEventDao::queryByTradingDay] exception, tradingDay=={}", tradingDay, e);
		});
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public boolean putOrder(ItOrder entity) {
		return execBool(() -> dao.save(entity), o -> {
			log.info("[OrderDao::save] success -> {}", entity);
			return true;
		}, e -> {
			log.error("[OrderDao::save] failure -> {}", entity, e);
			return false;
		});
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public boolean putOrderEvent(ItOrderEvent entity) {
		return execBool(() -> eventDao.save(entity), o -> {
			log.info("[OrderEventDao::save] success -> {}", entity);
			return true;
		}, e -> {
			log.error("[OrderEventDao::save] failure -> {}", entity, e);
			return false;
		});
	}

}
