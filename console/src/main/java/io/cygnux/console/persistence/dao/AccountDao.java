package io.cygnux.console.dao;

import io.cygnux.console.entity.AccountEntity;
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
     * @param broker String
     * @return List<AccountEntity>
     */
    List<AccountEntity> queryByBroker(String broker);

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
