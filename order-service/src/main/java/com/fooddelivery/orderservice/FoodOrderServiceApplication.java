package com.fooddelivery.orderservice;

import com.fooddelivery.shareddtoservice.configuration.KafkaConsumerConfig;
import com.fooddelivery.shareddtoservice.configuration.KafkaProducerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({KafkaConsumerConfig.class, KafkaProducerConfig.class})
public class FoodOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodOrderServiceApplication.class, args);
	}

}
