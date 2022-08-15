package io.github.meta.ease.cache.autoconfigure;

import io.github.meta.ease.cache.CacheConfig;
import io.github.meta.ease.cache.QuickstartApplication;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

/**
 * <p>文件名称:  CacheConfigTest</p>
 * <p>描述:     </p>
 * <p>创建时间:  2022/8/12</p>
 *
 * @author Abu
 * @version 22.0.1
 * @since 22.0.1
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {QuickstartApplication.class}, webEnvironment = NONE)
@Disabled
class CacheConfigTest {
    @Autowired
    CacheConfig cacheConfig;

    @Test
    void cache() {
        cacheConfig.findAll();
        cacheConfig.findAll();
    }
}