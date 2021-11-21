package io.cygnus.restful.service.resources.executor;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import io.cygnus.repository.entity.CygStrategy;
import io.cygnus.repository.entity.CygStrategyParam;
import io.cygnus.restful.service.StrategyService;
import io.mercury.common.concurrent.cache.CacheList;
import io.mercury.common.concurrent.cache.CacheMap;
import io.mercury.common.log.CommonLoggerFactory;

@Component
public class StrategyExecutor {

	private static final Logger log = CommonLoggerFactory.getLogger(StrategyExecutor.class);

	@Resource
	private StrategyService service;

	/**
	 * All strategy Cache
	 */
	private final CacheList<CygStrategy> strategyCacheList = new CacheList<>(() -> {
		return null;
	});

	/**
	 * 
	 * @return
	 */
	public List<CygStrategy> getAllStrategy() {
		return strategyCacheList.get();
	}

	/**
	 * Strategy CacheMap
	 */
	private final CacheMap<Integer, CygStrategy> StrategyCacheMap = CacheMap.newBuilder().build((strategyId) -> {
		return service.getStrategy(strategyId);
	});

	/**
	 * 
	 * @param strategyId
	 * @return
	 */
	public CygStrategy getStrategyById(Integer strategyId) {
		return StrategyCacheMap.getOptional(strategyId).get();
	}

	/**
	 * StrategyDefaultParam Cache
	 */
	private final CacheList<CygStrategyParam> AllStrategyDefaultParamCache = new CacheList<>(() -> {
		return null;
	});

	/**
	 * StrategyParam CacheMap
	 */
	private final CacheMap<Integer, List<CygStrategyParam>> StrategyParamCacheMap = CacheMap.newBuilder()
			.build((strategyId) -> {
				List<CygStrategyParam> strategyParams = service.getStrategyParams(strategyId);
				List<CygStrategyParam> mergeList = new ArrayList<>(strategyParams);
				// 遍历全部默认参数
				for (CygStrategyParam defaultParam : AllStrategyDefaultParamCache.get()) {
					boolean existed = false;
					for (CygStrategyParam strategyParam : strategyParams) {
						// 判断策略参数与默认参数是否相同
						if (strategyParam.isSame(defaultParam)) {
							// 如果相同, 则使用策略参数
							existed = true;
							break;
						}
					}
					// 如果默认参数在策略参数中不存在, 则将此默认参数添加到策略参数中
					if (!existed) {
						mergeList.add(new CygStrategyParam().setValues4DefaultParam(defaultParam)
								.setStrategyId(strategyId));
					}
				}
				return mergeList;
			});

	/**
	 * 
	 * @param strategyId
	 * @return
	 */
	public List<CygStrategyParam> getParamsByStrategyId(Integer strategyId) {
		return StrategyParamCacheMap.getOptional(strategyId).get();
	}

	/**
	 * 
	 * @param strategyParam
	 * @return
	 */
	public boolean putStrategyParam(CygStrategyParam strategyParam) {
		boolean isSuccess = service.putStrategyParam(strategyParam);
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
			.build((strategyId) -> {
				return strategyDao.getSymbolsByStrategyId(strategyId);
			});

	public List<StrategySymbol> getSymbolsByStrategyId(Integer strategyId) {
		return strategySymbolCacheMap.getOptional(strategyId).get();
	}

}
