package io.cygnus.repository.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import io.cygnus.repository.dao.AccountDao;
import io.cygnus.repository.entity.AccountEntity;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.serialization.json.JsonWrapper;

@Service
public class AccountService {

	@Resource
	private AccountDao dao;
	
	private static final Logger log = CommonLoggerFactory.getLogger(AccountService.class);

	public List<AccountEntity> getAccount(int accountId) {
		List<AccountEntity> list = dao.queryByAccountId(accountId);
		log.info("query Account where accountId == {}, result -> {}", JsonWrapper.toJson(list));
		return list;
	}

}
