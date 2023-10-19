package pl.mojezapiski.itradar.infrastructure.job.http;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import pl.mojezapiski.itradar.domain.job.JobFetchable;
import pl.mojezapiski.itradar.domain.job.dto.JobResponse;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
public class JobHttpClient implements JobFetchable {

    private final RestTemplate restTemplate;
    private final String uri;
    private final int port;

    @Override
    public List<JobResponse> fetchJobs() {
        log.info("Started fetching jobs using http client");
        HttpHeaders headers = new HttpHeaders();
        final HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);
        try {
            String urlForService = getUrlForService("/api/offers");
            final String url = UriComponentsBuilder.fromHttpUrl(urlForService).toUriString();
            ResponseEntity<List<JobResponse>> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
                    new ParameterizedTypeReference<>() {
                    });
            final List<JobResponse> body = response.getBody();
            if (body == null) {
                log.error("Response Body was null");
                throw new ResponseStatusException(HttpStatus.NO_CONTENT);
            }
            log.info("Success Response Body Returned: " + body);
            return body;
        } catch (ResourceAccessException e) {
            log.error("Error while fetching jobs using http client: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IllegalArgumentException e) {
            log.error("Error while fetching jobs using http client: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String getUrlForService(String service) {
        return uri + ":" + port + service;
    }

}
