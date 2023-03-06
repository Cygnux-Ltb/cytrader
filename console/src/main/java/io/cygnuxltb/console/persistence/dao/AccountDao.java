package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.AccountEntity;
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

    /**
     * @param brokerId String
     * @return List<AccountEntity>
     */
    List<AccountEntity> queryByBrokerId(String brokerId);

    /**
     * @param investorId String
     * @return List<AccountEntity>
     */
    List<AccountEntity> queryByInvestorId(String investorId);

    /**
     * @param accountId int
     * @return List<AccountEntity>
     */
    List<AccountEntity> queryByAccountId(int accountId);

    /**
     * @param subAccountId int
     * @return List<AccountEntity>
     */
    List<AccountEntity> queryBySubAccountId(int subAccountId);

}
