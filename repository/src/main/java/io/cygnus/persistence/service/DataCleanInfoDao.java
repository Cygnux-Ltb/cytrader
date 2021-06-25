package io.cygnus.persistence.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import io.cygnus.persistence.db.CommonDaoFactory;
import io.cygnus.repository.entity.DataCleanInfo;

public class DataCleanInfoDao {

	public List<DataCleanInfo> getData(String location, Long tradingDay) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<DataCleanInfo> list = session.createCriteria(DataCleanInfo.class)
				.add(Restrictions.eq(DataCleanInfo.COLUMN_Location, location))
				.add(Restrictions.eq(DataCleanInfo.COLUMN_TradingDay, tradingDay)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public void addOrUpdateData(DataCleanInfo info) {
		Session session = CommonDaoFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			@SuppressWarnings({ "unchecked", "deprecation" })
			List<DataCleanInfo> list = session.createCriteria(DataCleanInfo.class)
					.add(Restrictions.eq(DataCleanInfo.COLUMN_Location, info.getLocation()))
					.add(Restrictions.eq(DataCleanInfo.COLUMN_TradingDay, info.getTradingDay())).list();
			if (list.size() == 0) {
				session.save(info);
			} else {
				info.setUid(list.get(0).getUid());
				session.update(info);
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

	public static void main(String[] args) {
		try {
			DataCleanInfoDao dao = new DataCleanInfoDao();
			DataCleanInfo info = new DataCleanInfo();
			info.setLocation("test");
			info.setTradingDay(20170311);
			info.setStartTime("555555");
			dao.addOrUpdateData(info);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
