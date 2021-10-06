package com.ttn.kafka.poc.kafkaproducer.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ttn.kafka.poc.kafkaproducer.event.ContentEvent;
import com.ttn.kafka.poc.kafkaproducer.services.ContentEventProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ContentEventController {

    @Autowired
    ContentEventProducer contentEventProducer;

    @PostMapping("/contentEvent")
    public ResponseEntity<ContentEvent> postContentEvent(@RequestBody ContentEvent contentEvent) throws JsonProcessingException {
        log.info("Event Start ..................");
        contentEventProducer.sendSyncContentEvent(contentEvent);
        log.info("Event End..................");
        return ResponseEntity.status(HttpStatus.CREATED).body(contentEvent);
    }
}
