package io.cygnus.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.AccountEntity;

import java.util.List;

/**
 * @author yellow013
 * <p>
 * AccountDao
 */

@Repository
public interface AccountDao extends JpaRepository<AccountEntity, Long> {

    List<AccountEntity> queryBySubAccountId(int subAccountId);

    List<AccountEntity> queryByAccountId(int accountId);

    List<AccountEntity> queryByInvestorId(String investorId);

    List<AccountEntity> queryByBrokerId(String brokerId);

}
