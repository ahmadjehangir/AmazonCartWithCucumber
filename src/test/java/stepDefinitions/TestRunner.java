package stepDefinitions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features/amazonCart.feature", glue= {"stepDefinitions"},

plugin = {"pretty", "html:target/HtmlReports/repot.html",
        "json:target/JSONReports/report.json",
"json:target/JUnitReports/report.xml"}
        )

public class TestRunner {

}
