package ru.rtln.notificationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rtln.notificationservice.entity.SentParticipantSystemEvent;

public interface SentParticipantSystemEventRepository extends JpaRepository<SentParticipantSystemEvent, Long> {

}