package com.example.timesheet.rest;


import com.example.timesheet.model.Timesheet;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.timesheet.service.TimesheetService;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Timesheets",description = "API для работы с таймшитами")
public class TimesheetController {


    private final TimesheetService service;

    public TimesheetController(TimesheetService service) {
        this.service = service;
    }

    @Operation(
            summary = "получить таймшит по id сотрудника",
            description = "возвращает таймшит по id сотрудника",
            responses = {
                    @ApiResponse(description = "Успешный ответ",responseCode = "200", content = @Content(schema = @Schema(implementation = Timesheet.class))),
            }
    )
    @API.NotFoundResponse
    @API.InternalServerError
    @GetMapping("employees/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> findTimesheetsByEmployeeId(@Parameter(description = "Идентификатор сотрудника")@PathVariable Long id) {

        return ResponseEntity.ok(service.findTimesheetsByEmployeeId(id));
    }

    @Operation(
            summary = "получить таймшит",
            description = "возвращает таймшит по указанному id",
            responses = {
                    @ApiResponse(description = "Успешный ответ",responseCode = "200", content = @Content(schema = @Schema(implementation = Timesheet.class))),
            }
    )
    @API.NotFoundResponse
    @API.InternalServerError
    @GetMapping("/timesheets/{id}")
    public ResponseEntity<Timesheet> getById(@Parameter(description = "Идентификатор таймшита")@PathVariable Long id) {
        Optional<Timesheet> ts = service.findById(id);
        if (ts.isPresent()) {
            return ResponseEntity.ok().body(ts.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "получить все таймшиты",
            description = "возвращает все таймшиты",
            responses = {
                    @ApiResponse (description = "Успешный ответ",responseCode = "200", content = @Content(schema = @Schema(implementation = Timesheet.class))),
            }
    )
    @API.InternalServerError
    @GetMapping("/timesheets")
    public ResponseEntity<List<Timesheet>>getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(
            summary = "создать таймшит",
            description = "создает таймшит",
            responses = {
                    @ApiResponse (description = "Таймшит успешно создан",responseCode = "201", content = @Content(schema = @Schema(implementation = Timesheet.class))),
            }
    )
    @API.InternalServerError
    @PostMapping("/timesheets")
    public ResponseEntity<Timesheet> create(@RequestBody Timesheet timesheet) {
        timesheet = service.create(timesheet);
        if (timesheet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(timesheet);
    }

    @Operation(
            summary = "удалить таймшит",
            description = "удалитяет таймшит",
            responses = {
                    @ApiResponse (description = "Таймшит успешно удален",responseCode = "204", content = @Content(schema = @Schema(implementation = Void.class))),
            }
    )
    @API.NoContent
    @API.InternalServerError
    @DeleteMapping("/timesheets/{id}")
    public ResponseEntity<Void> delete (@Parameter(description = "Идентификатор таймшита")@PathVariable Long id) {
        service.delete(id);
        //204 no content
        return ResponseEntity.noContent().build();
    }
}
