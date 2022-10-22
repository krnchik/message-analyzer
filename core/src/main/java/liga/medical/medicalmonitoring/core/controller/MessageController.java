package liga.medical.medicalmonitoring.core.controller;

import liga.medical.medicalmonitoring.core.model.Message;
import liga.medical.medicalmonitoring.core.service.RabbitMQHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final RabbitMQHandler rabbitMQ;

    @Autowired
    public MessageController(RabbitMQHandler rabbitMQ) {
        this.rabbitMQ = rabbitMQ;
    }

    @PostMapping("/message")
    private ResponseEntity<Message> getMessage(@RequestBody Message message) {
        if (message == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        rabbitMQ.handle(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
