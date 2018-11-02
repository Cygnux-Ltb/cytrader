package io.ffreedom.redstone.core.account;

public class AccountInfo {

	private String accountName;
	private String investorId;
	private String investorPassword;
	private Broker broker;

	public AccountInfo(String accountName, String investorId, String investorPassword, Broker broker) {
		super();
		this.accountName = accountName;
		this.investorId = investorId;
		this.investorPassword = investorPassword;
		this.broker = broker;
	}

	public String getAccountName() {
		return accountName;
	}

	public String getInvestorId() {
		return investorId;
	}

	public String getInvestorPassword() {
		return investorPassword;
	}

	public Broker getBroker() {
		return broker;
	}

}
