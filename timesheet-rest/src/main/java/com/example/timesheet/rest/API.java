package com.example.timesheet.rest;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class API {

    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponse(description = "Страница не найдена",responseCode = "404", content = @Content(schema = @Schema(implementation = Void.class)))
    public @interface NotFoundResponse {

    }

    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponse(description = "Внутренняя ошибка сервера",responseCode = "500", content = @Content(schema = @Schema(implementation = Void.class)))
    public @interface InternalServerError {

    }

    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponse(description = "Нет контента",responseCode = "204", content = @Content(schema = @Schema(implementation = Void.class)))
    public @interface NoContent {

    }
}
