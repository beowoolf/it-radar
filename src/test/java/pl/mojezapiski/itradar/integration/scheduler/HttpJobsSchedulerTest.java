package pl.mojezapiski.itradar.integration.scheduler;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import pl.mojezapiski.itradar.ItRadarApplication;
import pl.mojezapiski.itradar.domain.job.JobFetchable;
import pl.mojezapiski.itradar.integration.BaseIntegrationTest;

import java.time.Duration;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@SpringBootTest(classes = ItRadarApplication.class, properties = "scheduling.enabled=true")
public class HttpJobsSchedulerTest extends BaseIntegrationTest {

    @SpyBean
    JobFetchable remoteJobClient;

    @Test
    public void should_run_http_client_jobs_fetching_exactly_given_times() {
        await().
                atMost(Duration.ofSeconds(2))
                .untilAsserted(() -> verify(remoteJobClient, times(1)).fetchJobs());
    }

}
