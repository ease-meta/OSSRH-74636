package io.github.meta.ease.alibaba.provider.examples.feign;

import io.github.meta.ease.domain.dto.SingleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author leijian
 * @version 1.0
 * @date 2022/1/16 16:01
 */
@FeignClient(value = "alibaba-provider-examples")
public interface FeigConsumerClinet {

    @GetMapping("/metrics/ata")
    SingleResponse<String> listATAMetrics(@RequestParam String ownerId);
}
