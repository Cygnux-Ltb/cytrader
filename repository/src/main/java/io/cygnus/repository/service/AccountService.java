package io.cygnus.repository.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import io.cygnus.repository.dao.AccountDao;
import io.cygnus.repository.entity.AccountEntity;
import io.mercury.common.log.CommonLoggerFactory;

@Service
public class AccountService {

	@Resource
	private AccountDao dao;
	
	private static final Logger log = CommonLoggerFactory.getLogger(AccountService.class);

	public List<AccountEntity> get(int accountId) {
		List<AccountEntity> list = dao.queryByAccountId(accountId);
		if (list == null)
			return new ArrayList<>();
		return list;
	}

}
