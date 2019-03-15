package com.open.cloud.workflow.framework.controller.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Classname: CommonController
 * @Description:
 * @Author: leijian
 * @Date: 2019/3/15
 * @Version: 1.0
 */
@Controller
@Slf4j
public class CommonController {
    /**
     * 文件上传路径
     */
    public static final String UPLOAD_PATH = "/profile/upload/";

    public String setFileDownloadHeader(HttpServletRequest request, String fileName) throws UnsupportedEncodingException
    {
        final String agent = request.getHeader("USER-AGENT");
        String filename = fileName;
        if (agent.contains("MSIE"))
        {
            // IE浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        }
        else if (agent.contains("Firefox"))
        {
            // 火狐浏览器
            filename = new String(fileName.getBytes(), "ISO8859-1");
        }
        else if (agent.contains("Chrome"))
        {
            // google浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        else
        {
            // 其它浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }
}