package io.cygnus.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import io.cygnus.db.CommonDaoFactory;
import io.cygnus.service.entity.CygInfo;
import io.cygnus.service.entity.CygInitConfig;
import io.cygnus.service.entity.CygMqConfig;
import io.cygnus.service.entity.CygStrategy;

public class CygInfoDao {

	public List<CygInfo> getAllCygInfo() {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings("unchecked")
		List<CygInfo> list = session.createCriteria(CygInfo.class).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public List<Integer> getAllCygId() {
		List<CygInfo> allCygInfos = getAllCygInfo();
		List<Integer> cygIds = new ArrayList<>();
		for (CygInfo CygInfo : allCygInfos) {
			cygIds.add(CygInfo.getCygId());
		}
		return cygIds;
	}

	public List<CygInfo> getCygInfoById(Integer cygId) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings("unchecked")
		List<CygInfo> list = session.createCriteria(CygInfo.class)
				.add(Restrictions.eq(CygInfo.COLUMN_NAME_CygID, cygId)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public List<CygStrategy> getCygStrategyById(Integer cygId) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings("unchecked")
		List<CygStrategy> list = session.createCriteria(CygStrategy.class)
				.add(Restrictions.eq(CygStrategy.COLUMN_NAME_CygID, cygId)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public List<CygMqConfig> getCygMqConfigById(Integer cygId) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings("unchecked")
		List<CygMqConfig> list = session.createCriteria(CygMqConfig.class)
				.add(Restrictions.eq(CygMqConfig.COLUMN_NAME_CygID, cygId)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public List<CygInitConfig> getCygInitConfigById(Integer cygId) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings("unchecked")
		List<CygInitConfig> list = session.createCriteria(CygInitConfig.class)
				.add(Restrictions.eq(CygMqConfig.COLUMN_NAME_CygID, cygId)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public static void main(String[] args) {
		try {
			Session session = CommonDaoFactory.getSession();
			Criteria criteria = session.createCriteria(CygInfo.class);
			@SuppressWarnings("unchecked")
			List<CygInfo> cygInfos = criteria.list();
			// String json = JSON.toJSONString(cygInfos);
			// System.out.println(json);

			for (CygInfo cygInfo : cygInfos) {
				System.out.println(cygInfo.getCygId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
