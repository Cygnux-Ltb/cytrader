package io.cygnus.db.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import io.cygnus.db.CommonDaoFactory;
import io.cygnus.service.entity.Order;

public class OrdersDao {

	public List<Order> getOrders(Integer strategyId, Date dateTradingDay, String investorId, String instrumentId) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings("unchecked")
		List<Order> list = session.createCriteria(Order.class)
				.add(Restrictions.eq(Order.COLUMN_NAME_TradingDay, dateTradingDay))
				.add(Restrictions.eq(Order.COLUMN_NAME_StrategyID, strategyId))
				.add(Restrictions.eq(Order.COLUMN_NAME_InvestorID, investorId))
				.add(Restrictions.eq(Order.COLUMN_NAME_InstrumentID, instrumentId)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public List<Order> getOrdersByInit(Date dateTradingDay, Integer strategyId) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings("unchecked")
		List<Order> list = session.createCriteria(Order.class)
				.add(Restrictions.eq(Order.COLUMN_NAME_TradingDay, dateTradingDay))
				.add(Restrictions.eq(Order.COLUMN_NAME_StrategyID, strategyId)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public boolean addOrder(Order order) {
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
