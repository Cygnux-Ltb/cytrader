package io.cygnus.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.cygnus.repository.constant.CommonColumn;
import io.cygnus.repository.constant.EntityName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 
 * Account Entity
 * 
 * @author yellow013
 *
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "cyg_account")
@Entity(name = EntityName.Account)
public final class AccountEntity {

	@Id
	@Column(name = CommonColumn.UID)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uid;

	@Column(name = "sub_account_id")
	private int subAccountId;

	@Column(name = "account_id")
	private int accountId;

	@Column(name = CommonColumn.BROKER_ID)
	private String brokerId;

	@Column(name = CommonColumn.INVESTOR_ID)
	private String investorId;

	@Column(name = "adaptor_type")
	private String adaptorType;

}
