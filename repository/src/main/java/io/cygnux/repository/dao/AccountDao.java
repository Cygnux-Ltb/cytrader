package io.cygnux.repository.dao;

import io.cygnux.repository.entities.ItAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Account DAO
 *
 * @author yellow013
 */

@Repository
public interface AccountDao extends JpaRepository<ItAccount, Long> {

    List<ItAccount> queryByBrokerId(String brokerId);

    List<ItAccount> queryByInvestorId(String investorId);

    List<ItAccount> queryByAccountId(int accountId);

    List<ItAccount> queryBySubAccountId(int subAccountId);

}
