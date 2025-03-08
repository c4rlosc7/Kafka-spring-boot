package org.example.kafka.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class DemoConsumer {
    public static final Logger LOG = LoggerFactory.getLogger(DemoConsumer.class);
    public static final String TOPIC_TEST_00 = "topic-test-00";
    public static final Properties properties = new Properties();

    public static void setProperties() {
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", "carlos-group-consumer");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    }

    public static void main(String[] args) {
        setProperties();
        try (Consumer<String, String> consumer = new KafkaConsumer<>(properties)){
            // consumer.subscribe(List.of(TOPIC_TEST_00));
            TopicPartition topicPartition = new TopicPartition(TOPIC_TEST_00, 0);
            consumer.assign(List.of(topicPartition));
            consumer.seek(topicPartition, 50);
            while (true) {
                ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> consumerRecord: consumerRecords) {
                    LOG.info("Offset = {}, partition = {}, key = {}, value = {} ",
                            consumerRecord.offset(), consumerRecord.partition(), consumerRecord.key(), consumerRecord.value());
                }
            }
        }
    }
}
