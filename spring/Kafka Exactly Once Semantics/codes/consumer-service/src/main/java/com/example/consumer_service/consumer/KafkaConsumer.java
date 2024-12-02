package com.example.consumer_service.consumer;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Data
public class KafkaConsumer {

    @KafkaListener(
            topics = "jslim-topic",
            groupId = "jslim-group"
    )
    public void receive(ConsumerRecord<String, Object> consumerRecord, Acknowledgment acknowledgment) {
        try {
            Object payload = consumerRecord.value();
            log.info("received payload = {}", payload.toString());

            acknowledgment.acknowledge();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }
}
