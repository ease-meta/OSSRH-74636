package com.open.cloud.test.groovy;

import groovy.lang.GroovyObject;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.springframework.util.StringUtils;

import java.net.URL;

/**
 * @author leijian
 * @version 1.0
 * @date 2019/6/15 14:50
 **/
@Slf4j
public class GroovyCommonEngine {
    /**
     *
     */
    private static final String RULE_DIR = "META-INF/rule/comet.groovy";

    /**
     * Groovy
     */
    static GroovyScriptEngine groovyScriptEngine;
    /**
     * Groovy
     */
    private String scriptName;
    /**
     *
     */
    private String methodName;
    /**
     *
     */
    private Object params;

    static {
        /*ClassLoader classLoader = ClassHelper.getClassLoader(GroovyCommonEngine.class);
        Enumeration<URL> urls;
        if (classLoader != null) {
            urls = classLoader.getResources("");
        } else {
            urls = ClassLoader.getSystemResources("");
        }
        List<String> list = new LinkedList<String>();
        String path = null;
        java.net.URL resourceURL = null;
        if (urls != null) {
            while (urls.hasMoreElements()) {
                resourceURL = urls.nextElement();
                log.info("url  :" + resourceURL.toString());
                path = resourceURL.getPath();
                log.info("path  :" + path);
                list.add(path);
            }
        }
        //String[] root = new String[]{path};
        URL[] root = new URL[]{resourceURL};*/
        URL url = GroovyCommonEngine.class.getClassLoader().getResource("");
        log.debug("url  :" + url.toString());
        String path = url.getPath();
        log.debug("path  :" + path);
        //String[] root = new String[]{path};
        URL[] root = new URL[]{url};
        groovyScriptEngine = new GroovyScriptEngine(root);
        CompilerConfiguration compilerConfiguration = new CompilerConfiguration();
        compilerConfiguration.setTargetBytecode("1.8");
        groovyScriptEngine.setConfig(compilerConfiguration);
    }


    public static Object invokeMethod(String scriptName, String methodName, Object... params) {
        log.info("RULE_NAME[{}],funcName[{}],checkDataMap[{}]", scriptName, methodName, params);
        Object ret = null;
        Class scriptClass = null;
        GroovyObject scriptInstance = null;
        try {
            scriptClass = groovyScriptEngine.loadScriptByName(scriptName);
            scriptInstance = (GroovyObject) scriptClass.newInstance();
        } catch (IllegalAccessException | ResourceException | InstantiationException | ScriptException e1) {
            log.error("errorStackTrace:", e1);
        }

        try {
            if (!StringUtils.isEmpty(scriptInstance)) {
                ret = scriptInstance.invokeMethod(methodName, params);
            }
        } catch (SecurityException e) {
            log.error("errorStackTrace:", e);
        }

        return ret;
    }
}
