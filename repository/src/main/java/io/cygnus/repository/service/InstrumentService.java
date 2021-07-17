package io.cygnus.repository.service;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Resource;

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
	private InstrumentDao instrumentDao;

	@Resource
	private InstrumentSettlementDao instrumentSettlementDao;

	private static final Logger log = CommonLoggerFactory.getLogger(InstrumentService.class);

	/**
	 * 
	 * @param instrumentCode
	 * @return
	 */
	public List<InstrumentEntity> getInstrument(@Nonnull String instrumentCode) {
		List<InstrumentEntity> list = instrumentDao.query(instrumentCode);
		log.info("query Instrument where instrumentCode == {}, result -> {}", JsonWrapper.toJson(list));
		return list;
	}

	/**
	 * 
	 * @param instrumentCode
	 * @param tradingDay
	 * @return
	 */
	public List<InstrumentSettlementEntity> getInstrumentSettlement(@Nonnull String instrumentCode, int tradingDay) {
		List<InstrumentSettlementEntity> list = instrumentSettlementDao.query(instrumentCode, tradingDay);
		log.info("query Instrument where instrumentCode == {} and tradingDay == {}, result -> {}",
				JsonWrapper.toJson(list));
		return list;
	}

}
