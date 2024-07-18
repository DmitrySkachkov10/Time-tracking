package by.krainet.dmitry_skachkov.timerackerservice.service.api;

import by.krainet.dmitry_skachkov.timerackerservice.core.dto.ProjectDto;
import by.krainet.dmitry_skachkov.timerackerservice.core.dto.create.ProjectCreateDto;

import java.util.List;
import java.util.UUID;

public interface ProjectService {

    void createProject(ProjectCreateDto createDto);

    ProjectDto getByUuid(UUID uuid);

    List<ProjectDto> getAll();


}
