package io.mercury.redstone.core.order.structure;

public final class OrdQty {

	/**
	 * 委托数量
	 */
	private int offerQty;
	/**
	 * 剩余数量
	 */
	private int leavesQty;
	/**
	 * 已成交数量
	 */
	private int filledQty;
	/**
	 * 上一次成交数量
	 */
	private int lastFilledQty;

	private OrdQty(int offerQty) {
		this.offerQty = offerQty;
		this.leavesQty = offerQty;
	}

	public static final OrdQty withOffer(int offerQty) {
		return new OrdQty(offerQty);
	}

	public int offerQty() {
		return offerQty;
	}

	public OrdQty setOfferQty(int offerQty) {
		if (this.offerQty == 0) {
			this.offerQty = offerQty;
			this.leavesQty = offerQty;
		}
		return this;
	}

	public int leavesQty() {
		return leavesQty;
	}

	public OrdQty setLeavesQty(int leavesQty) {
		this.leavesQty = leavesQty;
		return this;
	}

	public int filledQty() {
		return filledQty;
	}

	public OrdQty setFilledQty(int filledQty) {
		this.lastFilledQty = this.filledQty;
		this.filledQty = filledQty;
		return this;
	}

	public int lastFilledQty() {
		return lastFilledQty;
	}

	private static final String str0 = "{\"offerQty\" : ";
	private static final String str1 = ", \"leavesQty\" : ";
	private static final String str2 = ", \"lastFilledQty\" : ";
	private static final String str3 = ", \"filledQty\" : ";
	private static final String str4 = "}";

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(90);
		builder.append(str0);
		builder.append(offerQty);
		builder.append(str1);
		builder.append(leavesQty);
		builder.append(str2);
		builder.append(lastFilledQty);
		builder.append(str3);
		builder.append(filledQty);
		builder.append(str4);
		return builder.toString();
	}

	public static void main(String[] args) {

		System.out.println(OrdQty.withOffer(2));

	}

}
