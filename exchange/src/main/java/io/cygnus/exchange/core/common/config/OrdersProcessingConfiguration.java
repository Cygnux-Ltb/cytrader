package io.cygnus.exchange.core.common.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Order processing configuration
 */
@AllArgsConstructor
@Builder
@ToString
public final class OrdersProcessingConfiguration {

	public static final OrdersProcessingConfiguration DEFAULT = OrdersProcessingConfiguration.builder()
			.riskProcessingMode(RiskProcessingMode.FULL_PER_CURRENCY)
			.marginTradingMode(MarginTradingMode.MARGIN_TRADING_ENABLED).build();

	@Getter
	private final RiskProcessingMode riskProcessingMode;

	@Getter
	private final MarginTradingMode marginTradingMode;

	public enum RiskProcessingMode {
		// risk processing is on, every currency/asset account is checked independently
		FULL_PER_CURRENCY,

		// risk processing is off, any orders accepted and placed
		NO_RISK_PROCESSING
	}

	public enum MarginTradingMode {

		MARGIN_TRADING_DISABLED,

		MARGIN_TRADING_ENABLED
	}
}
