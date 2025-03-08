package com.example.spring_kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class SpringKafkaApplication {

	public static final String TOPIC_TEST_00 = "topic-test-00";
	public static final String CARLOS_GROUP_CONSUMER = "carlos-group-consumer";

	private static final Logger log = LoggerFactory.getLogger(SpringKafkaApplication.class);

	@KafkaListener(topics = TOPIC_TEST_00, groupId = CARLOS_GROUP_CONSUMER)
	public void listen(String message) {
		log.info("Message received {} ", message);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaApplication.class, args);
	}

}
