package io.cygnus.exchange.core.common.api;

import io.cygnus.exchange.core.common.enums.BalanceAdjustmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public final class ApiAdjustUserBalance extends ApiCommand {

	public final long uid;

	public final int currency;
	public final long amount;
	public final long transactionId;

	public final BalanceAdjustmentType adjustmentType = BalanceAdjustmentType.ADJUSTMENT; // TODO support suspend

	@Override
	public String toString() {
		String amountFmt = String.format("%s%d c%d", amount >= 0 ? "+" : "-", Math.abs(amount), currency);
		return "[ADJUST_BALANCE " + uid + " id:" + transactionId + " " + amountFmt + " " + adjustmentType + "]";

	}
}
