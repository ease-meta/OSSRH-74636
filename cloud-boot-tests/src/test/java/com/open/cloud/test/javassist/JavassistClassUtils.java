package com.open.cloud.test.javassist;

import javassist.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;


/**
 * 使用Javassist操作类的工具类
 */
@SuppressWarnings("all")
public final class JavassistClassUtils {

    private static final ClassPool POOL = new ClassPool();
    private static final Logger LOGGER = LoggerFactory.getLogger(JavassistClassUtils.class);

    static {
        POOL.appendClassPath(new ClassClassPath(JavassistClassUtils.class));
    }


    private static CtClass getCtClass(ClassPool pool, String name) throws Exception {
        return pool.get(name);
    }

    private static Object newInstance(CtClass ctClass, String className) throws Exception {
        Class clazz;

        try {
            clazz = ClassUtils.forName(className, JavassistClassUtils.class.getClassLoader());
        } catch (ClassNotFoundException e) {
            clazz = ctClass.toClass();
        }
        Assert.notNull(clazz, "No class find by name[" + className + "].");
        return clazz.newInstance();
    }

    public static Object newInstance(ClassPool pool, List<String> classInfos) throws Exception {
        int size = classInfos.size();
        String className = classInfos.get(0);
        CtClass ctClass = pool.getOrNull(className);

        if (ctClass != null) {
            return newInstance(ctClass, className);
        }
        ctClass = makeClassAndLog(pool, className);
        ctClass.addConstructor(CtNewConstructor.defaultConstructor(ctClass));
        addInterfaceAndLog(pool, ctClass, classInfos.get(1));

        for (int i = 2; i < size; i++) {
            addMethodAndLog(ctClass, classInfos.get(i));
        }
        return newInstance(ctClass, className);
    }

    public static Object newInstance(List<String> classInfos) throws Exception {
        return newInstance(POOL, classInfos);
    }

    private static CtClass makeClassAndLog(ClassPool pool, String className) {
        infoLog("class name:" + className);
        return pool.makeClass(className);
    }

    private static void addInterfaceAndLog(ClassPool pool, CtClass ctClass, String name) throws Exception {
        if (name == null) {
            return;
        }
        ctClass.addInterface(getCtClass(pool, name));
        infoLog("interface:" + name);
    }

    private static void addMethodAndLog(CtClass ctClass, String method) throws Exception {
        infoLog("method :" + method);
        ctClass.addMethod(CtNewMethod.make(method, ctClass));
    }

    private static void infoLog(String message) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(message);
        }
    }

    public static boolean isMap(Class clazz) {
        if (Map.class == clazz) {
            return true;
        }
        return ClassUtils.getAllInterfacesForClassAsSet(clazz).stream().anyMatch(new Predicate<Class<?>>() {
            @Override
            public boolean test(Class<?> aClass) {
                return Map.class == aClass;
            }
        });
    }

    public static boolean isBean(Class clazz) {
        return false;
    }

    public static boolean isSet(Class clazz) {
        if (Set.class == clazz) {
            return true;
        }
        return ClassUtils.getAllInterfacesForClassAsSet(clazz).stream().anyMatch(new Predicate<Class<?>>() {
            @Override
            public boolean test(Class<?> aClass) {
                return Set.class == aClass;
            }
        });
    }

    public static boolean isList(Class clazz) {
        if (List.class == clazz) {
            return true;
        }
        return ClassUtils.getAllInterfacesForClassAsSet(clazz).stream().anyMatch(new Predicate<Class<?>>() {
            @Override
            public boolean test(Class<?> aClass) {
                return List.class == aClass;
            }
        });
    }
}
