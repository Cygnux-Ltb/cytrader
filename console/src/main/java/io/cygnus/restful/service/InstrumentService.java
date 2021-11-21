package io.cygnus.restful.service;

import static io.mercury.common.functional.Functions.exec;
import static io.mercury.common.functional.Functions.execBool;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import io.cygnus.repository.dao.InstrumentDao;
import io.cygnus.repository.dao.InstrumentSettlementDao;
import io.cygnus.repository.entity.CygInstrument;
import io.cygnus.repository.entity.CygInstrumentStatic;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.serialization.json.JsonWrapper;

@Service
public final class InstrumentService {

	private final Logger log = CommonLoggerFactory.getLogger(InstrumentService.class);

	@Resource
	private InstrumentDao dao;

	@Resource
	private InstrumentSettlementDao settlementDao;

	/**
	 * 
	 * @param instrumentCode
	 * @return
	 */
	public List<CygInstrument> getInstrument(@Nonnull String instrumentCode) {
		return exec(() -> dao.query(instrumentCode), list -> {
			if (CollectionUtils.isEmpty(list))
				log.warn("query [InstrumentEntity] return 0 row, instrumentCode=={}", instrumentCode);
			else
				log.info("query [InstrumentEntity] where instrumentCode=={}, result -> {}", instrumentCode,
						JsonWrapper.toPrettyJsonHasNulls(list));
			return list;
		}, e -> {
			log.error("query [InstrumentEntity] exception, instrumentCode=={}", instrumentCode, e);
		});
	}

	/**
	 * 
	 * @param instrumentCode
	 * @param tradingDay
	 * @return
	 */
	public List<CygInstrumentStatic> getInstrumentSettlement(@Nonnull String instrumentCode, int tradingDay) {
		return exec(() -> settlementDao.query(instrumentCode, tradingDay), list -> {
			if (CollectionUtils.isEmpty(list))
				log.warn("query [InstrumentSettlementEntity] return 0 row, instrumentCode=={}, tradingDay=={}",
						instrumentCode, tradingDay);
			else
				log.info("query [InstrumentSettlementEntity] where instrumentCode=={} and tradingDay=={}, result -> {}",
						instrumentCode, tradingDay, JsonWrapper.toPrettyJsonHasNulls(list));
			return list;
		}, e -> {
			log.error("query [InstrumentSettlementEntity] exception, instrumentCode=={}, tradingDay=={}",
					instrumentCode, tradingDay, e);
		});
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public boolean putInstrument(@Nonnull CygInstrument entity) {
		return execBool(() -> dao.save(entity), o -> {
			log.info("save [InstrumentEntity] success, entity -> {}", entity);
			return true;
		}, e -> {
			log.error("save [InstrumentEntity] failure, entity -> {}", entity, e);
			return false;
		});
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public boolean putInstrumentSettlement(@Nonnull CygInstrumentStatic entity) {
		return execBool(() -> settlementDao.save(entity), o -> {
			log.info("save [InstrumentSettlementEntity] success -> {}", entity);
			return true;
		}, e -> {
			log.error("save [InstrumentSettlementEntity] failure -> {}", entity, e);
			return false;
		});
	}

}
