package by.krainet.dmitry_skachkov.timerackerservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.dialect.PostgreSQLIntervalSecondJdbcType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "record", schema = "time_tracking")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(RecordID.class)
public class Record {

    @Id
    @Column(name = "user_uuid")
    private UUID userUuid;

    @Id
    @Column(name = "task_uuid")
    private UUID taskUuid;

    @Column(name = "assigned_at", nullable = false)
    private LocalDateTime assignedAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_uuid", insertable = false, updatable = false)
    private Task task;
}
