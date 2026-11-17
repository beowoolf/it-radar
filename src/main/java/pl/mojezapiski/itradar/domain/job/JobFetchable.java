package pl.mojezapiski.itradar.domain.job;


import pl.mojezapiski.itradar.domain.job.dto.JobResponse;

import java.util.List;

public interface JobFetchable {

    List<JobResponse> fetchJobs();

}
