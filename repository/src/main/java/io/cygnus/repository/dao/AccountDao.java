package io.cygnus.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cygnus.repository.entity.CygAccount;

import java.util.List;

/**
 * Account DAO
 * 
 * @author yellow013
 * 
 */

@Repository
public interface AccountDao extends JpaRepository<CygAccount, Long> {

	List<CygAccount> queryByBrokerId(String brokerId);

	List<CygAccount> queryByInvestorId(String investorId);

	List<CygAccount> queryByAccountId(int accountId);

	List<CygAccount> queryBySubAccountId(int subAccountId);

}
