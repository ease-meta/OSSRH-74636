package org.elasticsearch.controller;

/**
 * @ClassName: TemplatesController
 * @Description: java类作用描述
 * @Author: liyuq
 * @CreateDate: 2019/9/4 9:10
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class TemplatesController {

    @RequestMapping("/")
    String test(HttpServletRequest request) {
        //逻辑处理
        request.setAttribute("key", "hello world");
       return "redirect:/index.html";
    }
}
