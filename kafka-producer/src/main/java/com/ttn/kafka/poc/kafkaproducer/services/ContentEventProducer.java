package com.ttn.kafka.poc.kafkaproducer.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttn.kafka.poc.kafkaproducer.event.ContentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component
@Slf4j
public class ContentEventProducer {

    @Autowired
    KafkaTemplate<Integer, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    String topic = "content-events";

    public void sendContentEvent(ContentEvent contentEvent) throws JsonProcessingException {

        Integer key = contentEvent.getContentEventId();
        String value = objectMapper.writeValueAsString(contentEvent);
        ListenableFuture<SendResult<Integer, String>> listenableFuture
                = kafkaTemplate.sendDefault(key, value);
        listenableFuture.addCallback(
                new ListenableFutureCallback<SendResult<Integer, String>>() {
                    @Override
                    public void onFailure(Throwable ex) {
                        log.error("...Failure..." + ex);
                    }

                    @Override
                    public void onSuccess(SendResult<Integer, String> result) {

                        log.info("...onSuccess..." + result.getProducerRecord().value());
                    }
                });
    }

    public void sendSyncContentEvent(ContentEvent contentEvent) throws JsonProcessingException {

        Integer key = contentEvent.getContentEventId();
        String value = objectMapper.writeValueAsString(contentEvent);
        try {
            SendResult<Integer, String> sendResult = kafkaTemplate.sendDefault(key, value).get(1, TimeUnit.SECONDS);
            log.info("Result : {}", sendResult);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

    }
}
