package io.cygnus.restful.service.resources.executor;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import io.cygnus.repository.entity.CygInfoEntity;
import io.cygnus.repository.service.CygInfoService;
import io.mercury.common.concurrent.cache.CacheList;
import io.mercury.common.concurrent.cache.CacheMap;
import io.mercury.common.log.CommonLoggerFactory;

@Component
public class CygInfoExecutor {

	private static final Logger log = CommonLoggerFactory.getLogger(CygInfoExecutor.class);

	@Resource
	private CygInfoService dao;

	// All cygInfo Cache
	private final CacheList<CygInfoEntity> AllCygInfoCache = new CacheList<>(() -> {
		log.info("update cache");
		return dao.getAll();
	});

	public List<CygInfoEntity> getCygInfos() {
		return AllCygInfoCache.get();
	}

	/**
	 * cygInfo Cache by cygId
	 */
	private final CacheMap<Integer, CygInfoEntity> CygInfoCacheMap = CacheMap.newBuilder().buildWith(cygId -> {
		return dao.get(cygId);
	});

	/**
	 * 
	 * @param cygId
	 * @return
	 */
	public CygInfoEntity getCygInfoById(int cygId) {
		return CygInfoCacheMap.getOptional(cygId).get();
	}

}
