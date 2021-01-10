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

import static io.cygnus.exchange.tests.util.LatencyTestsModule.hiccupTestImpl;

import org.junit.Test;

import io.cygnus.exchange.core.common.config.InitialStateConfiguration;
import io.cygnus.exchange.core.common.config.PerformanceConfiguration;
import io.cygnus.exchange.tests.util.TestDataParameters;


public final class PerfHiccups {


    @Test
    public void testHiccupMargin() {
        hiccupTestImpl(
                PerformanceConfiguration.latencyPerformanceBuilder()
                        .ringBufferSize(2 * 1024)
                        .matchingEnginesNum(1)
                        .riskEnginesNum(1)
                        .msgsInGroupLimit(256)
                        .build(),
                TestDataParameters.singlePairMarginBuilder().build(),
                InitialStateConfiguration.CLEAN_TEST,
                3);
    }


}