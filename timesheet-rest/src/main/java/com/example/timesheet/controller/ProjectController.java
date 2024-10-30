package com.example.timesheet.controller;

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
            summary = "получить проект",
            description = "возвращает проект по указанному id",
            responses = {
                    @ApiResponse (description = "Успешный ответ",responseCode = "200", content = @Content(schema = @Schema(implementation = Project.class))),
            }
    )
    @API.NotFoundResponse
    @API.InternalServerError
    @GetMapping("/{id}")
    public ResponseEntity<Project> getById(@PathVariable @Parameter(description = "Идентификатор проекта") Long id) {
         Optional<Project> project =  service.getById(id);
        return project.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(
            summary = "получить все проекты",
            description = "возвращает все проекты",
            responses = {
                    @ApiResponse (description = "Успешный ответ",responseCode = "200", content = @Content(schema = @Schema(implementation = Project.class))),
            }
    )
    @API.InternalServerError
    @GetMapping
    public ResponseEntity<List<Project>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }


    @Operation(
            summary = "создать проект",
            description = "создает проект",
            responses = {
                    @ApiResponse (description = "Проект успешно создан",responseCode = "201", content = @Content(schema = @Schema(implementation = Project.class))),
            }
    )
    @API.InternalServerError
    @PostMapping
    public ResponseEntity<Project> create(@Parameter(description = "Проект")@RequestBody Project project) {
        project = service.create(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }


    @Operation(
            summary = "удалить проект",
            description = "удалитяет проект",
            responses = {
                    @ApiResponse (description = "Проект успешно удален",responseCode = "204", content = @Content(schema = @Schema(implementation = Void.class))),
            }
    )
    @API.NoContent
    @API.InternalServerError
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete (@Parameter(description = "Идентификатор проекта")Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}
