package io.redstone.core.order.structure;

public final class OrdQty {

	/**
	 * 委托数量
	 */
	private long offerQty;
	/**
	 * 剩余数量
	 */
	private long leavesQty;
	/**
	 * 已成交数量
	 */
	private long filledQty;
	/**
	 * 最后一次成交数量
	 */
	private long lastFilledQty;
	/**
	 * 是否使用最大能成交的数量
	 */
	private boolean isMaxQty;

	private OrdQty(long offerQty) {
		this.offerQty = offerQty;
		this.leavesQty = offerQty;
	}

	private OrdQty(boolean isMaxQty) {
		this.isMaxQty = isMaxQty;
	}

	public static OrdQty withOffer(long offerQty) {
		return new OrdQty(offerQty);
	}

	public static OrdQty withMax(boolean isMaxQty) {
		return new OrdQty(isMaxQty);
	}

	public long offerQty() {
		return offerQty;
	}

	public OrdQty offerQty(long offerQty) {
		if (this.offerQty == 0) {
			this.offerQty = offerQty;
			this.leavesQty = offerQty;
		}
		return this;
	}

	public long leavesQty() {
		return leavesQty;
	}

	public OrdQty leavesQty(long leavesQty) {
		this.leavesQty = leavesQty;
		return this;
	}

	public long filledQty() {
		return filledQty;
	}

	public OrdQty filledQty(long filledQty) {
		this.lastFilledQty = this.filledQty;
		this.filledQty = filledQty;
		return this;
	}

	public long lastFilledQty() {
		return lastFilledQty;
	}

	public boolean isMaxQty() {
		return isMaxQty;
	}

}
