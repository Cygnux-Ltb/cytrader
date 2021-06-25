package io.cygnus.persistence.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import io.cygnus.persistence.db.CommonDaoFactory;
import io.cygnus.repository.entity.CygInfo;
import io.cygnus.repository.entity.CygInfo.CygInfoColumn;

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

	/**
	 * 
	 * @return
	 */
	public List<Integer> getAllCygId() {
		List<CygInfo> allCygInfos = getAllCygInfo();
		List<Integer> cygIds = new ArrayList<>();
		for (CygInfo CygInfo : allCygInfos) {
			cygIds.add(CygInfo.getCygId());
		}
		return cygIds;
	}

	/**
	 * 
	 * @param cygId
	 * @return
	 */
	public List<CygInfo> getCygInfoById(Integer cygId) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<CygInfo> list = session.createCriteria(CygInfo.class).add(Restrictions.eq(CygInfoColumn.CYG_ID, cygId))
				.list();
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
