package pl.mojezapiski.itradar.infrastructure.job.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.mojezapiski.itradar.domain.job.JobFacade;
import pl.mojezapiski.itradar.domain.job.dto.JobResponseDto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class HttpJobsScheduler {

    private static final String STARTED_OFFERS_FETCHING_MESSAGE = "Started jobs fetching {}";
    private static final String STOPPED_OFFERS_FETCHING_MESSAGE = "Stopped jobs fetching {}";
    private static final String ADDED_NEW_OFFERS_MESSAGE = "Added new {} jobs";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final JobFacade jobFacade;

    @Scheduled(fixedDelayString = "${http.jobs.scheduler.request.delay}")
    public List<JobResponseDto> fetchAllJobsAndSaveAllIfNotExists() {
        log.info(STARTED_OFFERS_FETCHING_MESSAGE, dateFormat.format(new Date()));
        final List<JobResponseDto> addedJobs = jobFacade.fetchAllJobsAndSaveAllIfNotExists();
        log.info(ADDED_NEW_OFFERS_MESSAGE, addedJobs.size());
        log.info(STOPPED_OFFERS_FETCHING_MESSAGE, dateFormat.format(new Date()));
        return addedJobs;
    }

}
