package io.cygnux.repository.dao;

import io.cygnux.repository.entities.TAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Account DAO
 *
 * @author yellow013
 */

@Repository
public interface AccountDao extends JpaRepository<TAccount, Long> {

    List<TAccount> queryByBrokerId(String brokerId);

    List<TAccount> queryByInvestorId(String investorId);

    List<TAccount> queryByAccountId(int accountId);

    List<TAccount> queryBySubAccountId(int subAccountId);

}
