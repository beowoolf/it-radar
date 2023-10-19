package pl.mojezapiski.itradar.domain.job;

import lombok.Getter;

@Getter
public class JobNotFoundException extends RuntimeException {

    public JobNotFoundException(Long jobId) {
        super(String.format("Job with id %s not found", jobId));
    }

}
