package io.cygnus.console.service;

import static io.mercury.common.functional.Functions.exec;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import io.cygnus.repository.dao.AccountDao;
import io.cygnus.repository.entity.CygAccount;
import io.mercury.common.log.Log4j2LoggerFactory;
import io.mercury.serialization.json.JsonWrapper;

@Service
public class AccountService {

	private final Logger log = Log4j2LoggerFactory.getLogger(AccountService.class);

	@Resource
	private AccountDao dao;

	public List<CygAccount> getAccount(int accountId) {
		exec(() -> dao.queryByAccountId(accountId), list -> list, e -> {

		});
		List<CygAccount> list = dao.queryByAccountId(accountId);
		log.info("query Account where accountId == {}, result -> {}", JsonWrapper.toJson(list));
		return list;
	}

}