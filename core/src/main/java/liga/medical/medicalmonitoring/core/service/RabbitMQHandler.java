package liga.medical.medicalmonitoring.core.service;

import liga.medical.medicalmonitoring.core.model.Message;

public interface RabbitMQHandler {
    void handle(Message message);
}
