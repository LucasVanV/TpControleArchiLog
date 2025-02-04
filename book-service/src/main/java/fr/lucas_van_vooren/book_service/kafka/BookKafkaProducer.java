package fr.lucas_van_vooren.book_service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;


@Service
public class BookKafkaProducer {
    
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic.book:book-events}")
    private String topic;

    public BookKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendBookDeleteEvent(Long accountId) {
        String event = String.format("{\"event\":\"BOOK_DELETED\",\"bookId\":\"%s\"}", accountId);
        kafkaTemplate.send(topic, event);
    }
}
