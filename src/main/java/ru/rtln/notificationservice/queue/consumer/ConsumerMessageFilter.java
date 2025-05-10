package ru.rtln.notificationservice.queue.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.rtln.notificationservice.queue.determinant.PayloadDeterminant;

@Slf4j
@Service("ConsumerMessageFilter")
public class ConsumerMessageFilter implements RecordFilterStrategy<String, String> {

    @Override
    public boolean filter(@NonNull ConsumerRecord<String, String> consumerRecord) {
        try {
            if (!PayloadDeterminant.keyIncluded(consumerRecord.key())) {
                log.warn("Message with type {} ignored", consumerRecord.key());
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.error("Message filter failed {} {}", e, consumerRecord);
        }
        return true;
    }
}
