package pl.mojezapiski.itradar.integration.http;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import pl.mojezapiski.itradar.domain.job.JobFetchable;
import pl.mojezapiski.itradar.infrastructure.http.RestTemplateResponseErrorHandler;
import pl.mojezapiski.itradar.infrastructure.job.http.JobHttpClientConfig;

import java.time.Duration;

import static pl.mojezapiski.itradar.integration.BaseIntegrationTest.WIRE_MOCK_HOST;

public class JobHttpClientTestConfig extends JobHttpClientConfig {

    public JobFetchable remoteJobTestClient(int port, int connectionTimeout, int readTimeout) {
        RestTemplateResponseErrorHandler restTemplateResponseErrorHandler = new RestTemplateResponseErrorHandler();
        final RestTemplate restTemplate = new RestTemplateBuilder()
                .errorHandler(restTemplateResponseErrorHandler)
                .setConnectTimeout(Duration.ofMillis(connectionTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .build();
        return remoteJobClient(restTemplate, WIRE_MOCK_HOST, port);
    }

}
