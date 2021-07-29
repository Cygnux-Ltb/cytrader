package io.cygnus.repository.service;

import static io.mercury.common.functional.Functions.booleanFun;
import static io.mercury.common.functional.Functions.listFun;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import io.cygnus.repository.dao.InstrumentDao;
import io.cygnus.repository.dao.InstrumentSettlementDao;
import io.cygnus.repository.entity.InstrumentEntity;
import io.cygnus.repository.entity.InstrumentSettlementEntity;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.serialization.json.JsonWrapper;

@Service
public final class InstrumentService {

	private static final Logger log = CommonLoggerFactory.getLogger(InstrumentService.class);

	@Resource
	private InstrumentDao dao;

	@Resource
	private InstrumentSettlementDao settlementDao;

	/**
	 * 
	 * @param instrumentCode
	 * @return
	 */
	public List<InstrumentEntity> getInstrument(@Nonnull String instrumentCode) {
		return listFun(() -> dao.query(instrumentCode), list -> {
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
	public List<InstrumentSettlementEntity> getInstrumentSettlement(@Nonnull String instrumentCode, int tradingDay) {
		return listFun(() -> settlementDao.query(instrumentCode, tradingDay), list -> {
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
	public boolean putInstrument(@Nonnull InstrumentEntity entity) {
		return booleanFun(() -> dao.save(entity), o -> {
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
	public boolean putInstrumentSettlement(@Nonnull InstrumentSettlementEntity entity) {
		return booleanFun(() -> settlementDao.save(entity), o -> {
			log.info("save [InstrumentSettlementEntity] success -> {}", entity);
			return true;
		}, e -> {
			log.error("save [InstrumentSettlementEntity] failure -> {}", entity, e);
			return false;
		});
	}

}
