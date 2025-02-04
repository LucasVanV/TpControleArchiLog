package fr.lucas_van_vooren.user_service.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserKafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic.user:user-events}")
    private String topic;

    public UserKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserDeleteEvent(Long accountId) {
        String event = String.format("{\"event\":\"USER_DELETED\",\"userId\":\"%s\"}", accountId);
        kafkaTemplate.send(topic, event);
    }
}
