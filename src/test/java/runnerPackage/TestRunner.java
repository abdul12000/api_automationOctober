package runnerPackage;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources/feature_files", plugin = {"html:target/ReportDestination", "pretty"},
        monochrome= true, glue = {"stepDefinitions"}, tags = "@regression")
public class TestRunner {
}
