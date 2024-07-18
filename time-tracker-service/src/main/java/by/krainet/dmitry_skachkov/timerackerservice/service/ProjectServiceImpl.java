package by.krainet.dmitry_skachkov.timerackerservice.service;

import by.dmitryskachkov.exception.exceptions.ValidationException;
import by.krainet.dmitry_skachkov.timerackerservice.core.dto.ProjectDto;
import by.krainet.dmitry_skachkov.timerackerservice.core.dto.TaskDto;
import by.krainet.dmitry_skachkov.timerackerservice.core.dto.create.ProjectCreateDto;
import by.krainet.dmitry_skachkov.timerackerservice.entity.Project;
import by.krainet.dmitry_skachkov.timerackerservice.repo.ProjectRepo;
import by.krainet.dmitry_skachkov.timerackerservice.service.api.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepo projectRepo;

    public ProjectServiceImpl(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    @Override
    @Transactional
    public void createProject(ProjectCreateDto createDto) {
        projectRepo.save(new Project(UUID.randomUUID(),
                createDto.getName(),
                createDto.getDescription()));
    }

    @Override
    public ProjectDto getByUuid(UUID uuid) {
        Project project = projectRepo.findById(uuid).orElseThrow(() -> new ValidationException("Bad uuid"));
        return new ProjectDto(project.getUuid().toString(),
                project.getName(),
                project.getDescription(),
                project.getTasks().stream()
                        .map(m -> new TaskDto(m.getUuid().toString(),
                                m.getName(),
                                m.getDescription(),
                                m.getStatus().toString(),
                                m.getProject().getUuid().toString()))
                        .toList());
    }

    @Override
    public List<ProjectDto> getAll() {
        List<Project> projects = projectRepo.findAll();
        return projects.stream()
                .map(project -> new ProjectDto(
                        project.getUuid().toString(),
                        project.getName(),
                        project.getDescription(),
                        project.getTasks().stream()
                                .map(task -> new TaskDto(
                                        task.getUuid().toString(),
                                        task.getName(),
                                        task.getDescription(),
                                        task.getStatus().toString(),
                                        task.getProject().getUuid().toString()))
                                .toList()))
                .toList();
    }
}
