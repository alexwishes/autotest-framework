package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        plugin = {"pretty",
                "de.monochromata.cucumber.report.PrettyReports:target/cucumber",
		"json:target/cucumber/cucumber.json"
        },
        glue = {"classpath:stepdefs"},
        features = {"classpath:features"},
        tags = "@chn_api_demo"
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
