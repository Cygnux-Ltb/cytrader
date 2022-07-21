package io.cygnux.repository.dao;

import io.cygnux.repository.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Account DAO
 *
 * @author yellow013
 */

@Repository
public interface AccountDao extends JpaRepository<AccountEntity, Long> {

    List<AccountEntity> queryByBrokerId(String brokerId);

    List<AccountEntity> queryByInvestorId(String investorId);

    List<AccountEntity> queryByAccountId(int accountId);

    List<AccountEntity> queryBySubAccountId(int subAccountId);

}
