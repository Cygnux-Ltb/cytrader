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
	/**
	 * 是否使用最大能成交的数量
	 */
	private boolean isMaxQty;

	private OrdQty(int offerQty) {
		this.offerQty = offerQty;
		this.leavesQty = offerQty;
	}

	private OrdQty(boolean isMaxQty) {
		this.isMaxQty = isMaxQty;
	}

	public static final OrdQty withOfferQty(int offerQty) {
		return new OrdQty(offerQty);
	}

	public static final OrdQty withMaxQty() {
		return new OrdQty(true);
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

	public boolean isMaxQty() {
		return isMaxQty;
	}

}
