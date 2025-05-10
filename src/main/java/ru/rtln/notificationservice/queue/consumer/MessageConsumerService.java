package ru.rtln.notificationservice.queue.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.rtln.notificationservice.queue.model.event.AbstractEvent;

@Slf4j
@Component
public class MessageConsumerService {

    private final ApplicationEventPublisher eventPublisher;
    private final ObjectMapper objectMapper;

    public MessageConsumerService(ApplicationEventPublisher eventPublisher, ObjectMapper objectMapper) {
        this.eventPublisher = eventPublisher;
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @KafkaListener(
            topics = "${settings.kafka.topic.system-event}",
            filter = "ConsumerMessageFilter"
    )
    public void receive(ConsumerRecord<String, String> message) {
        try {
            log.info("Message received [{}]", message);
            AbstractEvent<?> event = objectMapper.readValue(message.value(), AbstractEvent.class);
            eventPublisher.publishEvent(event);
        } catch (Exception e) {
            log.error("Error proceed while publish event", e);
        }
    }
}
