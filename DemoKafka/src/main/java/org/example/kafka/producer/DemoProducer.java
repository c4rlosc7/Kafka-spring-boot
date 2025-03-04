package org.example.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class DemoProducer {
    public static final Logger LOG = LoggerFactory.getLogger(DemoProducer.class);

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("acks", "1");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        long startTime = 0;
        try (Producer<String, String> producer = new KafkaProducer<>(properties)){
            for (int i = 0; i < 100; i++) {
                producer.send(new ProducerRecord<>("topic-test-00", "key", "message: "+i));
            }
            producer.flush();
        }
        LOG.info("Processing time = {} ms", (System.currentTimeMillis() - startTime));
    }
}
