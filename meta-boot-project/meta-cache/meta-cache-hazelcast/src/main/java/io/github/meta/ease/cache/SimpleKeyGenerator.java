package io.github.meta.ease.cache;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>文件名称:  SimpleKeyGenerator</p>
 * <p>描述:     </p>
 * <p>创建时间:  2022/8/15</p>
 *
 * @author Abu
 * @version 22.0.1
 * @since 22.0.1
 */
public class SimpleKeyGenerator implements KeyGenerator {

    private Map<String, String> keyMap = new ConcurrentHashMap<String, String>();

    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuffer sb = new StringBuffer();
        String packageName = target.getClass().getPackage().getName();
        if (StringUtils.isNotEmpty(packageName)) {
            if (keyMap.containsKey(packageName)) {
                sb.append(keyMap.get(packageName));
            } else {
                String[] strs = packageName.split("\\.", 15);
                StringBuffer sb1 = new StringBuffer();
                for (String str1 : strs) {
                    sb1.append(str1.substring(0, 1)).append(".");
                }
                keyMap.put(packageName, sb1.toString());
                sb.append(sb1.toString());
            }
        }
        sb.append(target.getClass().getSimpleName());
        sb.append(":" + method.getName());
        for (Object obj : params) {
            sb.append("_");
            sb.append(obj);
        }
        return sb.toString();
    }
}
