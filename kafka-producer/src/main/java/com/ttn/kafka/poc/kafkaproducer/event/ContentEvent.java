package com.ttn.kafka.poc.kafkaproducer.event;

import com.ttn.kafka.poc.kafkaproducer.model.Content;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class ContentEvent {
    private Integer contentEventId;
    private Content content;
}
