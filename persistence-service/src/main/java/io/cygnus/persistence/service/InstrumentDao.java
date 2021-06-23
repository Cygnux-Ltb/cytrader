package io.cygnus.persistence.service;

import org.springframework.data.repository.CrudRepository;

import io.cygnus.persistence.entity.CygInstrumentSettlementPrice;

public interface InstrumentDao extends CrudRepository<CygInstrumentSettlementPrice, Long> {

//	public List<InstrumentSettlementPrice> getSettlementPrice(Date dateTradingDay, String instrumentId) {
//		Session session = CommonDaoFactory.getSession();
//		@SuppressWarnings({ "unchecked", "deprecation" })
//		List<InstrumentSettlementPrice> list = session.createCriteria(InstrumentSettlementPrice.class)
//				.add(Restrictions.eq(InstrumentSettlementPrice.COLUMN_NAME_TradingDay, dateTradingDay))
//				.add(Restrictions.eq(InstrumentSettlementPrice.COLUMN_NAME_InstrumentID, instrumentId)).list();
//		CommonDaoFactory.close(session);
//		return list;
//	}

}
