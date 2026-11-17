package pl.mojezapiski.itradar.infrastructure.job.controller.error;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.mojezapiski.itradar.domain.job.JobNotFoundException;

import java.util.Collections;

@Log4j2
@ControllerAdvice
class JobControllerErrorHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(JobNotFoundException.class)
    @ResponseBody
    public JobErrorResponse jobNotFound(JobNotFoundException exception) {
        final String message = exception.getMessage();
        log.error(message);
        return new JobErrorResponse(message, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public JobPostErrorResponse jobDuplicate(DataIntegrityViolationException duplicateKeyException) {
        final String message = "Job url already exists";
        log.error(message);
        return new JobPostErrorResponse(Collections.singletonList(message), HttpStatus.CONFLICT);
    }

}
