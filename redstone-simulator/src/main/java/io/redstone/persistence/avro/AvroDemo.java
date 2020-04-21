package io.redstone.persistence.avro;

import java.nio.ByteBuffer;
import java.util.Arrays;

import io.mercury.serialization.avro.AvroBinaryDeserializer;
import io.mercury.serialization.avro.AvroBinarySerializer;
import io.mercury.serialization.avro.AvroTextDeserializer;
import io.mercury.serialization.avro.AvroTextSerializer;
import io.redstone.persistence.avro.entity.MarketDataSubscribe;

public class AvroDemo {

	public static void main(String[] args) {

		MarketDataSubscribe marketDataSubscribe = new MarketDataSubscribe();

		marketDataSubscribe.setInstrumentIdList(Arrays.asList("ni1709", "al1707", "rb1710"));

		AvroBinarySerializer<MarketDataSubscribe> byteSerializer = new AvroBinarySerializer<>(MarketDataSubscribe.class);

		AvroBinaryDeserializer<MarketDataSubscribe> byteDeserializer = new AvroBinaryDeserializer<>(
				MarketDataSubscribe.class);

		byte[] bytes = byteSerializer.serialization(marketDataSubscribe).array();

		MarketDataSubscribe deMarketDataSubscribe0 = byteDeserializer.deserialization(ByteBuffer.wrap(bytes));

		System.out.println(deMarketDataSubscribe0.toString());

		AvroTextSerializer<MarketDataSubscribe> textSerializer = new AvroTextSerializer<>(MarketDataSubscribe.class);

		AvroTextDeserializer<MarketDataSubscribe> textDeserializer = new AvroTextDeserializer<>(
				MarketDataSubscribe.class);

		String json = textSerializer.serialization(deMarketDataSubscribe0);

		System.out.println(json);

		MarketDataSubscribe deMarketDataSubscribe1 = textDeserializer.deserialization(json);

		System.out.println(deMarketDataSubscribe1);

	}

}
