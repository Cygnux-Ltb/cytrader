package io.cygnus.exchange.tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cygnus.exchange.core.common.config.PerformanceConfiguration;
import io.cygnus.exchange.tests.steps.OrderStepdefs;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber"}, strict = true)
public class RunCukeNaiveTests {

    @BeforeClass
    public static void beforeClass() {
        OrderStepdefs.testPerformanceConfiguration = PerformanceConfiguration.baseBuilder().build();
    }

    @AfterClass
    public static void afterClass() {
        OrderStepdefs.testPerformanceConfiguration = null;
    }

}
