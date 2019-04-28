package io.ffreedom.redstone.core.account;

public class AccountInfo {

	private String accountName;
	private String investorId;
	private String investorPassword;
	private Broker broker;

	public final static AccountInfo EMPTY = new AccountInfo("empty", "empty", "empty", new Broker() {

		@Override
		public int getBroketId() {
			return -1;
		}

		@Override
		public String getBroketName() {
			return "empty";
		}

	});

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
