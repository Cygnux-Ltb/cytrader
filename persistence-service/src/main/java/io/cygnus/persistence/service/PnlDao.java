package io.cygnus.db.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import io.cygnus.db.CommonDaoFactory;
import io.cygnus.service.entity.StrategyInstrumentPNLDaily;
import io.cygnus.service.entity.StrategyInstrumentPNLSettlementDaily;

public class PnlDao {

	public List<StrategyInstrumentPNLDaily> queryPnlDailys(Date dateTradingDay, Integer strategyId) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<StrategyInstrumentPNLDaily> list = session.createCriteria(StrategyInstrumentPNLDaily.class)
				.add(Restrictions.eq(StrategyInstrumentPNLDaily.COLUMN_NAME_TradingDay, dateTradingDay))
				.add(Restrictions.eq(StrategyInstrumentPNLDaily.COLUMN_NAME_StrategyID, strategyId))
				.add(Restrictions.eq(StrategyInstrumentPNLDaily.COLUMN_NAME_Approved, 'Y')).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public List<StrategyInstrumentPNLSettlementDaily> queryPnlSettlementDailys(Date dateTradingDay,
			Integer strategyId) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<StrategyInstrumentPNLSettlementDaily> list = session
				.createCriteria(StrategyInstrumentPNLSettlementDaily.class)
				.add(Restrictions.eq(StrategyInstrumentPNLSettlementDaily.COLUMN_NAME_TradingDay, dateTradingDay))
				.add(Restrictions.eq(StrategyInstrumentPNLSettlementDaily.COLUMN_NAME_StrategyID, strategyId)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public void addOrUpdatePnlDailys(StrategyInstrumentPNLDaily pnlDaily) {
		Session session = CommonDaoFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			@SuppressWarnings({ "unchecked", "deprecation" })
			List<StrategyInstrumentPNLDaily> queryResult = session.createCriteria(StrategyInstrumentPNLDaily.class)
					.add(Restrictions.eq(StrategyInstrumentPNLDaily.COLUMN_NAME_StrategyID, pnlDaily.getStrategyId()))
					.add(Restrictions.eq(StrategyInstrumentPNLDaily.COLUMN_NAME_InstrumentID,
							pnlDaily.getInstrumentId()))
					.add(Restrictions.eq(StrategyInstrumentPNLDaily.COLUMN_NAME_TradingDay, pnlDaily.getTradingDay()))
					.list();
			if (queryResult.size() == 0) {
				session.save(pnlDaily);
			} else {
				StrategyInstrumentPNLDaily queryResultObj = queryResult.get(0);
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
