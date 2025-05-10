package ru.rtln.notificationservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "system_event_participant", schema = "notification")
@Entity
public class SystemEventParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "system_event_id", nullable = false)
    private SystemEvent systemEvent;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @OneToMany(mappedBy = "systemEventParticipant")
    private Set<SentParticipantSystemEvent> sentParticipantSystemEvents = new LinkedHashSet<>();

    public SystemEventParticipant(Long id) {
        this.id = id;
    }

    public SystemEventParticipant(Long userId, SystemEvent systemEvent) {
        this.userId = userId;
        this.systemEvent = systemEvent;
    }
}