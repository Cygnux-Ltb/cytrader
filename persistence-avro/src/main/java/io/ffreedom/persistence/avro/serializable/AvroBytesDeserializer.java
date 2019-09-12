package io.ffreedom.persistence.avro.serializable;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificRecord;

import io.ffreedom.common.serialization.BytesDeserializer;
import io.ffreedom.persistence.avro.entity.Order;

@NotThreadSafe
public final class AvroBytesDeserializer<T extends SpecificRecord> extends BaseAvroDeserializer<T>
		implements BytesDeserializer<T> {

	private BinaryDecoder decoder;

	private Class<T> tClass;

	public AvroBytesDeserializer(Class<T> tClass) {
		this.tClass = tClass;
	}

	@Override
	public T deSerializationSingle(byte[] bytes) {
		try {
			if (datumReader == null) {
				datumReader = initDatumReader(tClass);
			}
			decoder = initDecoder(bytes);
			return datumReader.read(null, decoder);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException("AvroBytesDeserializer.deSerialization(bytes, tClass) -> " + e.getMessage());
		}
	}

	private BinaryDecoder initDecoder(byte[] bytes) {
		return DecoderFactory.get().binaryDecoder(bytes, decoder);
	}

	private int offset;
	private byte remainingBytes[];

	@Override
	public List<T> deSerializationMultiple(byte[] bytes) {
		byte[] allBytes;
		if (remainingBytes != null) {
			logger.warn("Incomplete bytes encountered from previous packet, now trying to concat");
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(remainingBytes.length + bytes.length);
			try {
				outputStream.write(remainingBytes);
				outputStream.write(bytes);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				throw new RuntimeException("Error concat incomplete bytes -> " + e.getMessage());
			}
			allBytes = outputStream.toByteArray();
		} else {
			allBytes = bytes;
		}
		List<T> resultList = new ArrayList<T>();
		try {
			if (datumReader == null) {
				datumReader = initDatumReader(tClass);
			}
			int countSize = allBytes.length;

			remainingBytes = null; // Comment for testing
			offset = 0;
			decoder = initDecoder(allBytes);// Comment for testing

			InputStream inputStream = decoder.inputStream();

			while (inputStream.available() != 0) {
				T t = datumReader.read(null, decoder);
				resultList.add(t);
				offset = countSize - inputStream.available();
			}
		} catch (EOFException e) {
			remainingBytes = Arrays.copyOfRange(allBytes, offset, allBytes.length);
			logger.debug("remainingBytes.length -> " + remainingBytes.length);
			logger.debug("recvBytes.length -> " + allBytes.length);
			logger.warn("Incomplete bytes packet encountered: " + e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException("AvroBytesDeserializer.deSerialization(bytes, tClass) -> " + e.getMessage());
		}
		return resultList;
	}

	public static void main(String[] args) throws IOException {

		AvroBytesSerializer serializer = new AvroBytesSerializer();
		AvroBytesDeserializer<Order> deserializer = new AvroBytesDeserializer<>(Order.class);

		for (int i = 0; i < 1000; i++) {
			Order simOrder1 = Order.newBuilder().setOrderSysId("1").setStrategyId(1).setBrokerId("1").setInvestorId("1")
					.setInstrumentId("ag1712").setOrderMsgType(1).setOrderRef(0).setUserId("1").setDirection(1)
					.setOffset(1).setVolumeFilled(1).setVolumeRemained(1).setVolumeTotalOriginal(1).setTradeId("1")
					.setTradingDay("1").setOrderStatus(1).setOrderType(1).setPrice(1).setOrdRejReason(1)
					.setInsertTime("1").setUpdateTime("1").setCancelTime("1").setFrontId(1).setSessionId(1)
					.setStatusMsg("1").setExchangeCode("1").setFee(1).setCounterType(1).setCounterSysId(1)
					.setCancelAttempts(1).setTimeStamp(1).setEpochTimeReturn(1).setFuncName("1").setLimitPrice(1)
					.setVolume(1).build();
			byte[] serialization1 = serializer.serialization(simOrder1);

			Order simOrder2 = Order.newBuilder().setOrderSysId("2").setStrategyId(2).setBrokerId("2").setInvestorId("1")
					.setInstrumentId("ag1712").setOrderMsgType(1).setOrderRef(0).setUserId("1").setDirection(1)
					.setOffset(1).setVolumeFilled(1).setVolumeRemained(1).setVolumeTotalOriginal(1).setTradeId("1")
					.setTradingDay("1").setOrderStatus(1).setOrderType(1).setPrice(1).setOrdRejReason(1)
					.setInsertTime("1").setUpdateTime("1").setCancelTime("1").setFrontId(1).setSessionId(1)
					.setStatusMsg("1").setExchangeCode("1").setFee(1).setCounterType(1).setCounterSysId(1)
					.setCancelAttempts(1).setTimeStamp(1).setEpochTimeReturn(1).setFuncName("1").setLimitPrice(1)
					.setVolume(1).build();
			byte[] serialization2 = serializer.serialization(simOrder2);

			Order simOrder3 = Order.newBuilder().setOrderSysId("3").setStrategyId(2).setBrokerId("3").setInvestorId("1")
					.setInstrumentId("ag1712").setOrderMsgType(1).setOrderRef(0).setUserId("1").setDirection(1)
					.setOffset(1).setVolumeFilled(1).setVolumeRemained(1).setVolumeTotalOriginal(1).setTradeId("1")
					.setTradingDay("1").setOrderStatus(1).setOrderType(1).setPrice(1).setOrdRejReason(1)
					.setInsertTime("1").setUpdateTime("1").setCancelTime("1").setFrontId(1).setSessionId(1)
					.setStatusMsg("1").setExchangeCode("1").setFee(1).setCounterType(1).setCounterSysId(1)
					.setCancelAttempts(1).setTimeStamp(1).setEpochTimeReturn(1).setFuncName("1").setLimitPrice(1)
					.setVolume(1).build();
			byte[] serialization3 = serializer.serialization(simOrder3);

			byte[] newBytes = new byte[(int) (serialization1.length * 1.5)];
			System.out.println(serialization1.length);
			System.out.println(serialization2.length);
			System.out.println(newBytes.length);

			System.arraycopy(serialization1, 0, newBytes, 0, serialization1.length);

			System.out.println(newBytes.length);

			System.arraycopy(serialization2, 0, newBytes, 74, serialization2.length - 37);

			List<Order> deSerializationToList = deserializer.deSerializationMultiple(newBytes);
			System.out.println(deSerializationToList);

			byte[] newBytes2 = new byte[(int) (serialization2.length * 1.5)];

			System.arraycopy(serialization2, 37, newBytes2, 0, serialization2.length / 2);

			System.arraycopy(serialization3, 0, newBytes2, 37, serialization3.length);

			List<Order> deSerializationToList2 = deserializer.deSerializationMultiple(newBytes2);
			System.out.println(deSerializationToList2);
		}

	}

}
