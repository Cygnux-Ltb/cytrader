package exchange.core2.tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import exchange.core2.core.common.config.PerformanceConfiguration;
import exchange.core2.tests.steps.OrderStepdefs;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber"}, strict = true)
public class RunCukesDirectThroughputTests {

    @BeforeClass
    public static void beforeClass() {
        OrderStepdefs.testPerformanceConfiguration = PerformanceConfiguration.throughputPerformanceBuilder().build();
    }

    @AfterClass
    public static void afterClass() {
        OrderStepdefs.testPerformanceConfiguration = null;
    }

}
