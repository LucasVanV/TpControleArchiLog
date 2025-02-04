package fr.lucas_van_vooren.borrowing_service.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BorrowingKafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic.borrowing:borrowing-events}")
    private String topic;

    public BorrowingKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNbUserEvent(Long userId, int nb) {
        String event = String.format("{\"event\":\"CREATION_BORROWING\",\"userId\":\"%s\", \"nb\":\"%d\"}", userId, nb);
        kafkaTemplate.send(topic, event);
    }    
}
