package com.example.personmenegement.config.kafka;

import com.example.personmenegement.entity.PersonEntity;
import com.example.personmenegement.entity.TaskEntity;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Bean
    public ConsumerFactory<String, TaskEntity> taskEntityConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(TaskEntity.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TaskEntity> taskEntityKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, TaskEntity> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(taskEntityConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, PersonEntity> personEntityConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(PersonEntity.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PersonEntity> personEntityKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PersonEntity> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(personEntityConsumerFactory());
        return factory;
    }
}