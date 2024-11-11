package org.ma.ds;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;




/*@Suite
@SpringBootTest
@ContextConfiguration(classes= TestConfig.class)
@CucumberContextConfiguration
@IncludeEngines("cucumber")
@SelectPackages("org.ma.ds.features")
@ConfigurationParameters({
        @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "pretty")
})*/

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/legal.feature",
        glue = {"src.main.java.com.moodys.ma.ds.stepDefinitions", "src.test.java.org.ma.ds.config"},
        plugin = {"pretty", "html:target/cucumber-reports"},
        dryRun = false
)
public class RunCucumberTest {
}
