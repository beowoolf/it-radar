package pl.mojezapiski.itradar.infrastructure.job.controller.error;

import org.springframework.http.HttpStatus;

import java.util.List;

public record JobPostErrorResponse(List<String> messages,
                                   HttpStatus status) {
}
