package io.cygnus.persistence.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import io.cygnus.persistence.db.CommonDaoFactory;
import io.cygnus.persistence.entity.CygInfo;
import io.cygnus.persistence.entity.CygInitConfig;
import io.cygnus.persistence.entity.CygMqConfig;

public class CygInfoDao {

	/**
	 * 
	 * @return
	 */
	public List<CygInfo> getAllCygInfo() {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
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
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<CygInfo> list = session.createCriteria(CygInfo.class).add(Restrictions.eq(CygInfo.COLUMN_CygID, cygId))
				.list();
		CommonDaoFactory.close(session);
		return list;
	}

	public List<CygMqConfig> getCygMqConfigById(Integer cygId) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<CygMqConfig> list = session.createCriteria(CygMqConfig.class)
				.add(Restrictions.eq(CygMqConfig.COLUMN_CygID, cygId)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public List<CygInitConfig> getCygInitConfigById(Integer cygId) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<CygInitConfig> list = session.createCriteria(CygInitConfig.class)
				.add(Restrictions.eq(CygMqConfig.COLUMN_CygID, cygId)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public static void main(String[] args) {
		try {
			Session session = CommonDaoFactory.getSession();
			@SuppressWarnings("deprecation")
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
