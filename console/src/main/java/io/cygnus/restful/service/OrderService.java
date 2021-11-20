package io.cygnus.repository.service;

import static io.mercury.common.functional.Functions.exec;
import static io.mercury.common.functional.Functions.execBool;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import io.cygnus.repository.dao.OrderDao;
import io.cygnus.repository.dao.OrderEventDao;
import io.cygnus.repository.entity.OrderEntity;
import io.cygnus.repository.entity.OrderEventEntity;
import io.cygnus.repository.service.base.BaseService;
import io.mercury.common.lang.Throws;
import io.mercury.common.log.CommonLoggerFactory;

@Service
public class OrderService extends BaseService {

	private final Logger log = CommonLoggerFactory.getLogger(OrderService.class);

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
	public List<OrderEntity> getOrders(int strategyId, String investorId, String instrumentCode, int tradingDay) {
		return getOrders(strategyId, investorId, instrumentCode, tradingDay, tradingDay);
	}

	private final String QueryOrderParamErrorMsg = "query [OrderEntity] param error";

	/**
	 * 
	 * @param strategyId
	 * @param startTradingDay
	 * @param endTradingDay
	 * @param investorId
	 * @param instrumentCode
	 * @return
	 */
	public List<OrderEntity> getOrders(int strategyId, String investorId, String instrumentCode, int startTradingDay,
			int endTradingDay) {
		if (checkStrategyId(strategyId, log, QueryOrderParamErrorMsg))
			Throws.illegalArgument("strategyId");
		if (checkTradingDay(startTradingDay, endTradingDay, log, QueryOrderParamErrorMsg))
			Throws.illegalArgument("startTradingDay & endTradingDay");
		if (checkInvestorId(investorId, log, QueryOrderParamErrorMsg))
			Throws.illegalArgument("investorId");
		if (checkInstrumentCode(instrumentCode, log, QueryOrderParamErrorMsg))
			Throws.illegalArgument("instrumentCode");
		return exec(() -> dao.query(strategyId, investorId, instrumentCode, startTradingDay, endTradingDay),
				list -> {
					if (CollectionUtils.isEmpty(list))
						log.warn(
								"query [OrderEntity] return 0 row, strategyId=={}, startTradingDay=={}, endTradingDay=={}, investorId=={}, instrumentCode=={}",
								strategyId, startTradingDay, endTradingDay, investorId, instrumentCode);
					else
						log.info(
								"query [OrderEntity] where strategyId=={}, startTradingDay=={}, endTradingDay=={}, investorId=={}, instrumentCode=={}, result row -> {}",
								strategyId, startTradingDay, endTradingDay, investorId, instrumentCode, list.size());
					return list;
				}, e -> {
					log.error(
							"query [OrderEntity] exception, strategyId=={}, startTradingDay=={}, endTradingDay=={}, investorId=={}, instrumentCode=={}",
							strategyId, startTradingDay, endTradingDay, investorId, instrumentCode, e);
				});
	}

	private final String QueryOrderEventParamErrorMsg = "query [OrderEventEntity] param error";

	/**
	 * 
	 * @param ordSysId
	 * @return
	 */
	public List<OrderEventEntity> getOrderEvents(long ordSysId) {
		if (checkOrdSysId(ordSysId, log, QueryOrderEventParamErrorMsg))
			return new FastList<>();
		return exec(() -> eventDao.queryByOrdSysId(ordSysId), list -> {
			if (CollectionUtils.isEmpty(list))
				log.warn("query [OrderEventEntity] return 0 row, ordSysId=={}", ordSysId);
			else
				log.info("query [OrderEventEntity] where ordSysId=={}, result row -> {}", ordSysId, list.size());
			return list;
		}, e -> {
			log.error("query [OrderEventEntity] exception, ordSysId=={}", ordSysId, e);
		});
	}

	/**
	 * 
	 * @param tradingDay
	 * @return
	 */
	public List<OrderEventEntity> getOrderEvents(int tradingDay) {
		if (checkTradingDay(tradingDay, log, QueryOrderEventParamErrorMsg))
			return new FastList<>();
		return exec(() -> eventDao.queryByTradingDay(tradingDay), list -> {
			if (CollectionUtils.isEmpty(list))
				log.warn("query [OrderEventEntity] return 0 row, tradingDay=={}", tradingDay);
			else
				log.info("query [OrderEventEntity] where tradingDay=={}, result row -> {}", tradingDay, list.size());
			return list;
		}, e -> {
			log.error("query [OrderEventEntity] exception, tradingDay=={}", tradingDay, e);
		});
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public boolean putOrder(OrderEntity entity) {
		return execBool(() -> dao.save(entity), o -> {
			log.info("save [OrderEntity] success -> {}", entity);
			return true;
		}, e -> {
			log.error("save [OrderEntity] failure -> {}", entity, e);
			return false;
		});
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public boolean putOrderEvent(OrderEventEntity entity) {
		return execBool(() -> eventDao.save(entity), o -> {
			log.info("save [OrderEventEntity] success -> {}", entity);
			return true;
		}, e -> {
			log.error("save [OrderEventEntity] failure -> {}", entity, e);
			return false;
		});
	}

}
