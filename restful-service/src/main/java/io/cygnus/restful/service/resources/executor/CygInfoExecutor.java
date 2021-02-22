package io.cygnus.restful.service.resources.executor;

import java.util.List;

import org.slf4j.Logger;

import io.cygnus.db.dao.CygInfoDao;
import io.cygnus.restful.service.base.BaseExecutor;
import io.cygnus.service.entity.CygInfo;
import io.cygnus.service.entity.CygMqConfig;
import io.cygnus.service.entity.CygStrategy;
import io.mercury.common.concurrent.list.CacheList;
import io.mercury.common.concurrent.map.GuavaCacheMap;
import io.mercury.common.log.CommonLoggerFactory;

public class CygInfoExecutor extends BaseExecutor {

	private Logger logger = CommonLoggerFactory.getLogger(getClass());

	// All cygInfo Cache
	private static final CacheList<CygInfo> AllCygInfoCache = new CacheList<>(() -> {
		CygInfoDao cygInfoDao = new CygInfoDao();
		return cygInfoDao.getAllCygInfo();
	});

	public List<CygInfo> getAllcygInfo() {
		return AllCygInfoCache.get();
	}

	/**
	 * cygInfo Cache by cygId
	 */
	private static final GuavaCacheMap<Integer, List<CygInfo>> CygInfoCacheMap = GuavaCacheMap.builder()
			.buildWith(cygId -> {
				CygInfoDao dao = new CygInfoDao();
				return dao.getCygInfoById(cygId);
			});

	public List<CygInfo> getCygInfoById(Integer cygId) {
		return CygInfoCacheMap.getOptional(cygId).get();
	}

	/**
	 * cygStrategy Cache by cygId
	 */
	private static final GuavaCacheMap<Integer, List<CygStrategy>> CygStrategyCacheMap = GuavaCacheMap.builder()
			.buildWith(cygId -> {
				CygInfoDao dao = new CygInfoDao();
				return dao.getCygStrategyById(cygId);
			});

	public List<CygStrategy> getCygStrategyById(Integer cygId) {
		return CygStrategyCacheMap.getOptional(cygId).get();
	}

	public List<CygMqConfig> getCygMqConfigById(Integer cygId) {
		CygInfoDao dao = new CygInfoDao();
		return dao.getCygMqConfigById(cygId);
	}

}
