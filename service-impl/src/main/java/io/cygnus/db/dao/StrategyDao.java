package io.cygnus.db.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import io.cygnus.db.CommonDaoFactory;
import io.cygnus.service.entity.Strategy;
import io.cygnus.service.entity.StrategyDefaultParam;
import io.cygnus.service.entity.StrategyParam;
import io.cygnus.service.entity.StrategySignal;
import io.cygnus.service.entity.StrategySymbol;

public class StrategyDao {

	public List<Strategy> getAllStrategy() {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Strategy> list = session.createCriteria(Strategy.class).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public List<Strategy> getStrategyById(Integer strategyId) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Strategy> list = session.createCriteria(Strategy.class)
				.add(Restrictions.eq(Strategy.COLUMN_StrategyID, strategyId)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public List<StrategyParam> getParamsByStrategyId(Integer strategyId) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<StrategyParam> list = session.createCriteria(StrategyParam.class)
				.add(Restrictions.eq(StrategyParam.COLUMN_NAME_StrategyID, strategyId)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public List<StrategyDefaultParam> getAllDefaultParam() {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<StrategyDefaultParam> list = session.createCriteria(StrategyDefaultParam.class).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public List<StrategySymbol> getSymbolsByStrategyId(Integer strategyId) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<StrategySymbol> list = session.createCriteria(StrategySymbol.class)
				.add(Restrictions.eq(StrategySymbol.COLUMN_NAME_StrategyID, strategyId)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public List<StrategySignal> getSignalByStrategyId(Integer strategyId) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<StrategySignal> list = session.createCriteria(StrategySignal.class)
				.add(Restrictions.eq(StrategySignal.COLUMN_NAME_StrategyID, strategyId)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public boolean putStrategyParam(StrategyParam strategyParam) {
		Session session = CommonDaoFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(StrategyParam.class);
			List<StrategyParam> queryResult = queryStrategyParam(criteria, strategyParam.getStrategyId(),
					strategyParam.getName(), strategyParam.getValueType());

			if (queryResult.size() == 0) {
				session.save(strategyParam);
			} else {
				StrategyParam queryResultObj = queryResult.get(0);
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

	public void putStrategySignal(StrategySignal strategySignal) {
		Session session = CommonDaoFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(StrategySignal.class);
			List<StrategySignal> queryResult = queryStrategySignal(criteria, strategySignal.getStrategyId(),
					strategySignal.getSignalID());
			if (queryResult.size() == 0) {
				session.save(strategySignal);
			} else {
				StrategySignal queryResultObj = queryResult.get(0);
				strategySignal.setUid(queryResultObj.getUid());
				session.update(strategySignal);
			}
		} catch (Exception e) {
			if (null != transaction) {
				transaction.rollback();
			}
		} finally {
			CommonDaoFactory.close(session);
		}

	}

	public int putStrategyParamOnlyUpdate(StrategyParam strategyParam) {
		Session session = CommonDaoFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(StrategyParam.class);
			List<StrategyParam> queryResult = queryStrategyParam(criteria, strategyParam.getStrategyId(),
					strategyParam.getName(), strategyParam.getValueType());
			if (queryResult.size() == 0) {
				return 0;
			} else {
				StrategyParam queryResultObj = queryResult.get(0);
				switch (queryResultObj.getValueType()) {
				case "String":
					queryResultObj.setValueString(strategyParam.getValueString());
					break;
				case "Int":
					queryResultObj.setValueInt(strategyParam.getValueInt());
					break;
				case "Double":
					queryResultObj.setValueDouble(strategyParam.getValueDouble());
					break;
				case "Date":
					queryResultObj.setValueDate(strategyParam.getValueDate());
					break;
				default:
					return 0;
				}
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
	private List<StrategyParam> queryStrategyParam(Criteria criteria, int strategyId, String name, String valueType) {
		return criteria.add(Restrictions.eq(StrategyParam.COLUMN_NAME_StrategyID, strategyId))
				.add(Restrictions.eq(StrategyParam.COLUMN_NAME_Name, name))
				.add(Restrictions.eq(StrategyParam.COLUMN_NAME_ValueType, valueType)).list();
	}

	@SuppressWarnings("unchecked")
	private List<StrategySignal> queryStrategySignal(Criteria criteria, int strategyId, int signalId) {
		return criteria.add(Restrictions.eq(StrategySignal.COLUMN_NAME_StrategyID, strategyId))
				.add(Restrictions.eq(StrategySignal.COLUMN_NAME_SignalID, signalId)).list();
	}

}
