/*
 * Copyright 2019 Maksim Zheravin
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
 */
package io.cygnus.exchange.tests.perf;

import static io.cygnus.exchange.tests.util.LatencyTestsModule.latencyTestImpl;

import org.junit.Test;

import io.cygnus.exchange.core.common.config.InitialStateConfiguration;
import io.cygnus.exchange.core.common.config.PerformanceConfiguration;
import io.cygnus.exchange.core.common.config.SerializationConfiguration;
import io.cygnus.exchange.tests.util.ExchangeTestContainer;
import io.cygnus.exchange.tests.util.TestDataParameters;

public final class PerfLatencyJournaling {

    /*
     * -------------- Disk Journaling tests -----------------
     * Recommended tuned profile: latency-performance
     *
     */

    @Test
    public void testLatencyMarginJournaling() {
        latencyTestImpl(
                PerformanceConfiguration.latencyPerformanceBuilder()
                        .ringBufferSize(32 * 1024)
                        .matchingEnginesNum(1)
                        .riskEnginesNum(1)
                        .msgsInGroupLimit(256)
                        .build(),
                TestDataParameters.singlePairMarginBuilder().build(),
                InitialStateConfiguration.cleanStartJournaling(ExchangeTestContainer.timeBasedExchangeId()),
                SerializationConfiguration.DISK_JOURNALING,
                6);
    }

    @Test
    public void testLatencyExchangeJournaling() {
        latencyTestImpl(
                PerformanceConfiguration.latencyPerformanceBuilder()
                        .ringBufferSize(32 * 1024)
                        .matchingEnginesNum(1)
                        .riskEnginesNum(1)
                        .msgsInGroupLimit(256)
                        .build(),
                TestDataParameters.singlePairExchangeBuilder().build(),
                InitialStateConfiguration.cleanStartJournaling(ExchangeTestContainer.timeBasedExchangeId()),
                SerializationConfiguration.DISK_JOURNALING,
                6);
    }

    @Test
    public void testLatencyMultiSymbolMediumJournaling() {
        latencyTestImpl(
                PerformanceConfiguration.latencyPerformanceBuilder()
                        .ringBufferSize(32 * 1024)
                        .matchingEnginesNum(4)
                        .riskEnginesNum(2)
                        .msgsInGroupLimit(256)
                        .build(),
                TestDataParameters.mediumBuilder().build(),
                InitialStateConfiguration.cleanStartJournaling(ExchangeTestContainer.timeBasedExchangeId()),
                SerializationConfiguration.DISK_JOURNALING,
                3);
    }

    @Test
    public void testLatencyMultiSymbolLargeJournaling() {
        latencyTestImpl(
                PerformanceConfiguration.latencyPerformanceBuilder()
                        .ringBufferSize(32 * 1024)
                        .matchingEnginesNum(4)
                        .riskEnginesNum(2)
                        .msgsInGroupLimit(256)
                        .build(),
                TestDataParameters.largeBuilder().build(),
                InitialStateConfiguration.cleanStartJournaling(ExchangeTestContainer.timeBasedExchangeId()),
                SerializationConfiguration.DISK_JOURNALING,
                3);
    }

    @Test
    public void testLatencyMultiSymbolHugeJournaling() {
        latencyTestImpl(
                PerformanceConfiguration.latencyPerformanceBuilder()
                        .ringBufferSize(64 * 1024)
                        .matchingEnginesNum(4)
                        .riskEnginesNum(2)
                        .msgsInGroupLimit(256)
                        .build(),
                TestDataParameters.hugeBuilder().build(),
                InitialStateConfiguration.cleanStartJournaling(ExchangeTestContainer.timeBasedExchangeId()),
                SerializationConfiguration.DISK_JOURNALING,
                2);
    }
}