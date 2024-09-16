package pl.mojezapiski.itradar.infrastructure.job.http;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.mojezapiski.itradar.domain.job.JobFetchable;

@Configuration
public class JobHttpClientConfig {

    @Bean
    public JobFetchable remoteJobClient(RestTemplate restTemplate,
                                        @Value("${job.http.client.config.uri:http://example.com}") String uri,
                                        @Value("${job.http.client.config.port:5057}") int port) {
        return new JobHttpClient(restTemplate, uri, port);
    }

}
