package io.cygnus.repository.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import io.cygnus.repository.dao.OrderDao;
import io.cygnus.repository.dao.OrderEventDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import io.cygnus.repository.db.CommonDaoFactory;
import io.cygnus.repository.CygOrderRepository;
import io.cygnus.repository.entity.OrderEntity;

@Component
public class OrderService {

	@Resource
	private OrderDao orderDao;

	@Resource
	private OrderEventDao orderEventDao;

	public List<OrderEntity> getOrders(int strategyId, int tradingDay, String investorId, String instrumentCode) {
		return null;
	}

	public List<OrderEntity> getOrdersByInit(int tradingDay, int strategyId) {


		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<OrderEntity> list = session.createCriteria(OrderEntity.class)
				.add(Restrictions.eq(OrderEntity.COLUMN_TradingDay, dateTradingDay))
				.add(Restrictions.eq(OrderEntity.COLUMN_StrategyID, strategyId)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public boolean addOrder(OrderEntity order) {
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
