package io.cygnus.repository.service;

import java.util.List;

import io.cygnus.repository.CygStrategyParamRepository;
import io.cygnus.repository.CygStrategyRepository;
import io.cygnus.repository.dao.StrategyDao;
import io.cygnus.repository.dao.StrategyParamDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import io.cygnus.repository.db.CommonDaoFactory;
import io.cygnus.repository.entity.StrategyEntity;
import io.cygnus.repository.entity.StrategyParamEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public final class StrategyService {

	@Resource
	private StrategyDao strategyDao;

	@Resource
	private StrategyParamDao strategyParamDao;

	/**
	 * 
	 * @return
	 */
	public List<StrategyEntity> getAllStrategy() {
		return strategyDao.findAll();
	}

	/**
	 * 
	 * @param strategyId
	 * @return
	 */
	public StrategyEntity getStrategyById(int strategyId) {
		return strategyDao.queryStrategyByStrategyId(strategyId);
	}

	/**
	 * 
	 * @param strategyId
	 * @return
	 */
	public List<StrategyParamEntity> getParamsByStrategyId(Integer strategyId) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<StrategyParamEntity> list = session.createCriteria(StrategyParamEntity.class)
				.add(Restrictions.eq(StrategyParamEntity.COLUMN_StrategyID, strategyId)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	/**
	 * 
	 */
	public boolean putStrategyParam(StrategyParamEntity strategyParam) {
		Session session = CommonDaoFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(StrategyParamEntity.class);
			List<StrategyParamEntity> queryResult = queryStrategyParam(criteria, strategyParam.getStrategyId(),
					strategyParam.getParamName(), strategyParam.getParamValueType());

			if (queryResult.size() == 0) {
				session.save(strategyParam);
			} else {
				StrategyParamEntity queryResultObj = queryResult.get(0);
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

	/**
	 * 
	 * @param strategyParam
	 * @return
	 */
	public int putStrategyParamOnlyUpdate(StrategyParamEntity strategyParam) {
		Session session = CommonDaoFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(StrategyParamEntity.class);
			List<StrategyParamEntity> queryResult = queryStrategyParam(criteria, strategyParam.getStrategyId(),
					strategyParam.getParamName(), strategyParam.getParamValueType());
			if (queryResult.size() == 0) {
				return 0;
			} else {
				StrategyParamEntity queryResultObj = queryResult.get(0);
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
	private List<StrategyParamEntity> queryStrategyParam(Criteria criteria, int strategyId, String paramName,
														 String paramValueType) {
		return criteria.add(Restrictions.eq(StrategyParamEntity.COLUMN_StrategyID, strategyId))
				.add(Restrictions.eq(StrategyParamEntity.COLUMN_ParamName, paramName))
				.add(Restrictions.eq(StrategyParamEntity.COLUMN_ParamValueType, paramValueType)).list();
	}

}
