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

/**
 * Exchange configuration
 */
@AllArgsConstructor

@Builder
public final class ExchangeConfiguration {

	/*
	 * Orders processing configuration
	 */
	@Getter
	private final OrdersProcessingConfiguration ordersProcessingCfg;

	/*
	 * Performance configuration
	 */
	@Getter
	private final PerformanceConfiguration performanceCfg;

	/*
	 * Exchange initialization configuration
	 */
	@Getter
	private final InitialStateConfiguration initStateCfg;

	/*
	 * Exchange configuration
	 */
	@Getter
	private final ReportsQueriesConfiguration reportsQueriesCfg;

	/*
	 * Logging configuration
	 */
	@Getter
	private final LoggingConfiguration loggingCfg;

	/*
	 * Serialization (snapshots and journaling) configuration
	 */
	@Getter
	private final SerializationConfiguration serializationCfg;

	@Override
	public String toString() {
		return "ExchangeConfiguration{" +

				"\n  ordersProcessingCfg=" + ordersProcessingCfg +

				"\n  performanceCfg=" + performanceCfg +

				"\n  initStateCfg=" + initStateCfg +

				"\n  reportsQueriesCfg=" + reportsQueriesCfg +

				"\n  loggingCfg=" + loggingCfg +

				"\n  serializationCfg=" + serializationCfg +

				'}';
	}

	/**
	 * Sample configuration builder having predefined default settings.
	 *
	 * @return configuration builder
	 */
	public static ExchangeConfiguration.ExchangeConfigurationBuilder defaultBuilder() {
		return ExchangeConfiguration

				.builder()

				.ordersProcessingCfg(OrdersProcessingConfiguration.DEFAULT)

				.initStateCfg(InitialStateConfiguration.DEFAULT)

				.performanceCfg(PerformanceConfiguration.DEFAULT)

				.reportsQueriesCfg(ReportsQueriesConfiguration.DEFAULT)

				.loggingCfg(LoggingConfiguration.DEFAULT)

				.serializationCfg(SerializationConfiguration.DEFAULT);
	}
}
