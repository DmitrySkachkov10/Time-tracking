package by.krainet.dmitry_skachkov.timerackerservice.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskDto {

    private String uuid;

    private String name;

    private String description;

    private String status;

    private String projectUuid;


}
