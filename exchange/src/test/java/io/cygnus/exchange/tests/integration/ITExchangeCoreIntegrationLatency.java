package io.cygnus.exchange.tests.integration;

import io.cygnus.exchange.core.common.config.PerformanceConfiguration;

public final class ITExchangeCoreIntegrationLatency extends ITExchangeCoreIntegration {

    @Override
    public PerformanceConfiguration getPerformanceConfiguration() {
        return PerformanceConfiguration.latencyPerformanceBuilder().build();
    }
}
