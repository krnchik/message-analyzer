package liga.medical.medicalmonitoring.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import liga.medical.common.dto.RabbitMessageDto;
import liga.medical.medicalmonitoring.core.annotation.DbLog;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonQueueRabbitMQHandler implements RabbitMQHandler {
    private final AmqpTemplate template;

    @Autowired
    public CommonQueueRabbitMQHandler(AmqpTemplate template) {
        this.template = template;
    }

    @DbLog
    public void handle(RabbitMessageDto message) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(message);
            template.convertAndSend("common_monitoring", message.getType().toString(), json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
