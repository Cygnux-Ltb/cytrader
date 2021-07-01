package io.cygnus.repository.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;

import io.cygnus.repository.db.CommonDaoFactory;
import io.cygnus.repository.entity.Bar;
import io.mercury.common.log.CommonLoggerFactory;

public class BarService {

	private static final Logger logger = CommonLoggerFactory.getLogger(BarService.class);

	public List<Bar> getTimeBinners(Integer cygId, Date dateTradingDay, String instrumentCode) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Bar> list = session.createCriteria(Bar.class).add(Restrictions.eq(Bar.COLUMN_CygId, cygId))
				.add(Restrictions.eq(Bar.COLUMN_TradingDay, dateTradingDay))
				.add(Restrictions.eq(Bar.COLUMN_InstrumentCode, instrumentCode)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public boolean addTimeBinners(Bar timeBinner) {
		Session session = CommonDaoFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			@SuppressWarnings({ "unchecked", "deprecation" })
			List<Bar> queryForFieldValues = session.createCriteria(Bar.class)
					.add(Restrictions.eq(Bar.COLUMN_CygId, timeBinner.getCygId()))
					.add(Restrictions.eq(Bar.COLUMN_TradingDay, timeBinner.getTradingDay()))
					.add(Restrictions.eq(Bar.COLUMN_TimePoint, timeBinner.getTimePoint()))
					.add(Restrictions.eq(Bar.COLUMN_InstrumentCode, timeBinner.getInstrumentCode())).list();
			if (queryForFieldValues.size() > 0) {
				// logger.error("Repeat consumption -> " + JSON.toJSONString(timeBinner));
				logger.error("Repeat consumption -> " + timeBinner.toString());
				return true;
			}
			session.save(timeBinner);
			transaction.commit();
		} catch (Exception e) {
			if (null != transaction) {
				transaction.rollback();
			}
		} finally {
			CommonDaoFactory.close(session);
		}
		return true;
	}

}
