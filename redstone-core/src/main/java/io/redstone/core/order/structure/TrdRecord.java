package io.redstone.core.order.structure;

/**
 * tradePrice fix use {@link MarketConstant#PriceMultiplier}
 */
public  class TrdRecord implements Comparable<TrdRecord> {

	private int serial;
	private long ordSysId;
	private long epochTime;

	private long trdPrice;
	private int trdQty;

	public TrdRecord(int serial, long ordSysId, long epochTime, long trdPrice, int trdQty) {
		super();
		this.ordSysId = ordSysId;
		this.serial = serial;
		this.epochTime = epochTime;
		this.trdPrice = trdPrice;
		this.trdQty = trdQty;
	}

	public long ordSysId() {
		return ordSysId;
	}

	public int serial() {
		return serial;
	}

	public long epochTime() {
		return epochTime;
	}

	public long trdPrice() {
		return trdPrice;
	}

	public int trdQty() {
		return trdQty;
	}

	@Override
	public int compareTo(TrdRecord o) {
		return this.serial < o.serial ? -1 : this.serial > o.serial ? 1 : 0;
	}

}