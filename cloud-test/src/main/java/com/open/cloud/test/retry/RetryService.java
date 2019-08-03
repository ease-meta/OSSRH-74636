package com.open.cloud.test.retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

/**
 * 1、使用了@Retryable的方法不能在本类被调用，不然重试机制不会生效。也就是要标记为@Service，然后在其它类使用@Autowired注入或者@Bean去实例才能生效。
 * <p>
 * 2 、要触发@Recover方法，那么在@Retryable方法上不能有返回值，只能是void才能生效。
 * <p>
 * 3 、非幂等情况下慎用
 * <p>
 * 4 、使用了@Retryable的方法里面不能使用try...catch包裹，要在方法上抛出异常，不然不会触发。
 */
@Service
public class RetryService {

    private final static Logger logger = LoggerFactory.getLogger(RetryService.class);

    private final int totalNum = 100000;

    /**
     * @Retryable的参数说明： •value：抛出指定异常才会重试
     * •include：和value一样，默认为空，当exclude也为空时，默认所以异常
     * •exclude：指定不处理的异常
     * •maxAttempts：最大重试次数，默认3次
     * •backoff：重试等待策略，默认使用@Backoff，@Backoff的value默认为1000L，我们设置为2000L；multiplier（指定延迟倍数）默认为0，表示固定暂停1秒后进行重试，如果把multiplier设置为1.5，则第一次重试为2秒，第二次为3秒，第三次为4.5秒。
     */
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000L, multiplier = 1.5))
    public int retry(int num) {
        logger.info("减库存开始" + LocalTime.now());
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            logger.error("illegal");
        }
        if (num <= 0) {
            throw new IllegalArgumentException("数量不对");
        }
        logger.info("减库存执行结束" + LocalTime.now());
        return totalNum - num;
    }

    @Recover
    public void recover(Exception e) {
        logger.warn("减库存失败！！！" + LocalTime.now());
    }


}