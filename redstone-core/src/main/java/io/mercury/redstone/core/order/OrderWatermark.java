package io.mercury.redstone.core.order;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import io.mercury.common.util.BitOperator;

public final class OrderWatermark {

	public static void main(String[] args) {

		ZonedDateTime now = ZonedDateTime.of(LocalDate.of(6100, 12, 31), LocalTime.NOON, ZoneOffset.UTC);
		long currentTimeMillis = System.currentTimeMillis();
		long toEpochSecond = now.toEpochSecond() * 1000;
		System.out.println(now.toEpochSecond() * 1000);
		System.out.println(now.getNano());
		System.out.println(currentTimeMillis);

		System.out.println(currentTimeMillis);
		System.out.println(currentTimeMillis << Byte.SIZE);
		System.out.println(Long.MAX_VALUE);

		System.out.println(BitOperator.longBinaryFormat(currentTimeMillis));
		System.out.println(BitOperator.longBinaryFormat(currentTimeMillis << Byte.SIZE));
		System.out.println(BitOperator.longBinaryFormat(currentTimeMillis << (Byte.SIZE * 2)));
		
		System.out.println(BitOperator.longBinaryFormat(toEpochSecond));
		
		System.out.println(ZonedDateTime.ofInstant(Instant.ofEpochMilli(140737488355327L), ZoneOffset.UTC));
		
		System.out.println(BitOperator.longBinaryFormat(Long.MAX_VALUE >> 16));
		System.out.println((Long.MAX_VALUE >> 16));
		System.out.println((1 << 8));
		System.out.println(BitOperator.longBinaryFormat(Long.MAX_VALUE));

	}

}
