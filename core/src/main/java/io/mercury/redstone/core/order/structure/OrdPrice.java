package io.mercury.redstone.core.order.structure;

import org.eclipse.collections.api.list.MutableList;

/**
 * [offerPrice] & [avgPrice] fix use {@link MarketConstant#PriceMultiplier}
 */
public final class OrdPrice {

	/**
	 * 委托价格
	 */
	private long offerPrice;
	/**
	 * 成交均价
	 */
	private long trdAvgPrice;

	private OrdPrice(long offerPrice) {
		this.offerPrice = offerPrice;
	}

	public static OrdPrice withOffer(long offerPrice) {
		return new OrdPrice(offerPrice);
	}

	public long offerPrice() {
		return offerPrice;
	}

	public OrdPrice offerPrice(long offerPrice) {
		if (this.offerPrice == 0)
			this.offerPrice = offerPrice;
		return this;
	}

	public long trdAvgPrice() {
		return trdAvgPrice;
	}

	public OrdPrice calculateAvgPrice(TrdRecordList trdRecordList) {
		if (!trdRecordList.isEmpty()) {
			MutableList<TrdRecord> allRecord = trdRecordList.allRecord();
			// 计算总成交金额
			long totalTurnover = allRecord.sumOfLong(trade -> trade.trdPrice() * trade.trdQty());
			// 计算总成交量
			long totalQty = allRecord.sumOfInt(trade -> trade.trdQty());
			if (totalQty > 0L)
				this.trdAvgPrice = totalTurnover / totalQty;
			return this;
		}
		return this;
	}

	private static final String str0 = "{\"offerPrice\" : ";
	private static final String str1 = ", \"trdAvgPrice\" : ";
	private static final String str2 = "}";

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(75);
		builder.append(str0);
		builder.append(offerPrice);
		builder.append(str1);
		builder.append(trdAvgPrice);
		builder.append(str2);
		return builder.toString();
	}

	public static void main(String[] args) {
		System.out.println(OrdPrice.withOffer(2560));
	}

}
