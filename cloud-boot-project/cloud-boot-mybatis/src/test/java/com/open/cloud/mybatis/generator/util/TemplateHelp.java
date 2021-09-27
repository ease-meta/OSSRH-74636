package com.open.cloud.mybatis.generator.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

public class TemplateHelp {

    protected static final Logger LOGGER = LoggerFactory.getLogger(TemplateHelp.class);

    /**
     * 模板路径
     *
     * @param data 数据模型
     * @param ftlName ftl模板文件名称
     * @param fileName 生成文件的文件名称 例如：d:\TAction.java
     */
    public static void creatTemplate(Map<String, Object> data, String ftlName, String fileName) throws IOException, TemplateException {
        Configuration cfg = new Configuration();
        // 设置字符集编码
        cfg.setEncoding(Locale.CHINA, "UTF-8");
        // 加载模板文件
        cfg.setClassForTemplateLoading(new TemplateHelp().getClass(), "/ftl");
        // 设置对象包装器
        cfg.setObjectWrapper(new DefaultObjectWrapper());

        // 设计异常处理器
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);

        // 获取指定模板文件
        Template template = cfg.getTemplate(ftlName);

        // 检查路径是否存在，不存在则创建
        String path = fileName.substring(0, fileName.lastIndexOf("."));
        // 目标文件
        File saveFile = new File(path);
        // 判断存放路径是否存在，存在就删除，不存在就创建
        if (saveFile.exists()) {
            boolean result = saveFile.delete();
            if (!result) {
                LOGGER.error("Delete File Failure, FileName:[{}]", fileName);
            }
        } else {
            saveFile.getParentFile().mkdirs();
        }

        // 定义输入文件，默认生成在工程根目录，设置文件的编码格式
        Writer out = new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8");

        // 最后开始生成
        template.process(data, out);

        // 关闭资源，否则删除不掉
        out.close();
    }
}