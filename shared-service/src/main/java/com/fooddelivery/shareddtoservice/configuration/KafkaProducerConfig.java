package com.fooddelivery.shareddtoservice.configuration;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Bean
    public ProducerFactory<String, Map<String, Object>> producerFactory() {
        Map<String, Object> configs = new HashMap<>();

//        if ("dev".equals(activeProfile)) {
//            configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        } else {
//            configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-service:9092");
//        }
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-service:9092");
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean
    public KafkaTemplate<String, Map<String, Object>> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}

