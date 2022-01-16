package io.github.meta.ease.alibaba.provider.examples.controller;

import io.github.meta.ease.alibaba.provider.examples.feign.FeigConsumerClinet;
import io.github.meta.ease.domain.dto.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 接口层
 */
@RestController
public class MetricsController {

    @Autowired
    private FeigConsumerClinet feigConsumerClinet;

    @GetMapping(value = "/metrics/ata")
    public SingleResponse<String> listATAMetrics(@RequestParam String ownerId) {
        feigConsumerClinet.listATAMetrics(ownerId);
        return SingleResponse.of(ownerId);
    }

    @GetMapping("/echo/{string}")
    public String echo(@PathVariable String string) {
        return "hello Nacos Discovery " + string;
    }

    @GetMapping("/sleep")
    public String sleep() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }
}