package io.mercury.redstone.core.adaptor.dto;

public final class ReplyBalance {

	private int investorId;
	private int balance;

	public ReplyBalance(int investorId, int balance) {
		this.investorId = investorId;
		this.balance = balance;
	}

	public int getInvestorId() {
		return investorId;
	}

	public int getBalance() {
		return balance;
	}

}
