package io.mercury.redstone.core.adaptor.dto;

import java.util.List;

import org.eclipse.collections.api.list.ImmutableList;

import io.mercury.common.collections.ImmutableLists;

public final class PositionsReport {

	private final int investorId;
	private final ImmutableList<Position> positions;

	public PositionsReport(int investorId, Position position) {
		this.investorId = investorId;
		this.positions = ImmutableLists.newList(position);
	}

	public PositionsReport(int investorId, List<Position> positions) {
		this.investorId = investorId;
		this.positions = ImmutableLists.newList(positions);
	}

	public int getInvestorId() {
		return investorId;
	}

	public ImmutableList<Position> getPositions() {
		return positions;
	}

	public static class Position {

		private final String instrumentCode;
		private final int currentQty;

		public Position(String instrumentCode, int currentQty) {
			this.instrumentCode = instrumentCode;
			this.currentQty = currentQty;
		}

		public String getInstrumentCode() {
			return instrumentCode;
		}

		public int getCurrentQty() {
			return currentQty;
		}

	}

}
