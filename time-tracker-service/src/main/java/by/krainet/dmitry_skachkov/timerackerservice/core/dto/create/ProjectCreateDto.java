package by.krainet.dmitry_skachkov.timerackerservice.core.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectCreateDto {

    private String name;

    private String description;
}
