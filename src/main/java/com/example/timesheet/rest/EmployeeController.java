package com.example.timesheet.rest;

import com.example.timesheet.model.Employee;
import com.example.timesheet.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController()
@RequestMapping("/employees")
@RequiredArgsConstructor
@Tag(name = "Employees",description = "API для работы с сотрудниками")
public class EmployeeController {
    private final EmployeeService service;

    @Operation(
            summary = "получить всех сотрудников",
            description = "возвращает всех сотрудников",
            responses = {
                    @ApiResponse (description = "Успешный ответ",responseCode = "200", content = @Content(schema = @Schema(implementation = Employee.class))),
            }
    )
    @API.InternalServerError
    @GetMapping
    public ResponseEntity<List<Employee>> findAllEmployees() {
        return ResponseEntity.ok(service.findAll());
    }


    @Operation(
            summary = "найти сотрудника",
            description = "возвращает сотрудника по указанному id",
            responses = {
                    @ApiResponse(description = "Успешный ответ",responseCode = "200", content = @Content(schema = @Schema(implementation = Employee.class))),
            }
    )
    @API.NotFoundResponse
    @API.InternalServerError
    @GetMapping("/{id}")
    public ResponseEntity<Employee> findEmployee(@Parameter(description = "Идентификатор сотрудника")@PathVariable Long id) {
        Optional<Employee> employeeOpt = service.findById(id);
        if (employeeOpt.isEmpty()) {
            throw new NoSuchElementException();
        }
        return ResponseEntity.ok(employeeOpt.get());
    }


    @Operation(
            summary = "добавить сотрудника",
            description = "добавить сотрудника",
            responses = {
                    @ApiResponse (description = "Cотрудник успешно добавлен",responseCode = "201", content = @Content(schema = @Schema(implementation = Employee.class))),
            }
    )
    @API.InternalServerError
    @PostMapping
    public ResponseEntity<Employee> create(@Parameter(description = "Сотрудник")@RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(employee));
    }


    @Operation(
            summary = "удалить сотрудника",
            description = "удаляет сотрудника",
            responses = {
                    @ApiResponse (description = "Сотрудник успешно удален",responseCode = "204", content = @Content(schema = @Schema(implementation = Void.class))),
            }
    )
    @API.NotFoundResponse
    @API.InternalServerError
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@Parameter(description = "Идентификатор сотрудника")@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.notFound().build();
    }
}
