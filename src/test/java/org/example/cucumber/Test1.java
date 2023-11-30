package org.example.cucumber;


import io.cucumber.junit.CucumberOptions;
import org.testng.annotations.Test;

@Test
@CucumberOptions(glue = {"com.cucumber"}
        , features = {"src/test/resources/features/Test1.feature"}
        , plugin = "json:target/cucumber-result/json/Test1.json"
)
public class Test1 {
}
