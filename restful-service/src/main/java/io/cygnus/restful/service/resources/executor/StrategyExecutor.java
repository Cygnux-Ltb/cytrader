package io.cygnus.restful.service.resources.executor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import io.cygnus.db.dao.StrategyDao;
import io.cygnus.service.entity.Strategy;
import io.cygnus.service.entity.StrategyDefaultParam;
import io.cygnus.service.entity.StrategyParam;
import io.cygnus.service.entity.StrategySymbol;
import io.mercury.common.concurrent.cache.CacheList;
import io.mercury.common.concurrent.cache.CacheMap;
import io.mercury.common.log.CommonLoggerFactory;

public class StrategyExecutor {

	private static final Logger log = CommonLoggerFactory.getLogger(StrategyExecutor.class);

	private static final StrategyDao strategyDao = new StrategyDao();

	/**
	 * All strategy Cache
	 */
	private static final CacheList<Strategy> AllStrategyCache = new CacheList<>(() -> {
		return strategyDao.getAllStrategy();
	});

	/**
	 * 
	 * @return
	 */
	public List<Strategy> getAllStrategy() {
		return AllStrategyCache.get();
	}

	/**
	 * Strategy CacheMap
	 */
	private static final CacheMap<Integer, List<Strategy>> StrategyCacheMap = CacheMap.newBuilder()
			.buildWith((strategyId) -> {
				return strategyDao.getStrategyById(strategyId);
			});

	/**
	 * 
	 * @param strategyId
	 * @return
	 */
	public List<Strategy> getStrategyById(Integer strategyId) {
		return StrategyCacheMap.getOptional(strategyId).get();
	}

	/**
	 * StrategyDefaultParam Cache
	 */
	private static final CacheList<StrategyDefaultParam> AllStrategyDefaultParamCache = new CacheList<>(() -> {
		return strategyDao.getAllDefaultParam();
	});

	/**
	 * StrategyParam CacheMap
	 */
	private static final CacheMap<Integer, List<StrategyParam>> StrategyParamCacheMap = CacheMap.newBuilder()
			.buildWith((strategyId) -> {
				List<StrategyParam> strategyParams = strategyDao.getParamsByStrategyId(strategyId);
				List<StrategyParam> mergeList = new ArrayList<>(strategyParams);
				// 遍历全部默认参数
				for (StrategyDefaultParam defaultParam : AllStrategyDefaultParamCache.get()) {
					boolean existed = false;
					for (StrategyParam strategyParam : strategyParams) {
						// 判断策略参数与默认参数是否相同
						if (strategyParam.isSame(defaultParam)) {
							// 如果相同, 则使用策略参数
							existed = true;
							break;
						}
					}
					// 如果默认参数在策略参数中不存在, 则将此默认参数添加到策略参数中
					if (!existed) {
						mergeList.add(
								new StrategyParam().setValues4DefaultParam(defaultParam).setStrategyId(strategyId));
					}
				}
				return mergeList;
			});

	/**
	 * 
	 * @param strategyId
	 * @return
	 */
	public List<StrategyParam> getParamsByStrategyId(Integer strategyId) {
		return StrategyParamCacheMap.getOptional(strategyId).get();
	}

	/**
	 * 
	 * @param strategyParam
	 * @return
	 */
	public boolean putStrategyParam(StrategyParam strategyParam) {
		boolean isSuccess = strategyDao.putStrategyParam(strategyParam);
		if (isSuccess) {
			StrategyParamCacheMap.setUnavailable(strategyParam.getStrategyId());
			return true;
		} else {
			return false;
		}
	}

	/**
	 * StrategySymbol CacheMap
	 */
	private static CacheMap<Integer, List<StrategySymbol>> strategySymbolCacheMap = CacheMap.newBuilder()
			.buildWith((strategyId) -> {
				return strategyDao.getSymbolsByStrategyId(strategyId);
			});

	public List<StrategySymbol> getSymbolsByStrategyId(Integer strategyId) {
		return strategySymbolCacheMap.getOptional(strategyId).get();
	}

}
