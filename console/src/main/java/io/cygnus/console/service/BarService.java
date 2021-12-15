package io.cygnus.console.service;

import static io.mercury.common.functional.Functions.exec;
import static io.mercury.common.functional.Functions.execBool;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import io.cygnus.repository.dao.BarDao;
import io.cygnus.repository.entity.CygBar;
import io.mercury.common.log.Log4j2LoggerFactory;

@Service
public class BarService {

	private final Logger log = Log4j2LoggerFactory.getLogger(BarService.class);

	@Resource
	private BarDao dao;

	/**
	 * 
	 * @param instrumentCode
	 * @param tradingDay
	 * @return
	 */
	public List<CygBar> getBars(@Nonnull String instrumentCode, int tradingDay) {
		return exec(() -> dao.query(instrumentCode, tradingDay), list -> {
			if (CollectionUtils.isEmpty(list))
				log.warn("query [BarEntity] return 0 row, instrumentCode=={}, tradingDay=={}", instrumentCode,
						tradingDay);
			else
				log.info("query [BarEntity] where instrumentCode=={}, tradingDay=={}, result row -> {}", instrumentCode,
						tradingDay, list.size());
			return list;
		}, e -> {
			log.error("query [BarEntity] exception, instrumentCode=={}, tradingDay=={}", instrumentCode, tradingDay, e);
		});
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public boolean putBar(@Nonnull CygBar entity) {
		return execBool(() -> dao.save(entity), o -> {
			log.info("save [BarEntity] success -> {}", entity);
			return true;
		}, e -> {
			log.error("save [BarEntity] failure -> {}", entity, e);
			return false;
		});
	}

}
