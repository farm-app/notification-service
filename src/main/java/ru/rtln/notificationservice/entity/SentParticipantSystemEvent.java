package ru.rtln.notificationservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "sent_system_event_participant", schema = "notification")
@Entity
public class SentParticipantSystemEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "system_event_participant_id", nullable = false)
    private SystemEventParticipant systemEventParticipant;

    @NotNull
    @Column(name = "sent", nullable = false)
    private Boolean sent;

    public SentParticipantSystemEvent(
            Long systemEventParticipantId,
            Boolean sent
    ) {
        this.systemEventParticipant = new SystemEventParticipant(systemEventParticipantId);
        this.sent = sent;
    }
}