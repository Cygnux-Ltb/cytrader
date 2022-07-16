package io.cygnux.console.service;

import io.cygnux.repository.dao.AccountDao;
import io.cygnux.repository.entities.TAccount;
import io.mercury.common.log.Log4j2LoggerFactory;
import io.mercury.serialization.json.JsonWrapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static io.mercury.common.functional.Functions.exec;

@Service
public class AccountService {

    private static final Logger log = Log4j2LoggerFactory.getLogger(AccountService.class);

    @Resource
    private AccountDao dao;

    public List<TAccount> getAccount(int accountId) {
        exec(() -> dao.queryByAccountId(accountId), list -> list, e -> {
        });
        List<TAccount> list = dao.queryByAccountId(accountId);
        log.info("query Account where accountId == {}, result -> {}", accountId, JsonWrapper.toJson(list));
        return list;
    }

}