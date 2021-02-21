package org.apache.kafka;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@RestController
public class KafkaProducer {
    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("/kafka/normal/{msg}")
    public void sendMessage(@PathVariable("msg") String msg) {
        Message message = new Message();
        message.setId(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        message.setMessage(msg);
        kafkaTemplate.send("test", ObjectUtils.identityToString(message));
    }
}