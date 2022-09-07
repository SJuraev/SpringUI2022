package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)

@CucumberOptions(
        features = {"classpath:features"},

        glue = {"step_defs"},

        //tags = {"@meetup"},
        //tags = {"@random"},
        tags = {"@search"},

        plugin = { "html:target/default-cucumber-reports",
                "json:target/cucumberSpring2022Gold.json",//will generate json report, but needs to be run from command-line
                  "pretty"},

        dryRun = false




)

public class MeetUpRunner {

}
