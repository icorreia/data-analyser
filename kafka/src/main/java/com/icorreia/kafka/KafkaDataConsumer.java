package com.icorreia.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;

/**
 *
 * @author Ivo Correia (idvcorreia@gmail.com)
 * @since 1.0
 */
public class KafkaDataConsumer {

    /**
     * A Logger for this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(KafkaDataConsumer.class);

    private final KafkaConsumer<Object, Object> consumer;

    public KafkaDataConsumer() {
        // specify some consumer properties
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", "test");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");

        consumer = new KafkaConsumer(properties);
    }

    public void consumeMessage() {
        consumer.subscribe(Arrays.asList("fast-messages", "summary-markers"));

        int readMessages = 0;
        while (readMessages < 5) {
            ConsumerRecords<Object, Object> records = consumer.poll(200);

            for (ConsumerRecord record : records) {
                switch (record.topic()) {
                    case "fast-messages":
                        // deal with messages from fast-messages topic
                        logger.info("Received message for fast-messages.");
                        break;
                    case "summary-markers":
                        logger.info("Received message for fast-messages.");
                        break;
                    default:
                        logger.info("Received message for unknown topic.");
                }
            }

            readMessages++;
        }
    }

    public void cleanUp() {
        consumer.close();
    }
}
