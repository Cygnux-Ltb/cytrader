package io.mercury.redstone.core.adaptor.dto;

import java.util.List;

public final class ReplyPositions {

	private int investorId;
	private List<ReplyPosition> positions;

	public ReplyPositions(int investorId, List<ReplyPosition> positions) {
		this.investorId = investorId;
		this.positions = positions;
	}

	public int getInvestorId() {
		return investorId;
	}

	public List<ReplyPosition> getPositions() {
		return positions;
	}

	public static class ReplyPosition {

		private String instrumentName;
		private int currentQty;

		public String getInstrumentName() {
			return instrumentName;
		}

		public ReplyPosition setInstrumentName(String instrumentName) {
			this.instrumentName = instrumentName;
			return this;
		}

		public int getCurrentQty() {
			return currentQty;
		}

		public ReplyPosition setCurrentQty(int currentQty) {
			this.currentQty = currentQty;
			return this;
		}

	}

}
