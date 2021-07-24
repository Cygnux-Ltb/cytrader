package io.cygnus.repository.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import io.cygnus.repository.dao.StrategyDao;
import io.cygnus.repository.dao.StrategyParamDao;
import io.cygnus.repository.entity.StrategyEntity;
import io.cygnus.repository.entity.StrategyParamEntity;
import io.mercury.common.log.CommonLoggerFactory;

@Service
public final class StrategyService {

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
		return dao.findAll();
	}

	/**
	 * 
	 * @param strategyId
	 * @return
	 */
	public StrategyEntity getStrategyById(int strategyId) {
		return dao.findById(strategyId).get();
	}

	/**
	 * 
	 * @param strategyId
	 * @return
	 */
	public List<StrategyParamEntity> getParamsByStrategyId(int strategyId) {
		List<StrategyParamEntity> list = paramDao.queryByStrategyId(strategyId);
		if (list == null) {
			return new ArrayList<>();
		}
		return list;
	}

	/**
	 * 
	 * @param strategyParam
	 * @return
	 */
	public boolean putStrategyParam(StrategyParamEntity strategyParam) {
		try {
			paramDao.save(strategyParam);
			return true;
		} catch (Exception e) {
			log.error("Save StrategyParamEntity error -> {}", e.getMessage(), e);
			return false;
		}
	}

}
