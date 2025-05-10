package ru.rtln.notificationservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ru.rtln.notificationservice.util.enumeration.SystemEventType;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NamedEntityGraph(
        name = "graph.SystemEventWithParticipants",
        attributeNodes = @NamedAttributeNode(value = "participants")
)
@Table(name = "system_event", schema = "notification")
@Entity
public class SystemEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "entity_id", nullable = false)
    private String entityId;

    @NotNull
    @Column(name = "type", nullable = false)
    private SystemEventType type;

    @NotNull
    @Size(max = 16384)
    @Column(name = "content", nullable = false, length = 16384)
    private String content;

    @NotNull
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @OneToMany(mappedBy = "systemEvent")
    private List<SystemEventParticipant> participants;
}