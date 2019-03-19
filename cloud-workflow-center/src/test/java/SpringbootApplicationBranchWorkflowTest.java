import com.alibaba.fastjson.JSON;
import com.open.cloud.workflow.SpringbootApplicationBranchWorkflow;
import com.open.cloud.workflow.framework.drools.bean.Student;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Classname: SpringbootApplicationBranchWorkflowTest
 * @Description:
 * @Author: leijian
 * @Date: 2019/3/18
 * @Version: 1.0
 */
//底层用junit SpringJunit4ClassRunner
@RunWith(SpringRunner.class)
//启动整个Springboot工程//@SpringBootTest  //这个不能跟WebMvcTest同时存在，只能选择一个，创建API文档采用WebMvcTest
@SpringBootTest(classes={SpringbootApplicationBranchWorkflow.class})
//鼠标选中SpringBootTestDemo后执行Run As -> JUnit Test可以同时执行多个测试
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class SpringbootApplicationBranchWorkflowTest {

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).apply(documentationConfiguration(this.restDocumentation).operationPreprocessors().withRequestDefaults(prettyPrint()).withResponseDefaults(prettyPrint())).build();
    }

    @Test
    public void TestApi() throws Exception{
        this.mockMvc.perform(get("/v1/permission").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

}

