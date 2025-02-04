package fr.lucas_van_vooren.user_service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.lucas_van_vooren.user_service.service.UserService;

@Service
public class UserKafkaConsumer {
    @Autowired
    private UserService userService;

    @KafkaListener(topics = "borrowing-events", groupId = "user-group")
    public void consumeCreateBorrowEvent(String message){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode event = objectMapper.readTree(message);

            if ("CREATION_BORROWING".equals(event.get("event").asText())) {
                Long userId = Long.valueOf(event.get("userId").asText());
                int nb = event.get("nb").asInt();
                userService.addBorrow(userId, nb);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
