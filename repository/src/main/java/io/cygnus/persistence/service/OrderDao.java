package io.cygnus.persistence.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import io.cygnus.persistence.db.CommonDaoFactory;
import io.cygnus.repository.CygOrderRepository;
import io.cygnus.repository.entity.CygOrder;

@Component
public class OrderDao {

	@Resource
	private CygOrderRepository repository;

	public List<CygOrder> getOrders(Integer strategyId, Date dateTradingDay, String investorId, String instrumentCode) {
		
		
		
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<CygOrder> list = session.createCriteria(CygOrder.class)
				.add(Restrictions.eq(CygOrder.COLUMN_TradingDay, dateTradingDay))
				.add(Restrictions.eq(CygOrder.COLUMN_StrategyID, strategyId))
				.add(Restrictions.eq(CygOrder.COLUMN_InvestorID, investorId))
				.add(Restrictions.eq(CygOrder.COLUMN_InstrumentCode, instrumentCode)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public List<CygOrder> getOrdersByInit(Date dateTradingDay, Integer strategyId) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<CygOrder> list = session.createCriteria(CygOrder.class)
				.add(Restrictions.eq(CygOrder.COLUMN_TradingDay, dateTradingDay))
				.add(Restrictions.eq(CygOrder.COLUMN_StrategyID, strategyId)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public boolean addOrder(CygOrder order) {
		Session session = CommonDaoFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(order);
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

}
