package ru.rtln.notificationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.rtln.notificationservice.entity.SystemEventParticipant;

import java.util.List;

public interface SystemEventParticipantRepository extends JpaRepository<SystemEventParticipant, Long> {

    @Query("""
            SELECT  ep
             FROM   SystemEventParticipant ep
             LEFT JOIN SentParticipantSystemEvent spe
               ON   spe.systemEventParticipant.id = ep.id AND
                    spe.sent = FALSE
            """)
    List<SystemEventParticipant> findUnsentSystemEventParticipants();
}