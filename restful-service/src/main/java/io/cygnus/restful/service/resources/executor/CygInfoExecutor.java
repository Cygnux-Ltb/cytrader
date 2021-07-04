package io.cygnus.restful.service.resources.executor;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import io.cygnus.persistence.entity.CygInfo;
import io.cygnus.persistence.entity.CygMqConfig;
import io.cygnus.persistence.entity.CygStrategy;
import io.cygnus.repository.service.CygInfoService;
import io.mercury.common.concurrent.cache.CacheList;
import io.mercury.common.concurrent.cache.CacheMap;
import io.mercury.common.log.CommonLoggerFactory;

@Component
public class CygInfoExecutor {

	private static final Logger log = CommonLoggerFactory.getLogger(CygInfoExecutor.class);

	private final static CygInfoService dao = new CygInfoService();

	// All cygInfo Cache
	private static final CacheList<CygInfo> AllCygInfoCache = new CacheList<>(() -> {
		return dao.getAllCygInfo();
	});

	public List<CygInfo> getAllcygInfo() {
		return AllCygInfoCache.get();
	}

	/**
	 * cygInfo Cache by cygId
	 */
	private static final CacheMap<Integer, List<CygInfo>> CygInfoCacheMap = CacheMap.newBuilder().buildWith(cygId -> {
		return dao.getCygInfoById(cygId);
	});

	public List<CygInfo> getCygInfoById(Integer cygId) {
		return CygInfoCacheMap.getOptional(cygId).get();
	}

	/**
	 * cygStrategy Cache by cygId
	 */
	private static final CacheMap<Integer, List<CygStrategy>> CygStrategyCacheMap = CacheMap.newBuilder()
			.buildWith(cygId -> {
				return dao.getCygStrategyById(cygId);
			});

	/**
	 * 
	 * @param cygId
	 * @return
	 */
	public List<CygStrategy> getCygStrategyById(Integer cygId) {
		return CygStrategyCacheMap.getOptional(cygId).get();
	}

	/**
	 * 
	 * @param cygId
	 * @return
	 */
	public List<CygMqConfig> getCygMqConfigById(Integer cygId) {
		return dao.getCygMqConfigById(cygId);
	}

}
