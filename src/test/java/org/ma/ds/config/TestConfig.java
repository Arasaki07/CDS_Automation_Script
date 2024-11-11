package org.ma.ds.config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.ma.ds.RunCucumberTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

//@Configuration
//@ComponentScan(basePackages = "com.example")  // Adjust package to your application
@CucumberContextConfiguration
@SpringBootTest (classes = RunCucumberTest.class)
@AutoConfigureMockMvc
public class TestConfig {
    // This is where you can configure beans for testing
}