package io.cygnus.console.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import io.cygnus.repository.dao.CygInfoDao;
import io.cygnus.repository.entity.CygInfoEntity;
import io.mercury.common.log.Log4j2LoggerFactory;

@Service
public class CygInfoService {

	private final Logger log = Log4j2LoggerFactory.getLogger(CygInfoService.class);

	@Resource
	private CygInfoDao dao;

	/**
	 * 
	 * @return
	 */
	public List<CygInfoEntity> getAll() {
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
	public CygInfoEntity get(int cygId) {
		CygInfoEntity cygInfo = dao.getById(cygId);
		return cygInfo;
	}

}
