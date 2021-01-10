package io.cygnus.exchange.tests.integration;

import io.cygnus.exchange.core.common.config.PerformanceConfiguration;

public class ITExchangeCoreIntegrationRejectionBasic extends ITExchangeCoreIntegrationRejection {


    @Override
    public PerformanceConfiguration getPerformanceConfiguration() {
        return PerformanceConfiguration.baseBuilder().build();
    }
}
