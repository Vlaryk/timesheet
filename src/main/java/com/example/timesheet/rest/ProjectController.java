package com.example.timesheet.rest;

import com.example.timesheet.model.Project;
import com.example.timesheet.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
@Tag(name = "Projects",description = "API для работы с проектами")
public class ProjectController {

    private final ProjectService service;


    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @Operation(
            summary = "get project",
            description = "получить проект по id",
            responses = {
                    @ApiResponse (description = "Успешный ответ",responseCode = "200", content = @Content(schema = @Schema(implementation = Project.class))),
            }
    )
    @API.NotFoundResponse
    @GetMapping("/{id}")
    public ResponseEntity<Project> getById(@PathVariable @Parameter(description = "ИИдентификатор проекта") Long id) {
         Optional<Project> project =  service.getById(id);
        return project.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project) {
        project = service.create(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete (Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}
