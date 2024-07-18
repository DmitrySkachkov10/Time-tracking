package by.krainet.dmitry_skachkov.timerackerservice.core.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskCreateDto {

    private String name;

    private String description;

    private String projectUuid;
}
