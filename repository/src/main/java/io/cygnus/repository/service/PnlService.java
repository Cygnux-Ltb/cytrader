package io.cygnus.repository.service;

import java.util.Date;
import java.util.List;

import io.cygnus.repository.dao.PnlDailyDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import io.cygnus.repository.db.CommonDaoFactory;
import io.cygnus.repository.entity.PnlDailyEntity;
import io.cygnus.repository.entity.PnlSettlementDailyEntity;
import org.springframework.stereotype.Component;

@Component
public final class PnlService {

	private PnlDailyDao pnlDailyDao;

	public List<PnlDailyEntity> queryPnlDailys(Integer strategyId, Date dateTradingDay ) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<PnlDailyEntity> list = session.createCriteria(PnlDailyEntity.class)
				.add(Restrictions.eq(PnlDailyEntity.COLUMN_TradingDay, dateTradingDay))
				.add(Restrictions.eq(PnlDailyEntity.COLUMN_StrategyID, strategyId))
				.add(Restrictions.eq(PnlDailyEntity.COLUMN_Approved, 'Y')).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public List<PnlSettlementDailyEntity> queryPnlSettlementDailys(Integer strategyId, Date dateTradingDay) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<PnlSettlementDailyEntity> list = session.createCriteria(PnlSettlementDailyEntity.class)
				.add(Restrictions.eq(PnlSettlementDailyEntity.COLUMN_TradingDay, dateTradingDay))
				.add(Restrictions.eq(PnlSettlementDailyEntity.COLUMN_StrategyID, strategyId)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public void addOrUpdatePnlDailys(PnlDailyEntity pnlDaily) {
		Session session = CommonDaoFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			@SuppressWarnings({ "unchecked", "deprecation" })
			List<PnlDailyEntity> queryResult = session.createCriteria(PnlDailyEntity.class)
					.add(Restrictions.eq(PnlDailyEntity.COLUMN_StrategyID, pnlDaily.getStrategyId()))
					.add(Restrictions.eq(PnlDailyEntity.COLUMN_InstrumentCode, pnlDaily.getInstrumentCode()))
					.add(Restrictions.eq(PnlDailyEntity.COLUMN_TradingDay, pnlDaily.getTradingDay())).list();
			if (queryResult.size() == 0) {
				session.save(pnlDaily);
			} else {
				PnlDailyEntity queryResultObj = queryResult.get(0);
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
