
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "Features",  // Path to feature files
    glue = "stepDefinitions"                   // Package containing step definitions
)
public class TestRunner {
}

