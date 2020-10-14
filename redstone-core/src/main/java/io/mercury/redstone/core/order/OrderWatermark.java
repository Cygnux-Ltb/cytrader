package io.mercury.redstone.core.order;

import static java.lang.System.out;

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
		out.println(now.toEpochSecond() * 1000);
		out.println(now.getNano());
		out.println(currentTimeMillis);

		out.println(currentTimeMillis);
		out.println(currentTimeMillis << Byte.SIZE);
		out.println(Long.MAX_VALUE);

		out.println(Short.BYTES);
		out.println(BitOperator.longBinaryFormat(currentTimeMillis));
		out.println(BitOperator.longBinaryFormat(currentTimeMillis << Byte.SIZE));
		out.println(BitOperator.longBinaryFormat(currentTimeMillis << Short.SIZE));
		out.println(BitOperator.longBinaryFormat(currentTimeMillis << (Byte.SIZE * 2)));

		out.println(BitOperator.longBinaryFormat(toEpochSecond));

		out.println(ZonedDateTime.ofInstant(Instant.ofEpochMilli(140737488355327L), ZoneOffset.UTC));

		out.println(BitOperator.longBinaryFormat(Long.MAX_VALUE >> 16));
		out.println((Long.MAX_VALUE >> 16));
		out.println((1 << 8));
		out.println(BitOperator.longBinaryFormat(Long.MAX_VALUE));

	}

}
