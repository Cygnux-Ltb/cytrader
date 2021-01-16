package io.cygnus.exchange.core.utils;

import static net.openhft.chronicle.core.UnsafeMemory.UNSAFE;

import io.cygnus.exchange.core.common.MatcherTradeEvent;
import io.cygnus.exchange.core.common.cmd.CommandResultCode;
import io.cygnus.exchange.core.common.cmd.OrderCommand;

@SuppressWarnings("restriction")
public final class UnsafeOperator {

	private static final long OFFSET_RESULT_CODE;
	private static final long OFFSET_EVENT;

	static {
		try {
			OFFSET_RESULT_CODE = UNSAFE.objectFieldOffset(OrderCommand.class.getDeclaredField("resultCode"));
			OFFSET_EVENT = UNSAFE.objectFieldOffset(OrderCommand.class.getDeclaredField("matcherEvent"));
		} catch (NoSuchFieldException ex) {
			throw new IllegalStateException(ex);
		}
	}

	public static void setResultVolatile(final OrderCommand cmd, final boolean result,
			final CommandResultCode successCode, final CommandResultCode failureCode) {

		final CommandResultCode codeToSet = result ? successCode : failureCode;

		CommandResultCode currentCode;
		do {
			// read current code
			currentCode = (CommandResultCode) UNSAFE.getObjectVolatile(cmd, OFFSET_RESULT_CODE);

			// finish if desired code was already set
			// or if someone has set failure
			if (currentCode == codeToSet || currentCode == failureCode) {
				break;
			}

			// do a CAS operation
		} while (!UNSAFE.compareAndSwapObject(cmd, OFFSET_RESULT_CODE, currentCode, codeToSet));
	}

	public static void appendEventsVolatile(final OrderCommand cmd, final MatcherTradeEvent eventHead) {

		final MatcherTradeEvent tail = eventHead.findTail();

		// MatcherTradeEvent.asList(eventHead).forEach(a -> log.info("in {}", a));

		do {
			// read current head and attach to the tail of new
			tail.nextEvent = (MatcherTradeEvent) UNSAFE.getObjectVolatile(cmd, OFFSET_EVENT);

			// do a CAS operation
		} while (!UNSAFE.compareAndSwapObject(cmd, OFFSET_EVENT, tail.nextEvent, eventHead));

	}

}
