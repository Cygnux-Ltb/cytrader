package io.cygnus.repository.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import io.cygnus.repository.dao.AccountDao;
import io.cygnus.repository.entity.AccountEntity;

@Service
public class AccountService {

	@Resource
	private AccountDao accountDao;

	public List<AccountEntity> get(int accountId) {
		List<AccountEntity> list = accountDao.queryByAccountId(accountId);
		if (list == null)
			return new ArrayList<>();
		return list;
	}

}
