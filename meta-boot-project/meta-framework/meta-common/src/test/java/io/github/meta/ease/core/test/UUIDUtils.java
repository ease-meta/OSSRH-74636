package io.github.meta.ease.core.test;

import java.util.UUID;

/**
 * @author binghe
 * @version 1.0.0
 * @description 生成没有-的UUID
 */
public class UUIDUtils {

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "");
    }
}
