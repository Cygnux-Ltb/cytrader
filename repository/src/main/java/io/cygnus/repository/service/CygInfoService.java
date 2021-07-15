package io.cygnus.repository.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import io.cygnus.repository.dao.CygInfoDao;
import io.cygnus.repository.entity.CygInfoEntity;
import io.mercury.common.log.CommonLoggerFactory;

@Service
public class CygInfoService {

	@Resource
	private CygInfoDao dao;

	private static final Logger log = CommonLoggerFactory.getLogger(CygInfoService.class);

	/**
	 * 
	 * @return
	 */
	public List<CygInfoEntity> getCygInfos() {
		List<CygInfoEntity> list = dao.findAll();
		if (list == null) {
			log.error("");
			return new ArrayList<>();
		}
		return list;
	}

	/**
	 * 
	 * @param cygId
	 * @return
	 */
	public CygInfoEntity getCygInfo(int cygId) {
		CygInfoEntity cygInfo = dao.getById(cygId);
		return cygInfo;
	}

}
