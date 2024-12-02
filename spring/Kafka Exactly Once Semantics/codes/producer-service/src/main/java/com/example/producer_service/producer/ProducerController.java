package com.example.producer_service.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/producer")
public class ProducerController {

    private final KafkaProducer kafkaProducer;

    @GetMapping
    public ResponseEntity<Boolean> produce() {
        kafkaProducer.send("jslim-topic", "Hello world");

        return ResponseEntity.ok(true);
    }
}
