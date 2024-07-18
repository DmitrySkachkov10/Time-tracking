package by.krainet.dmitry_skachkov.timerackerservice.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectDto {

    private String uuid;

    private String name;

    private String description;

    private List<TaskDto> taskDtoList = new ArrayList<>();
}
