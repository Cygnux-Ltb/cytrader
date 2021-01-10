/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
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
