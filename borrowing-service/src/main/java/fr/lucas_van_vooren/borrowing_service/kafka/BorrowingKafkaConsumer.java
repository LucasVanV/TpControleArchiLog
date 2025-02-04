package fr.lucas_van_vooren.borrowing_service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.lucas_van_vooren.borrowing_service.service.BorrowingService;

@Service
public class BorrowingKafkaConsumer {
    
    @Autowired
    private BorrowingService borrowingService;

    @KafkaListener(topics = "book-events", groupId = "borrowing-group")
    public void consumeBookDeletedEvent(String message){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode event = objectMapper.readTree(message);

            if("BOOK_DELETED".equals(event.get("event").asText())){
                Long bookId = Long.valueOf(event.get("bookId").asText());
                borrowingService.deleteBorrowByBookId(bookId);
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @KafkaListener(topics = "user-events", groupId = "borrowing-group")
    public void consumeUserDeletedEvent(String message){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode event = objectMapper.readTree(message);

            if("USER_DELETED".equals(event.get("event").asText())){
                Long bookId = Long.valueOf(event.get("userId").asText());
                borrowingService.deleteBorrowByUserId(bookId);
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
