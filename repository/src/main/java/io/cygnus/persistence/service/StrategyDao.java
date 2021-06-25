package io.cygnus.persistence.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import io.cygnus.persistence.db.CommonDaoFactory;
import io.cygnus.repository.entity.CygStrategy;
import io.cygnus.repository.entity.CygStrategyParam;

public class StrategyDao {

	public List<CygStrategy> getAllStrategy() {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<CygStrategy> list = session.createCriteria(CygStrategy.class).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public List<CygStrategy> getStrategyById(Integer strategyId) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<CygStrategy> list = session.createCriteria(CygStrategy.class)
				.add(Restrictions.eq(CygStrategy.COLUMN_StrategyID, strategyId)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public List<CygStrategyParam> getParamsByStrategyId(Integer strategyId) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<CygStrategyParam> list = session.createCriteria(CygStrategyParam.class)
				.add(Restrictions.eq(CygStrategyParam.COLUMN_StrategyID, strategyId)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public boolean putStrategyParam(CygStrategyParam strategyParam) {
		Session session = CommonDaoFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(CygStrategyParam.class);
			List<CygStrategyParam> queryResult = queryStrategyParam(criteria, strategyParam.getStrategyId(),
					strategyParam.getParamName(), strategyParam.getParamValueType());

			if (queryResult.size() == 0) {
				session.save(strategyParam);
			} else {
				CygStrategyParam queryResultObj = queryResult.get(0);
				strategyParam.setUid(queryResultObj.getUid());
				session.update(strategyParam);
			}
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (null != transaction) {
				transaction.rollback();
			}
			return false;
		} finally {
			CommonDaoFactory.close(session);
		}

	}

	public int putStrategyParamOnlyUpdate(CygStrategyParam strategyParam) {
		Session session = CommonDaoFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(CygStrategyParam.class);
			List<CygStrategyParam> queryResult = queryStrategyParam(criteria, strategyParam.getStrategyId(),
					strategyParam.getParamName(), strategyParam.getParamValueType());
			if (queryResult.size() == 0) {
				return 0;
			} else {
				CygStrategyParam queryResultObj = queryResult.get(0);
				queryResultObj.setParamValue(strategyParam.getParamValue());
				session.update(queryResultObj);
				return 1;
			}
		} catch (Exception e) {
			if (null != transaction) {
				transaction.rollback();
			}
			return -1;
		} finally {
			CommonDaoFactory.close(session);
		}
	}

	@SuppressWarnings("unchecked")
	private List<CygStrategyParam> queryStrategyParam(Criteria criteria, int strategyId, String paramName,
			String paramValueType) {
		return criteria.add(Restrictions.eq(CygStrategyParam.COLUMN_StrategyID, strategyId))
				.add(Restrictions.eq(CygStrategyParam.COLUMN_ParamName, paramName))
				.add(Restrictions.eq(CygStrategyParam.COLUMN_ParamValueType, paramValueType)).list();
	}

}
