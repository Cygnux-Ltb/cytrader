package io.cygnus.persistence.service;

import org.springframework.data.jpa.repository.JpaRepository;

import io.cygnus.persistence.entity.CygInstrumentSettlement;

public interface InstrumentDao extends JpaRepository<CygInstrumentSettlement, Long> {

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
