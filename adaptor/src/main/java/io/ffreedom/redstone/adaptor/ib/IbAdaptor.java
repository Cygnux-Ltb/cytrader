package io.ffreedom.redstone.adaptor.ib;

import java.util.Arrays;

import io.ffreedom.persistence.avro.entity.MarketDataSubscribe;

public class IbAdaptor {

	
	
	public static void main(String[] args) {
		
		MarketDataSubscribe subscribe = MarketDataSubscribe.newBuilder().setUniqueId(777)
				.setInstrumentIdList(Arrays.asList("rb1801"))
				.setStartTradingDay("20170809").setEndTradingDay("20170809").build();
		
		System.out.println(subscribe);
		
	}
}
