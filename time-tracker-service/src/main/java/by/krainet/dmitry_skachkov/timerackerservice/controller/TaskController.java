package by.krainet.dmitry_skachkov.timerackerservice.controller;

import by.krainet.dmitry_skachkov.timerackerservice.core.dto.TaskDto;
import by.krainet.dmitry_skachkov.timerackerservice.core.dto.create.TaskCreateDto;
import by.krainet.dmitry_skachkov.timerackerservice.service.api.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody TaskCreateDto taskCreateDto) {
        taskService.createTask(taskCreateDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<TaskDto> getTaskByUuid(@PathVariable UUID uuid) {
        TaskDto taskDto = taskService.getByUuid(uuid);
        return ResponseEntity.ok(taskDto);
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> taskDtos = taskService.getAll();
        return ResponseEntity.ok(taskDtos);
    }

    @PutMapping("/{uuid}/status")
    public ResponseEntity<Void> setStatus(@PathVariable UUID uuid, @RequestParam String status) {
        taskService.setStatus(uuid, status);
        return ResponseEntity.ok().build();
    }
}