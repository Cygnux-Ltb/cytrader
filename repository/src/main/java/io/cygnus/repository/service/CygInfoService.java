package io.cygnus.repository.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import io.cygnus.repository.dao.CygInfoDao;
import io.cygnus.repository.entity.CygInfoEntity;

@Service
public class CygInfoService {

	@Resource
	private CygInfoDao cygInfoDao;

	/**
	 * 
	 * @return
	 */
	public List<CygInfoEntity> getCygInfos() {
		List<CygInfoEntity> list = cygInfoDao.findAll();
		if (list == null)
			return new ArrayList<>();
		return list;
	}

	/**
	 * 
	 * @param cygId
	 * @return
	 */
	public CygInfoEntity getCygInfo(int cygId) {
		CygInfoEntity cygInfo = cygInfoDao.getById(cygId);
		return cygInfo;
	}

}
