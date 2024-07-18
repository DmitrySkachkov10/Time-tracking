package by.krainet.dmitry_skachkov.timerackerservice.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecordDto {

    private String taskUuid;

    private String userUuid;

    private LocalDateTime assignedAt;

    private LocalDateTime completedAt;

}
