package pl.mojezapiski.itradar.domain.job;

import pl.mojezapiski.itradar.domain.job.dto.JobResponse;

import java.util.List;

public class InMemoryFetcherTestImpl implements JobFetchable {

    List<JobResponse> listOfJobs;

    InMemoryFetcherTestImpl(List<JobResponse> listOfJobs) {
        this.listOfJobs = listOfJobs;
    }

    @Override
    public List<JobResponse> fetchJobs() {
        return listOfJobs;
    }

}
