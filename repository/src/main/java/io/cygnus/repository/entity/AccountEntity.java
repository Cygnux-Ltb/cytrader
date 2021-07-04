package io.cygnus.repository.entity;

import io.cygnus.repository.constant.ColumnDefinition;
import io.cygnus.repository.constant.CommonColumn;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table
@Entity
public final class AccountEntity {

	@Id
	@Column(name = CommonColumn.UID)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uid;

	@Column(name = AccountColumn.SUB_ACCOUNT_ID)
	private int subAccountId;

	@Column(name = AccountColumn.ACCOUNT_ID)
	private int accountId;

	@Column(name = CommonColumn.BROKER_ID)
	private String brokerId;

	@Column(name = CommonColumn.INVESTOR_ID)
	private String investorId;

	@Column(name = "adaptor_type")
	private String adaptorType;

	/**
	 * 
	 * @author yellow013
	 *
	 */
	public interface AccountColumn {

		String SUB_ACCOUNT_ID = "sub_account_id";
		String ACCOUNT_ID = "account_id";

	}

}
