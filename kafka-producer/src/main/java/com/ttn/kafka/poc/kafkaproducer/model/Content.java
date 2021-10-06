package com.ttn.kafka.poc.kafkaproducer.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class Content {
    private Integer contentId;
    private String contentName;
    private String contentType;
}
