package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.dao.AccountDao;
import io.cygnuxltb.console.persistence.entity.AccountEntity;
import io.cygnuxltb.console.service.bean.OutboundConverter;
import io.cygnuxltb.protocol.http.outbound.AccountDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static io.cygnuxltb.console.persistence.util.DaoExecutor.select;

@Service
public class AccountService {

    @Resource
    private AccountDao accountDao;

    public List<AccountDTO> getAccount(int accountId) {
        return select(AccountEntity.class,
                () -> accountDao.queryByAccountId(accountId))
                .stream()
                .map(OutboundConverter::toAccountDTO)
                .collect(Collectors.toList());
    }

}