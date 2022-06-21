package io.cygnux.console.service;

import static io.mercury.common.functional.Functions.exec;
import static io.mercury.common.functional.Functions.execBool;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import io.cygnux.console.service.base.BaseService;
import io.cygnux.repository.dao.StrategyDao;
import io.cygnux.repository.dao.StrategyParamDao;
import io.cygnux.repository.entities.internal.InStrategy;
import io.cygnux.repository.entities.internal.InStrategyParam;
import io.mercury.common.lang.Throws;
import io.mercury.common.log.Log4j2LoggerFactory;

@Service
public final class StrategyService extends BaseService {

	private final Logger log = Log4j2LoggerFactory.getLogger(StrategyService.class);

	@Resource
	private StrategyDao dao;

	@Resource
	private StrategyParamDao paramDao;

	/**
	 * 
	 * @return
	 */
	public List<InStrategy> getStrategys() {
		return exec(() -> dao.findAll(), list -> list, e -> {
			log.error("query [StrategyEntity] exception", e);
		});
	}

	/**
	 * 
	 * @param strategyId
	 * @return
	 */
	public InStrategy getStrategy(int strategyId) {
		if (checkStrategyId(strategyId, log, "query [StrategyEntity] param error"))
			Throws.illegalArgument("strategyId");
		return dao.queryByStrategyId(strategyId);
	}

	/**
	 * 
	 * @param strategyName
	 * @return
	 */
	public List<InStrategy> getStrategy(String strategyName) {
		if (checkStrategyName(strategyName, log, "query [StrategyEntity] param error"))
			Throws.illegalArgument("strategyName");
		return dao.queryByStrategyName(strategyName);
	}

	/**
	 * 
	 * @param strategyId
	 * @return
	 */
	public List<InStrategyParam> getStrategyParams(int strategyId) {
		if (checkStrategyId(strategyId, log, "query [StrategyParamEntity] param error"))
			Throws.illegalArgument("strategyId");
		return exec(() -> paramDao.queryByStrategyId(strategyId), list -> {
			if (CollectionUtils.isEmpty(list)) {
				log.warn("query [StrategyParamEntity] return 0 row, strategyId=={}", strategyId);
			} else
				log.info("query [StrategyParamEntity] where strategyId=={}, result row -> {}", strategyId, list.size());
			return list;
		}, e -> {
			log.error("query [StrategyParamEntity] exception, strategyId=={}", strategyId, e);
		});
	}

	/**
	 * 
	 * @param strategyName
	 * @return
	 */
	public List<InStrategyParam> getStrategyParams(String strategyName) {
		if (checkStrategyName(strategyName, log, "query [StrategyParamEntity] param error"))
			Throws.illegalArgument("strategyId");
		return exec(() -> paramDao.queryByStrategyName(strategyName), list -> {
			if (CollectionUtils.isEmpty(list)) {
				log.warn("query [StrategyParamEntity] return 0 row, strategyName=={}", strategyName);
			} else
				log.info("query [StrategyParamEntity] where strategyName=={}, result row -> {}", strategyName,
						list.size());
			return list;
		}, e -> {
			log.error("query [StrategyParamEntity] exception, strategyName=={}", strategyName, e);
		});
	}

	/**
	 * 
	 * @param strategyName
	 * @return
	 */
	public List<InStrategyParam> getDefaultStrategyParams() {
		return getStrategyParams(0);
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public boolean putStrategy(InStrategy entity) {
		return execBool(() -> dao.save(entity), o -> {
			log.info("save [StrategyEntity] success -> {}", entity);
			return true;
		}, e -> {
			log.error("save [StrategyEntity] failure -> {}", entity, e);
			return false;
		});
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public boolean putStrategyParam(InStrategyParam entity) {
		return execBool(() -> paramDao.save(entity), o -> {
			log.info("save [StrategyParamEntity] success -> {}", entity);
			return true;
		}, e -> {
			log.error("save [StrategyParamEntity] failure -> {}", entity, e);
			return false;
		});
	}

}
