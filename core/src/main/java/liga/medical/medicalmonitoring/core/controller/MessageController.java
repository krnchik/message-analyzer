package liga.medical.medicalmonitoring.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import liga.medical.medicalmonitoring.core.model.Message;
import liga.medical.medicalmonitoring.core.service.RabbitMQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final RabbitMQ rabbitMQ;

    @Autowired
    public MessageController(RabbitMQ rabbitMQ) {
        this.rabbitMQ = rabbitMQ;
    }


    @PostMapping("/message")
    private ResponseEntity<Message> getMessage(@RequestBody Message message) {
        if (message == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            rabbitMQ.sendMessage(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
