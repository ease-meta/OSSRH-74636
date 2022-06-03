package io.github.meta.ease.web.autoconfigure;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {WebMvcCoreAutoConfiguration.class,}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Disabled
class WebMvcCoreAutoConfigurationTest {

    @Autowired
    private WebMvcCoreAutoConfiguration webMvcCoreAutoConfigurationUnderTest;


    @Test
    void testCometConsumerMessageConvert() {

    }
}
