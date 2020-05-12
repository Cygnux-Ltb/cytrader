package io.redstone.core.order.structure;

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
	 * 最后一次成交数量
	 */
	private int lastFilledQty;

	private OrdQty(int offerQty) {
		this.offerQty = offerQty;
		this.leavesQty = offerQty;
	}

	public static final OrdQty withOfferQty(int offerQty) {
		return new OrdQty(offerQty);
	}

	public int offerQty() {
		return offerQty;
	}

	public OrdQty offerQty(int offerQty) {
		if (this.offerQty == 0) {
			this.offerQty = offerQty;
			this.leavesQty = offerQty;
		}
		return this;
	}

	public int leavesQty() {
		return leavesQty;
	}

	public OrdQty leavesQty(int leavesQty) {
		this.leavesQty = leavesQty;
		return this;
	}

	public int filledQty() {
		return filledQty;
	}

	public OrdQty filledQty(int filledQty) {
		this.lastFilledQty = this.filledQty;
		this.filledQty = filledQty;
		return this;
	}

	public int lastFilledQty() {
		return lastFilledQty;
	}

	private static final String str0 = "{\"offerQty\" : ";
	private static final String str1 = ", \"leavesQty\" : ";
	private static final String str2 = ", \"filledQty\" : ";
	private static final String str3 = ", \"lastFilledQty\" : ";
	private static final String str4 = "}";

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(90);
		builder.append(str0);
		builder.append(offerQty);
		builder.append(str1);
		builder.append(leavesQty);
		builder.append(str2);
		builder.append(filledQty);
		builder.append(str3);
		builder.append(lastFilledQty);
		builder.append(str4);
		return builder.toString();
	}

	public static void main(String[] args) {

		System.out.println(OrdQty.withOfferQty(2));

	}

}
