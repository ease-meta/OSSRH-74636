package io.github.meta.ease.alibaba.provider.examples.feign;

import io.github.meta.ease.domain.dto.SingleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author leijian
 * @version 1.0
 * @date 2022/1/16 16:01
 */
@FeignClient(value = "alibaba-provider-examples")
public interface FeigConsumerClinet {

    @PostMapping("/metrics/ata")
    SingleResponse<String> listATAMetrics(@RequestBody String ownerId);
}
