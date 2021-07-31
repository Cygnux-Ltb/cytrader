package io.cygnus.repository.service;

import static io.mercury.common.functional.Functions.listFun;

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

	private final Logger log = CommonLoggerFactory.getLogger(AccountService.class);

	@Resource
	private AccountDao dao;

	public List<AccountEntity> getAccount(int accountId) {
		listFun(() -> dao.queryByAccountId(accountId), list -> list, e -> {

		});
		List<AccountEntity> list = dao.queryByAccountId(accountId);
		log.info("query Account where accountId == {}, result -> {}", JsonWrapper.toJson(list));
		return list;
	}

}