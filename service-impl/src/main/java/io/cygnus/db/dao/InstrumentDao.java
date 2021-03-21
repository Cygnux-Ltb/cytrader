package io.cygnus.db.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import io.cygnus.db.CommonDaoFactory;
import io.cygnus.service.entity.InstrumentSettlementPrice;

public class InstrumentDao {

	public List<InstrumentSettlementPrice> getSettlementPrice(Date dateTradingDay, String instrumentId) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<InstrumentSettlementPrice> list = session.createCriteria(InstrumentSettlementPrice.class)
				.add(Restrictions.eq(InstrumentSettlementPrice.COLUMN_NAME_TradingDay, dateTradingDay))
				.add(Restrictions.eq(InstrumentSettlementPrice.COLUMN_NAME_InstrumentID, instrumentId)).list();
		CommonDaoFactory.close(session);
		return list;
	}

}
