package testCases;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = "src/test/feature",glue = "stepDefinitions",monochrome = true,
        plugin ={
        "pretty", // Generates a pretty console output
        "html:testReports/cucumber.html", // Generates an HTML report
        "json:testReports/cucumber.json",
        "rerun:target/failed_scenarios.txt"}

)
public class TestNGRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true) // This enables parallel execution
    public Object[][] scenarios() {

        String cucumberTags = System.getProperty("cucumber.filter.tags");
        if (cucumberTags != null) {
            System.setProperty("cucumber.filter.tags", cucumberTags);
        }
        return super.scenarios();
    }


}
