package io.redstone.core.account;

abstract class Balance {

	private double availableMargin;
	private double availableCredit;

	public final double availableMargin() {
		return availableMargin;
	}

	public final void availableMargin(double availableMargin) {
		this.availableMargin = availableMargin;
	}

	public final double availableCredit() {
		return availableCredit;
	}

	public final void availableCredit(double availableCredit) {
		this.availableCredit = availableCredit;
	}

}
