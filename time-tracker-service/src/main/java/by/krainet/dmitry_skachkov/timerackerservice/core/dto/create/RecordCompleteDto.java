package by.krainet.dmitry_skachkov.timerackerservice.core.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecordCompleteDto {
    private UUID userUuid;
    private UUID taskUuid;
    private String status;
}
