package io.cygnus.db.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;

import io.cygnus.db.CommonDaoFactory;
import io.cygnus.service.entity.TimeBinner;
import io.mercury.common.log.CommonLoggerFactory;

public class BinnerDao {

	private Logger logger = CommonLoggerFactory.getLogger(getClass());

	public List<TimeBinner> getTimeBinners(Integer cygId, Date dateTradingDay, String instrumentId) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings("unchecked")
		List<TimeBinner> list = session.createCriteria(TimeBinner.class)
				.add(Restrictions.eq(TimeBinner.COLUMN_NAME_CygId, cygId))
				.add(Restrictions.eq(TimeBinner.COLUMN_NAME_TradingDay, dateTradingDay))
				.add(Restrictions.eq(TimeBinner.COLUMN_NAME_InstrumentId, instrumentId)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public boolean addTimeBinners(TimeBinner timeBinner) {
		Session session = CommonDaoFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			@SuppressWarnings("unchecked")
			List<TimeBinner> queryForFieldValues = session.createCriteria(TimeBinner.class)
					.add(Restrictions.eq(TimeBinner.COLUMN_NAME_CygId, timeBinner.getCygId()))
					.add(Restrictions.eq(TimeBinner.COLUMN_NAME_TradingDay, timeBinner.getTradingDay()))
					.add(Restrictions.eq(TimeBinner.COLUMN_NAME_Time, timeBinner.getTime()))
					.add(Restrictions.eq(TimeBinner.COLUMN_NAME_InstrumentId, timeBinner.getInstrumentId())).list();
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
