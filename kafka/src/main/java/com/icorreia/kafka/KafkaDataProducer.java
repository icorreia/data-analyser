package com.icorreia.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Check https://www.mapr.com/blog/getting-started-sample-programs-apache-kafka-09.
 *
 * @author Ivo Correia (idvcorreia@gmail.com)
 * @since 1.0
 */
public class KafkaDataProducer {

    /**
     * A Logger for this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(KafkaDataProducer.class);

    private final KafkaProducer<Object, Object> producer;

    publicKafkaDataProducer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers","localhost:9092");
        properties.put("acks","all");
        properties.put("retries","0");
        properties.put("batch.size","16384");
        properties.put("auto.commit.interval.ms","1000");
        properties.put("linger.ms","0");
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("block.on.buffer.full", "true");

        producer = new KafkaProducer(properties);
    }

    public void sendMessage() {
        producer.send(new ProducerRecord("fast-messages", "This is a dummy message"));
        logger.trace("Sent new Kafka message.");
    }

    public void cleanUp() {
        producer.close();
    }
}
