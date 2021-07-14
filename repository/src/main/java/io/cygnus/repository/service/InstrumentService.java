package io.cygnus.repository.service;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import io.cygnus.repository.dao.InstrumentDao;
import io.cygnus.repository.dao.InstrumentSettlementDao;
import io.cygnus.repository.entity.InstrumentEntity;
import io.cygnus.repository.entity.InstrumentSettlementEntity;

@Service
public final class InstrumentService {

	@Resource
	private InstrumentDao instrumentDao;

	@Resource
	private InstrumentSettlementDao instrumentSettlementDao;
	
	
	

	public List<InstrumentEntity> getInstrument(@Nonnull String instrumentCode) {
		return getInstrument(instrumentCode, 0);
	}

	public List<InstrumentEntity> getInstrument(@Nonnull String instrumentCode, int tradingDay) {
		List<InstrumentEntity> list = instrumentDao.query(instrumentCode, tradingDay);

		return list;
	}

	public List<InstrumentSettlementEntity> getInstrumentSettlement(@Nonnull String instrumentCode, int tradingDay) {
		List<InstrumentSettlementEntity> list = instrumentSettlementDao.query(instrumentCode, tradingDay);
		return list;
	}

}
