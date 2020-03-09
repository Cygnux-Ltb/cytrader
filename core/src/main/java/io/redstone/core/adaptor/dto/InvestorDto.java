package io.redstone.core.adaptor.dto;

abstract class InvestorDto {

	private int investorId;

	InvestorDto(int investorId) {
		this.investorId = investorId;
	}

	public int getInvestorId() {
		return investorId;
	}

}
