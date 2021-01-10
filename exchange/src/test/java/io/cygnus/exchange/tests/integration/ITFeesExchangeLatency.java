package io.cygnus.exchange.tests.integration;

import io.cygnus.exchange.core.common.config.PerformanceConfiguration;

public class ITFeesExchangeLatency extends ITFeesExchange {
    @Override
    public PerformanceConfiguration getPerformanceConfiguration() {
        return PerformanceConfiguration.latencyPerformanceBuilder().build();
    }
}
