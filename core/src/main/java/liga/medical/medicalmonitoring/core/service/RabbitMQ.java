package liga.medical.medicalmonitoring.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import liga.medical.medicalmonitoring.core.model.Message;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQ {
    private final AmqpTemplate template;

    @Autowired
    public RabbitMQ(AmqpTemplate template) {
        this.template = template;
    }

    public void sendMessage(Message message) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(message);
        template.convertAndSend("common_monitoring", json);
    }
}
