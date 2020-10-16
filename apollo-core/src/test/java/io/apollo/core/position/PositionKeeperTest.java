package io.apollo.core.position;

import org.junit.Test;

import io.gemini.definition.market.instrument.futures.impl.ChinaFutures;
import io.gemini.definition.market.instrument.futures.impl.ChinaFuturesSymbol;
import io.gemini.definition.order.enums.TrdDirection;

public class PositionKeeperTest {

	@Test
	public void test() {
		int subAccountId = 10;
		ChinaFutures rb2010 = new ChinaFutures(ChinaFuturesSymbol.RB, 2010);
		PositionKeeper.setPositionsLimit(subAccountId, rb2010, 10, 10);
		PositionKeeper.addCurrentPosition(subAccountId, rb2010, TrdDirection.Long, 10);
		PositionKeeper.addCurrentPosition(subAccountId, rb2010, TrdDirection.Short, 15);
	}

	@Test
	public void testSetPositionsLimit() {

	}

	@Test
	public void testGetPositionLimit() {

	}

	@Test
	public void testUpdatePosition() {

	}

	@Test
	public void testGetCurrentPosition() {

	}

	@Test
	public void testAddCurrentPosition() {

	}

	@Test
	public void testDump() {

	}

}
