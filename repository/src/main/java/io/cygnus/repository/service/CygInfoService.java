package io.cygnus.repository.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import io.cygnus.repository.db.CommonDaoFactory;
import io.cygnus.repository.entity.CygInfoEntity;
import io.cygnus.repository.entity.CygInfoEntity.CygInfoQueryColumn;

public class CygInfoService {

	/**
	 * 
	 * @return
	 */
	public List<CygInfoEntity> getAllCygInfo() {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<CygInfoEntity> list = session.createCriteria(CygInfoEntity.class).list();
		CommonDaoFactory.close(session);
		return list;
	}

	/**
	 * 
	 * @return
	 */
	public List<Integer> getAllCygId() {
		List<CygInfoEntity> allCygInfos = getAllCygInfo();
		List<Integer> cygIds = new ArrayList<>();
		for (CygInfoEntity CygInfo : allCygInfos) {
			cygIds.add(CygInfo.getCygId());
		}
		return cygIds;
	}

	/**
	 * 
	 * @param cygId
	 * @return
	 */
	public List<CygInfoEntity> getCygInfoById(Integer cygId) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<CygInfoEntity> list = session.createCriteria(CygInfoEntity.class).add(Restrictions.eq(CygInfoQueryColumn.CYG_ID, cygId))
				.list();
		CommonDaoFactory.close(session);
		return list;
	}

	public static void main(String[] args) {
		try {
			Session session = CommonDaoFactory.getSession();
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(CygInfoEntity.class);
			@SuppressWarnings("unchecked")
			List<CygInfoEntity> cygInfos = criteria.list();
			// String json = JSON.toJSONString(cygInfos);
			// System.out.println(json);

			for (CygInfoEntity cygInfo : cygInfos) {
				System.out.println(cygInfo.getCygId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
