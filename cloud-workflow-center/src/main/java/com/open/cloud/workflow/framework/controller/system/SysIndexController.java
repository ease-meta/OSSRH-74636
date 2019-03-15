package com.open.cloud.workflow.framework.controller.system;

import com.open.cloud.workflow.framework.config.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SysIndexController extends BaseController {


    // 系统首页
    @GetMapping({"/index", "/"})
    public String index(ModelMap mmap) {
        return "index";
    }

}