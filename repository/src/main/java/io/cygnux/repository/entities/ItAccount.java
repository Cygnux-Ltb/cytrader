package io.cygnux.repository.entities;

import io.cygnux.repository.constant.CommonColumn;
import jakarta.persistence.*;
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
@Table(name = "in_account")
@Entity(name = ItAccount.EntityName)
public final class ItAccount {

	public final static String EntityName = "ItAccount";

	@Id
	@Column(name = CommonColumn.UID)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uid;

	@Column(name = "sub_account_id", nullable = false)
	private int subAccountId;

	@Column(name = "account_id", nullable = false)
	private int accountId;

	@Column(name = CommonColumn.BROKER_ID, nullable = false)
	private String brokerId;

	@Column(name = "investor_id")
	private String investorId;

	@Column(name = "adaptor_type", nullable = false)
	private String adaptorType;

}
