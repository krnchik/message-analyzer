package liga.medical.medicalmonitoring.core.service;

import liga.medical.common.dto.RabbitMessageDto;

public interface RabbitMQHandler {
    void handle(RabbitMessageDto message);
}
