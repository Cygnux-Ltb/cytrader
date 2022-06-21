package io.cygnux.repository.dao;

import io.cygnux.repository.entities.internal.InAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Account DAO
 *
 * @author yellow013
 */

@Repository
public interface AccountDao extends JpaRepository<InAccount, Long> {

    List<InAccount> queryByBrokerId(String brokerId);

    List<InAccount> queryByInvestorId(String investorId);

    List<InAccount> queryByAccountId(int accountId);

    List<InAccount> queryBySubAccountId(int subAccountId);

}
