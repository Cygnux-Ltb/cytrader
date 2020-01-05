package io.redstone.persistence.avro;

import java.util.Arrays;

import io.mercury.codec.avro.AvroBytesDeserializer;
import io.mercury.codec.avro.AvroBytesSerializer;
import io.mercury.codec.avro.AvroTextDeserializer;
import io.mercury.codec.avro.AvroTextSerializer;
import io.redstone.persistence.avro.entity.MarketDataSubscribe;

public class AvroDemo {

	public static void main(String[] args) {

		MarketDataSubscribe marketDataSubscribe = new MarketDataSubscribe();

		marketDataSubscribe.setInstrumentIdList(Arrays.asList("ni1709", "al1707", "rb1710"));

		AvroBytesSerializer byteSerializer = new AvroBytesSerializer();

		AvroBytesDeserializer<MarketDataSubscribe> byteDeserializer = new AvroBytesDeserializer<>(
				MarketDataSubscribe.class);

		byte[] bytes = byteSerializer.serialization(marketDataSubscribe);

		MarketDataSubscribe deMarketDataSubscribe0 = byteDeserializer.deSerializationSingle(bytes);

		System.out.println(deMarketDataSubscribe0.toString());

		AvroTextSerializer textSerializer = new AvroTextSerializer();

		AvroTextDeserializer<MarketDataSubscribe> textDeserializer = new AvroTextDeserializer<>(
				MarketDataSubscribe.class);

		String json = textSerializer.serialization(deMarketDataSubscribe0);

		System.out.println(json);

		MarketDataSubscribe deMarketDataSubscribe1 = textDeserializer.deSerializationSingle(json);

		System.out.println(deMarketDataSubscribe1);

	}

}
