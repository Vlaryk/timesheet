package com.example.timesheet.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Hello",description = "API для работы с приветствием")
public class HelloController {


    @Operation(
            summary = "Страница приветствия",
            description = "Страница которая приветствует пользователя",
            responses = {
                    @ApiResponse(description = "Успешный ответ",responseCode = "200", content = @Content(schema = @Schema(implementation = String.class))),
            }
    )
    @API.InternalServerError
    @GetMapping("/hello")
    public String helloPage(@Parameter(description = "Имя пользователя")@RequestParam String username) {
        return "<h1>Hello, " + username + "</h1>";
    }


    @Operation(
            summary = "Страница приветствия",
            description = "Страница которая приветствует пользователя",
            responses = {
        @ApiResponse(description = "Успешный ответ",responseCode = "200", content = @Content(schema = @Schema(implementation = String.class))),
    }
    )
    @API.InternalServerError
    @GetMapping("/hello/{username}")
    public String helloPagePathVariable(@Parameter(description = "Имя пользователя")@PathVariable String username) {
        return "<h1>Hello, " + username + "</h1>";
    }
}
