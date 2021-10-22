package co.com.sofka.runner.soap.information.countryflag;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/countryflag/countryflag.feature"},
        glue = {"co.com.sofka.stepdefnitions.soap.information.countryflag"}
)
public class CountryFlagWithCucumberTest {
}
