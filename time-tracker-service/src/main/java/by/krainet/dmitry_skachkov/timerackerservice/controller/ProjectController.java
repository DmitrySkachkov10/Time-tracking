
package by.krainet.dmitry_skachkov.timerackerservice.controller;

import by.krainet.dmitry_skachkov.timerackerservice.core.dto.ProjectDto;
import by.krainet.dmitry_skachkov.timerackerservice.core.dto.create.ProjectCreateDto;
import by.krainet.dmitry_skachkov.timerackerservice.service.api.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<Void> createProject(@RequestBody ProjectCreateDto createDto) {
        projectService.createProject(createDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ProjectDto> getProjectByUuid(@PathVariable UUID uuid) {
        ProjectDto projectDto = projectService.getByUuid(uuid);
        return ResponseEntity.ok(projectDto);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<ProjectDto> projectDtos = projectService.getAll();
        return ResponseEntity.ok(projectDtos);
    }
}
