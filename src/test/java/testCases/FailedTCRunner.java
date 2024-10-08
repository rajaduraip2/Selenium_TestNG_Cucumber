package testCases;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = "@target/failed_scenarios.txt",glue = "stepDefinitions",monochrome = true,
        plugin ={
                "pretty", // Generates a pretty console output
                "html:testReports/cucumber.html", // Generates an HTML report
                "json:testReports/cucumber.json"})
public class FailedTCRunner extends AbstractTestNGCucumberTests {
    @DataProvider(parallel = true) // This enables parallel execution
    public Object[][] scenarios() {
        return super.scenarios();

    }
}
