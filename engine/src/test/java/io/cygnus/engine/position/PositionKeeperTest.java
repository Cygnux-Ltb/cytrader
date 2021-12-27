package io.cygnus.engine.position;

import org.junit.Test;

import io.horizon.market.instrument.ChinaFutures.ChinaFuturesInstrument;
import io.horizon.market.instrument.ChinaFutures.ChinaFuturesSymbol;
import io.horizon.market.instrument.Instrument;
import io.horizon.trader.order.enums.TrdDirection;

public class PositionKeeperTest {

	@Test
	public void test() {
		int subAccountId = 10;
		Instrument rb2010 = ChinaFuturesInstrument.newInstance(ChinaFuturesSymbol.RB, 2010);
		PositionKeeper.setSubAccountPositionsLimit(subAccountId, rb2010, 10, 10);
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
