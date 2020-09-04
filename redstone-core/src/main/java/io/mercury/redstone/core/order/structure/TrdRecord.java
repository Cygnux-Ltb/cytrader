package io.mercury.redstone.core.order.structure;

/**
 * tradePrice fix use {@link MarketConstant#PriceMultiplier}
 */
public class TrdRecord implements Comparable<TrdRecord> {

	private int serial;
	private long uniqueId;
	private long epochTime;

	private long trdPrice;
	private int trdQty;

	public TrdRecord(int serial, long uniqueId, long epochTime, long trdPrice, int trdQty) {
		this.serial = serial;
		this.uniqueId = uniqueId;
		this.epochTime = epochTime;
		this.trdPrice = trdPrice;
		this.trdQty = trdQty;
	}

	public long uniqueId() {
		return uniqueId;
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
		return this.uniqueId < o.uniqueId ? -1
				: this.uniqueId > o.uniqueId ? 1 
						: this.serial < o.serial ? -1 
								: this.serial > o.serial ? 1 
										: 0;
	}

}