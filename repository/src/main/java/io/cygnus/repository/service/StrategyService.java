package io.cygnus.repository.service;

import static io.mercury.common.functional.Functions.booleanFun;
import static io.mercury.common.functional.Functions.listFun;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import io.cygnus.repository.dao.StrategyDao;
import io.cygnus.repository.dao.StrategyParamDao;
import io.cygnus.repository.entity.StrategyEntity;
import io.cygnus.repository.entity.StrategyParamEntity;
import io.cygnus.repository.service.base.BaseService;
import io.mercury.common.lang.Throws;
import io.mercury.common.log.CommonLoggerFactory;

@Service
public final class StrategyService extends BaseService {

	@Resource
	private StrategyDao dao;

	@Resource
	private StrategyParamDao paramDao;

	private static final Logger log = CommonLoggerFactory.getLogger(StrategyService.class);

	/**
	 * 
	 * @return
	 */
	public List<StrategyEntity> getStrategys() {
		return listFun(() -> dao.findAll(), list -> list, e -> {
			log.error("query [StrategyEntity] exception", e);
		});
	}

	/**
	 * 
	 * @param strategyId
	 * @return
	 */
	public StrategyEntity getStrategyById(int strategyId) {
		if (checkStrategyId(strategyId, log, "query [StrategyEntity] param error"))
			Throws.illegalArgument("strategyId");
		return dao.findById(strategyId).get();
	}

	/**
	 * 
	 * @param strategyId
	 * @return
	 */
	public List<StrategyParamEntity> getParams(int strategyId) {
		if (checkStrategyId(strategyId, log, "query [StrategyParamEntity] param error"))
			Throws.illegalArgument("strategyId");
		return listFun(() -> paramDao.queryByStrategyId(strategyId), list -> {
			if (CollectionUtils.isEmpty(list)) {
				log.warn("query [StrategyParamEntity] return 0 row, strategyId=={}", strategyId);
			} else
				log.info("query [StrategyParamEntity] where strategyId=={}, result row -> {}", strategyId, list.size());
			return list;
		}, e -> {
			log.error("query [StrategyParamEntity] exception, strategyId=={}", strategyId, e);
		});
	}

	public boolean putStrategy(StrategyEntity entity) {
		return booleanFun(() -> dao.save(entity), o -> {
			log.info("save [StrategyEntity] success -> {}", entity);
			return true;
		}, e -> {
			log.error("save [StrategyEntity] failure -> {}", entity, e);
			return false;
		});
	}

	/**
	 * 
	 * @param strategyParam
	 * @return
	 */
	public boolean putStrategyParam(StrategyParamEntity entity) {
		return booleanFun(() -> paramDao.save(entity), o -> {
			log.info("save [StrategyParamEntity] success -> {}", entity);
			return true;
		}, e -> {
			log.error("save [StrategyParamEntity] failure -> {}", entity, e);
			return false;
		});
	}

}
