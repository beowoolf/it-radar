package pl.mojezapiski.itradar.infrastructure.job.controller.error;

import org.springframework.http.HttpStatus;

public record JobErrorResponse(
        String message,
        HttpStatus status) {
}
