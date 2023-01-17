package io.cygnux.console.service;

import io.cygnux.console.persistence.dao.AccountDao;
import io.cygnux.console.persistence.entity.AccountEntity;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.cygnux.console.persistence.util.DaoExecutor.select;

@Service
public class AccountService {

    @Resource
    private AccountDao accountDao;

    public List<AccountEntity> getAccount(int accountId) {
        return select(() -> accountDao.queryByAccountId(accountId), AccountEntity.class);
    }

}