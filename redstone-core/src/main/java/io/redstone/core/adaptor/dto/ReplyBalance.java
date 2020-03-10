package io.redstone.core.adaptor.dto;

public class ReplyBalance extends InvestorDto {

	private int balance;

	ReplyBalance(int investorId, int balance) {
		super(investorId);
		this.balance = balance;
	}

	public int getBalance() {
		return balance;
	}

}
