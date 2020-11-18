package io.apollo.simulator.persistence.avro;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import io.apollo.simulator.persistence.avro.entity.SimOrder;
import io.mercury.serialization.avro.AvroBinaryDeserializer;
import io.mercury.serialization.avro.AvroBinarySerializer;

public class AvroDome0 {

	public static void main(String[] args) throws IOException {

		AvroBinarySerializer<SimOrder> serializer = new AvroBinarySerializer<>(SimOrder.class);
		AvroBinaryDeserializer<SimOrder> deserializer = new AvroBinaryDeserializer<>(SimOrder.class);

		for (int i = 0; i < 1000; i++) {
			SimOrder simOrder1 = SimOrder.newBuilder().setOrderSysId("1").setStrategyId(1).setBrokerId("1")
					.setInvestorId("1").setInstrumentId("ag1712").setOrderMsgType(1).setOrderRef(0).setUserId("1")
					.setDirection(1).setOffset(1).setVolumeFilled(1).setVolumeRemained(1).setVolumeTotalOriginal(1)
					.setTradeId("1").setTradingDay("1").setOrderStatus(1).setOrderType(1).setPrice(1).setOrdRejReason(1)
					.setInsertTime("1").setUpdateTime("1").setCancelTime("1").setFrontId(1).setSessionId(1)
					.setStatusMsg("1").setExchangeCode("1").setFee(1).setCounterType(1).setCounterSysId(1)
					.setCancelAttempts(1).setTimeStamp(1).setEpochTimeReturn(1).setFuncName("1").setLimitPrice(1)
					.setVolume(1).build();
			ByteBuffer serialization1 = serializer.serialization(simOrder1);

			SimOrder simOrder2 = SimOrder.newBuilder().setOrderSysId("2").setStrategyId(2).setBrokerId("2")
					.setInvestorId("1").setInstrumentId("ag1712").setOrderMsgType(1).setOrderRef(0).setUserId("1")
					.setDirection(1).setOffset(1).setVolumeFilled(1).setVolumeRemained(1).setVolumeTotalOriginal(1)
					.setTradeId("1").setTradingDay("1").setOrderStatus(1).setOrderType(1).setPrice(1).setOrdRejReason(1)
					.setInsertTime("1").setUpdateTime("1").setCancelTime("1").setFrontId(1).setSessionId(1)
					.setStatusMsg("1").setExchangeCode("1").setFee(1).setCounterType(1).setCounterSysId(1)
					.setCancelAttempts(1).setTimeStamp(1).setEpochTimeReturn(1).setFuncName("1").setLimitPrice(1)
					.setVolume(1).build();
			ByteBuffer serialization2 = serializer.serialization(simOrder2);

			SimOrder simOrder3 = SimOrder.newBuilder().setOrderSysId("3").setStrategyId(2).setBrokerId("3")
					.setInvestorId("1").setInstrumentId("ag1712").setOrderMsgType(1).setOrderRef(0).setUserId("1")
					.setDirection(1).setOffset(1).setVolumeFilled(1).setVolumeRemained(1).setVolumeTotalOriginal(1)
					.setTradeId("1").setTradingDay("1").setOrderStatus(1).setOrderType(1).setPrice(1).setOrdRejReason(1)
					.setInsertTime("1").setUpdateTime("1").setCancelTime("1").setFrontId(1).setSessionId(1)
					.setStatusMsg("1").setExchangeCode("1").setFee(1).setCounterType(1).setCounterSysId(1)
					.setCancelAttempts(1).setTimeStamp(1).setEpochTimeReturn(1).setFuncName("1").setLimitPrice(1)
					.setVolume(1).build();
			ByteBuffer serialization3 = serializer.serialization(simOrder3);

			byte[] newBytes = new byte[(int) (serialization1.array().length * 1.5)];
			System.out.println(serialization1.array().length);
			System.out.println(serialization2.array().length);
			System.out.println(newBytes.length);

			System.arraycopy(serialization1, 0, newBytes, 0, serialization1.array().length);

			System.out.println(newBytes.length);

			System.arraycopy(serialization2, 0, newBytes, 74, serialization2.array().length - 37);

			List<SimOrder> deSerializationToList = deserializer.deserializationMultiple(newBytes);
			System.out.println(deSerializationToList);

			byte[] newBytes2 = new byte[(int) (serialization2.array().length * 1.5)];

			System.arraycopy(serialization2, 37, newBytes2, 0, serialization2.array().length / 2);

			System.arraycopy(serialization3, 0, newBytes2, 37, serialization3.array().length);

			List<SimOrder> deSerializationToList2 = deserializer.deserializationMultiple(newBytes2);
			System.out.println(deSerializationToList2);
		}

	}

}
