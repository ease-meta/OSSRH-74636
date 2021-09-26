package com.open.cloud.mybatis.test;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//@SpringBootTest(classes = {HelloSpringBootApplication.class})
//@AutoConfigureMockMvc //测试接口用
public class HelloControllerTest {
    private static final Logger log = LoggerFactory.getLogger(HelloControllerTest.class);


    public void testBefore() {
        log.info("测试前");
    }


    public void testAfter() {
        log.info("测试后");
    }

    @Autowired
    private MockMvc mockMvc;

    /**
     * 测试 /mockTest
     */
    @Test
    public void mockTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/mockTest")).
                andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        //打印出状态码，200就是成功
        log.info("状态码=" + status);

    }
}