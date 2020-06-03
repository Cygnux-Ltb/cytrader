package io.mercury.redstone.core.adaptor.dto;

import java.util.List;

public final class PositionsReport {

	private int investorId;
	private List<Position> positions;

	public PositionsReport(int investorId, List<Position> positions) {
		this.investorId = investorId;
		this.positions = positions;
	}

	public int getInvestorId() {
		return investorId;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public static class Position {

		private String instrumentCode;
		private int currentQty;

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
