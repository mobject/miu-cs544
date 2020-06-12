package edu.miu.cs.cs544.examples;

import edu.miu.cs.cs544.examples.controller.CountryController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTest {

    @Autowired
    private CountryController countryController;

    @Test
    public void contextLoads(){
        Assertions.assertThat(this.countryController).isNotNull();
    }
}