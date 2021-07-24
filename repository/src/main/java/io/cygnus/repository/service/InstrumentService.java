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

	@Resource
	private InstrumentDao dao;

	@Resource
	private InstrumentSettlementDao settlementDao;

	private static final Logger log = CommonLoggerFactory.getLogger(InstrumentService.class);

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
			log.error("query [InstrumentEntity] exception, instrumentCode=={}, e.getMessage() -> {}", instrumentCode,
					e.getMessage(), e);
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
			log.error(
					"query [InstrumentSettlementEntity] exception, instrumentCode=={}, tradingDay=={}, e.getMessage() -> {}",
					instrumentCode, tradingDay, e.getMessage(), e);
		});
	}

	/**
	 * 
	 * @param instrument
	 * @return
	 */
	public boolean putInstrument(@Nonnull InstrumentEntity instrument) {
		return booleanFun(() -> dao.save(instrument), o -> {
			log.info("save [InstrumentEntity] success, instrument -> {}", instrument);
			return true;
		}, e -> {
			log.error("save [InstrumentEntity] failure, instrument -> {}, e.getMessage() -> {}", instrument,
					e.getMessage(), e);
			return false;
		});
	}

	/**
	 * 
	 * @param instrumentSettlement
	 * @return
	 */
	public boolean putInstrumentSettlement(@Nonnull InstrumentSettlementEntity instrumentSettlement) {
		return booleanFun(() -> settlementDao.save(instrumentSettlement), o -> {
			log.info("save [InstrumentSettlementEntity] success, instrumentSettlement -> {}", instrumentSettlement);
			return true;
		}, e -> {
			log.error("save [InstrumentSettlementEntity] failure, instrumentSettlement -> {}, e.getMessage() -> {}",
					instrumentSettlement, e.getMessage(), e);
			return false;
		});
	}

}
