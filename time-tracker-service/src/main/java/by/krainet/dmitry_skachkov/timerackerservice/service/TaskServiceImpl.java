package by.krainet.dmitry_skachkov.timerackerservice.service;

import by.dmitryskachkov.exception.exceptions.ValidationException;
import by.krainet.dmitry_skachkov.timerackerservice.core.dto.TaskDto;
import by.krainet.dmitry_skachkov.timerackerservice.core.dto.create.TaskCreateDto;
import by.krainet.dmitry_skachkov.timerackerservice.entity.Project;
import by.krainet.dmitry_skachkov.timerackerservice.entity.Task;
import by.krainet.dmitry_skachkov.timerackerservice.entity.enums.Status;
import by.krainet.dmitry_skachkov.timerackerservice.repo.ProjectRepo;
import by.krainet.dmitry_skachkov.timerackerservice.repo.TaskRepo;
import by.krainet.dmitry_skachkov.timerackerservice.service.api.ProjectService;
import by.krainet.dmitry_skachkov.timerackerservice.service.api.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;

    private final ProjectRepo projectRepo;

    private final ProjectService projectService;

    public TaskServiceImpl(TaskRepo taskRepo, ProjectRepo projectRepo, ProjectService projectService) {
        this.taskRepo = taskRepo;
        this.projectRepo = projectRepo;
        this.projectService = projectService;
    }

    @Override
    @Transactional
    public void createTask(TaskCreateDto taskCreateDto) {

        taskRepo.save(new Task(UUID.randomUUID(),
                taskCreateDto.getName(),
                taskCreateDto.getDescription(),
                Status.NOT_STARTED,
                new Project(UUID.fromString(taskCreateDto.getProjectUuid())),
                null));
    }

    @Override
    public TaskDto getByUuid(UUID uuid) {
        Task task = taskRepo.findById(uuid).orElseThrow(() -> new ValidationException("Task not found with UUID: " + uuid));
        return new TaskDto(task.getUuid().toString(),
                task.getName(),
                task.getDescription(),
                task.getStatus().toString(),
                task.getProject().getUuid().toString());
    }

    public List<TaskDto> getAll() {
        List<Task> tasks = taskRepo.findAll();
        return tasks.stream()
                .map(task -> new TaskDto(
                        task.getUuid().toString(),
                        task.getName(),
                        task.getDescription(),
                        task.getStatus().toString(),
                        task.getProject().getUuid().toString()))
                .toList();
    }

    @Override
    @Transactional
    public void setStatus(UUID uuid, String status) {
        Task task = taskRepo.findById(uuid).orElseThrow(() -> new ValidationException("Task not found with UUID: " + uuid));
        task.setStatus(Status.valueOf(status));
        taskRepo.save(task);
    }
}
