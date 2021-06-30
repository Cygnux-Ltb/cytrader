package io.cygnus.persistence.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import io.cygnus.persistence.db.CommonDaoFactory;
import io.cygnus.repository.entity.CygPnlDaily;
import io.cygnus.repository.entity.CygPnlSettlementDaily;

public class PnlDao {

	public List<CygPnlDaily> queryPnlDailys(Integer strategyId, Date dateTradingDay ) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<CygPnlDaily> list = session.createCriteria(CygPnlDaily.class)
				.add(Restrictions.eq(CygPnlDaily.COLUMN_TradingDay, dateTradingDay))
				.add(Restrictions.eq(CygPnlDaily.COLUMN_StrategyID, strategyId))
				.add(Restrictions.eq(CygPnlDaily.COLUMN_Approved, 'Y')).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public List<CygPnlSettlementDaily> queryPnlSettlementDailys(Integer strategyId, Date dateTradingDay) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<CygPnlSettlementDaily> list = session.createCriteria(CygPnlSettlementDaily.class)
				.add(Restrictions.eq(CygPnlSettlementDaily.COLUMN_TradingDay, dateTradingDay))
				.add(Restrictions.eq(CygPnlSettlementDaily.COLUMN_StrategyID, strategyId)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public void addOrUpdatePnlDailys(CygPnlDaily pnlDaily) {
		Session session = CommonDaoFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			@SuppressWarnings({ "unchecked", "deprecation" })
			List<CygPnlDaily> queryResult = session.createCriteria(CygPnlDaily.class)
					.add(Restrictions.eq(CygPnlDaily.COLUMN_StrategyID, pnlDaily.getStrategyId()))
					.add(Restrictions.eq(CygPnlDaily.COLUMN_InstrumentCode, pnlDaily.getInstrumentCode()))
					.add(Restrictions.eq(CygPnlDaily.COLUMN_TradingDay, pnlDaily.getTradingDay())).list();
			if (queryResult.size() == 0) {
				session.save(pnlDaily);
			} else {
				CygPnlDaily queryResultObj = queryResult.get(0);
				pnlDaily.setUid(queryResultObj.getUid());
				session.update(pnlDaily);
			}
			transaction.commit();
		} catch (Exception e) {
			if (null != transaction) {
				transaction.rollback();
			}
		} finally {
			CommonDaoFactory.close(session);
		}
	}

}
