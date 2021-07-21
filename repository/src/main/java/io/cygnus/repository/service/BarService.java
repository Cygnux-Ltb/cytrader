package io.cygnus.repository.service;

import static io.mercury.common.functional.Functions.booleanFun;
import static io.mercury.common.functional.Functions.fun;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import io.cygnus.repository.dao.BarDao;
import io.cygnus.repository.entity.BarEntity;
import io.mercury.common.log.CommonLoggerFactory;

@Service
public class BarService {

	private static final Logger log = CommonLoggerFactory.getLogger(BarService.class);

	@Resource
	private BarDao dao;

	/**
	 * 
	 * @param instrumentCode
	 * @param tradingDay
	 * @return
	 */
	public List<BarEntity> getBars(final String instrumentCode, final int tradingDay) {
		return fun(() -> dao.query(instrumentCode, tradingDay), list -> {
			if (CollectionUtils.isEmpty(list))
				log.warn("query [BarEntity] return 0 row, instrumentCode=={}, tradingDay=={}", instrumentCode,
						tradingDay);
			else
				log.info("query [BarEntity] where instrumentCode=={}, tradingDay=={}, result row -> {}", instrumentCode,
						tradingDay, list.size());
			return list;
		}, e -> {
			log.error("query [BarEntity] exception, instrumentCode=={}, tradingDay=={}, e.getMessage() -> {}",
					instrumentCode, tradingDay, e.getMessage(), e);
		}, ArrayList::new);
	}

	/**
	 * 
	 * @param bar
	 * @return
	 */
	public boolean putBar(@Nonnull final BarEntity bar) {
		return booleanFun(() -> dao.save(bar), o -> {
			log.info("put [BarEntity] success, bar -> {}", bar);
			return true;
		}, e -> {
			log.error("put [BarEntity] failure, bar -> {}, e.getMessage() -> {}", bar, e.getMessage(), e);
			return false;
		});
	}

}
