package io.github.meta.ease.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * <p>文件名称:  CacheConfig</p>
 * <p>描述:     </p>
 * <p>创建时间:  2022/8/12</p>
 *
 * @author Abu
 * @version 22.0.1
 * @since 22.0.1
 */
@Component
@Slf4j
public class CacheConfig {

    @Cacheable(cacheNames = {"student"})
    //@Cached(name = "student", expire = 3600,cacheType = CacheType.LOCAL)
    public Student findAll() {
        log.info("student");
        Student student = new Student();
        student.setName("haha");
        return student;
    }
}
