package io.cygnus.exchange.tests.integration;

import io.cygnus.exchange.core.common.config.PerformanceConfiguration;

public class ITExchangeCoreIntegrationStressBasic extends ITExchangeCoreIntegrationStress {

    @Override
    public PerformanceConfiguration getPerformanceConfiguration() {
        return PerformanceConfiguration.DEFAULT;
    }
}
