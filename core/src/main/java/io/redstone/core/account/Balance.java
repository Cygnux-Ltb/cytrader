package io.redstone.core.account;

abstract class Balance {

	private double availableMargin;
	private double availableCredit;

	public final double getAvailableMargin() {
		return availableMargin;
	}

	public final void setAvailableMargin(double availableMargin) {
		this.availableMargin = availableMargin;
	}

	public final double getAvailableCredit() {
		return availableCredit;
	}

	public final void setAvailableCredit(double availableCredit) {
		this.availableCredit = availableCredit;
	}

}
