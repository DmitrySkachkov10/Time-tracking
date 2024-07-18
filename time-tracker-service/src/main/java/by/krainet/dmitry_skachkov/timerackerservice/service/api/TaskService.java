package by.krainet.dmitry_skachkov.timerackerservice.service.api;

import by.krainet.dmitry_skachkov.timerackerservice.core.dto.TaskDto;
import by.krainet.dmitry_skachkov.timerackerservice.core.dto.create.TaskCreateDto;
import by.krainet.dmitry_skachkov.timerackerservice.entity.enums.Status;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    void createTask(TaskCreateDto taskCreateDto);

    TaskDto getByUuid(UUID uuid);

    List<TaskDto> getAll();

    void setStatus(UUID uuid, String status);
}
