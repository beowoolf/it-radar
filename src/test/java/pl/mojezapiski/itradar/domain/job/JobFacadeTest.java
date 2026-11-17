package pl.mojezapiski.itradar.domain.job;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DuplicateKeyException;
import pl.mojezapiski.itradar.domain.job.dto.JobRequestDto;
import pl.mojezapiski.itradar.domain.job.dto.JobResponse;
import pl.mojezapiski.itradar.domain.job.dto.JobResponseDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

public class JobFacadeTest {
    public ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void should_fetch_from_jobs_from_remote_and_save_all_jobs_when_repository_is_empty() throws JsonProcessingException {
        // given
        JobFacade jobFacade = new JobFacadeTestConfiguration().jobFacadeForTests();
        assertThat(jobFacade.findAllJobs()).isEmpty();

        // when
        List<JobResponseDto> result = jobFacade.fetchAllJobsAndSaveAllIfNotExists();

        // then
        assertThat(result).hasSize(6);
    }

    @Test
    public void should_save_only_2_jobs_when_repository_had_4_added_with_job_urls() throws JsonProcessingException {
        // given
        String json1 = """
                {
                    "title": "Backend Developer",
                    "street": "Centrum",
                    "city": "Wrocław",
                    "country_code": "PL",
                    "address_text": "Centrum, Wrocław",
                    "marker_icon": "go",
                    "workplace_type": "remote",
                    "company_name": "Gamesture Sp. z o.o.",
                    "company_url": "http://gamesture.com",
                    "company_size": "80",
                    "experience_level": "mid",
                    "latitude": "51.1078852",
                    "longitude": "17.0385376",
                    "published_at": "2023-04-19T11:00:14.909Z",
                    "remote_interview": true,
                    "open_to_hire_ukrainians": true,
                    "id": "gamesture-sp-z-o-o-backend-developer-wroclaw",
                    "display_offer": false,
                    "employment_types": [
                        {
                            "type": "permanent",
                            "salary": {
                                "from": 8000,
                                "to": 16000,
                                "currency": "pln"
                            }
                        }
                    ],
                    "company_logo_url": "https://bucket.justjoin.it/offers/company_logos/thumb/4d9a5369b294d3b34782e26178c82a78836d3073.png?1680604238",
                    "skills": [
                        {
                            "name": "Python",
                            "level": 1
                        },
                        {
                            "name": "SQL",
                            "level": 3
                        },
                        {
                            "name": "Golang",
                            "level": 3
                        }
                    ],
                    "remote": true,
                    "multilocation": [
                        {
                            "city": "Wrocław",
                            "street": "Centrum",
                            "slug": "gamesture-sp-z-o-o-backend-developer-wroclaw"
                        }
                    ],
                    "way_of_apply": "redirect"
                }""";
        JobResponse parsedJson11 = objectMapper.readValue(json1, new TypeReference<>() {
        });
        parsedJson11.setId(parsedJson11.getId() + "1");
        JobResponse parsedJson12 = objectMapper.readValue(json1, new TypeReference<>() {
        });
        parsedJson12.setId(parsedJson12.getId() + "2");
        JobResponse parsedJson13 = objectMapper.readValue(json1, new TypeReference<>() {
        });
        parsedJson13.setId(parsedJson13.getId() + "3");
        JobResponse parsedJson14 = objectMapper.readValue(json1, new TypeReference<>() {
        });
        parsedJson14.setId(parsedJson14.getId() + "4");
        JobResponse parsedJson15 = objectMapper.readValue(json1, new TypeReference<>() {
        });
        parsedJson15.setId(parsedJson15.getId() + "5");
        JobResponse parsedJson16 = objectMapper.readValue(json1, new TypeReference<>() {
        });
        parsedJson16.setId(parsedJson16.getId() + "6");
        String json2 = """
                {
                    "title": "Backend Developer",
                    "street": "Centrum",
                    "city": "Wrocław",
                    "country_code": "PL",
                    "address_text": "Centrum, Wrocław",
                    "marker_icon": "go",
                    "workplace_type": "remote",
                    "company_name": "Gamesture Sp. z o.o.",
                    "company_url": "http://gamesture.com",
                    "company_size": "80",
                    "experience_level": "mid",
                    "latitude": "51.1078852",
                    "longitude": "17.0385376",
                    "published_at": "2023-04-19T11:00:14.909Z",
                    "remote_interview": true,
                    "open_to_hire_ukrainians": true,
                    "id": "https://justjoin.it/offers/gamesture-sp-z-o-o-backend-developer-wroclaw",
                    "display_offer": false,
                    "employment_types": [
                        {
                            "type": "permanent",
                            "salary": {
                                "from": 8000,
                                "to": 16000,
                                "currency": "pln"
                            }
                        }
                    ],
                    "company_logo_url": "https://bucket.justjoin.it/offers/company_logos/thumb/4d9a5369b294d3b34782e26178c82a78836d3073.png?1680604238",
                    "skills": [
                        {
                            "name": "Python",
                            "level": 1
                        },
                        {
                            "name": "SQL",
                            "level": 3
                        },
                        {
                            "name": "Golang",
                            "level": 3
                        }
                    ],
                    "remote": true,
                    "multilocation": [
                        {
                            "city": "Wrocław",
                            "street": "Centrum",
                            "slug": "gamesture-sp-z-o-o-backend-developer-wroclaw"
                        }
                    ],
                    "way_of_apply": "redirect"
                }""";
        JobRequestDto parsedJson21 = objectMapper.readValue(json2, new TypeReference<>() {
        });
        parsedJson21.setId(parsedJson21.getId() + "1");
        JobRequestDto parsedJson22 = objectMapper.readValue(json2, new TypeReference<>() {
        });
        parsedJson22.setId(parsedJson22.getId() + "2");
        JobRequestDto parsedJson23 = objectMapper.readValue(json2, new TypeReference<>() {
        });
        parsedJson23.setId(parsedJson23.getId() + "3");
        JobRequestDto parsedJson24 = objectMapper.readValue(json2, new TypeReference<>() {
        });
        parsedJson24.setId(parsedJson24.getId() + "4");
        JobFacade jobFacade = new JobFacadeTestConfiguration(
                List.of(
                        parsedJson11,
                        parsedJson12,
                        parsedJson13,
                        parsedJson14,
                        parsedJson15,
                        parsedJson16
                )
        ).jobFacadeForTests();
        jobFacade.saveJob(parsedJson21);
        jobFacade.saveJob(parsedJson22);
        jobFacade.saveJob(parsedJson23);
        jobFacade.saveJob(parsedJson24);
        assertThat(jobFacade.findAllJobs()).hasSize(4);

        // when
        List<JobResponseDto> response = jobFacade.fetchAllJobsAndSaveAllIfNotExists();

        // then
        assertThat(List.of(
                        response.get(0).getUrl(),
                        response.get(1).getUrl()
                )
        ).containsExactlyInAnyOrder("https://justjoin.it/offers/" + parsedJson15.getId(), "https://justjoin.it/offers/" + parsedJson16.getId());
    }

    @Test
    public void should_save_4_jobs_when_there_are_no_jobs_in_database() throws JsonProcessingException {
        // given
        String json = """
                {
                    "title": "Backend Developer",
                    "street": "Centrum",
                    "city": "Wrocław",
                    "country_code": "PL",
                    "address_text": "Centrum, Wrocław",
                    "marker_icon": "go",
                    "workplace_type": "remote",
                    "company_name": "Gamesture Sp. z o.o.",
                    "company_url": "http://gamesture.com",
                    "company_size": "80",
                    "experience_level": "mid",
                    "latitude": "51.1078852",
                    "longitude": "17.0385376",
                    "published_at": "2023-04-19T11:00:14.909Z",
                    "remote_interview": true,
                    "open_to_hire_ukrainians": true,
                    "id": "http://gamesture.com/offers/gamesture-sp-z-o-o-backend-developer-wroclaw",
                    "display_offer": false,
                    "employment_types": [
                        {
                            "type": "permanent",
                            "salary": {
                                "from": 8000,
                                "to": 16000,
                                "currency": "pln"
                            }
                        }
                    ],
                    "company_logo_url": "https://bucket.justjoin.it/offers/company_logos/thumb/4d9a5369b294d3b34782e26178c82a78836d3073.png?1680604238",
                    "skills": [
                        {
                            "name": "Python",
                            "level": 1
                        },
                        {
                            "name": "SQL",
                            "level": 3
                        },
                        {
                            "name": "Golang",
                            "level": 3
                        }
                    ],
                    "remote": true,
                    "multilocation": [
                        {
                            "city": "Wrocław",
                            "street": "Centrum",
                            "slug": "gamesture-sp-z-o-o-backend-developer-wroclaw"
                        }
                    ],
                    "way_of_apply": "redirect"
                }""";
        JobRequestDto parsedJson1 = objectMapper.readValue(json, new TypeReference<>() {
        });
        parsedJson1.setId(parsedJson1.getId() + "1");
        JobRequestDto parsedJson2 = objectMapper.readValue(json, new TypeReference<>() {
        });
        parsedJson2.setId(parsedJson2.getId() + "2");
        JobRequestDto parsedJson3 = objectMapper.readValue(json, new TypeReference<>() {
        });
        parsedJson3.setId(parsedJson3.getId() + "3");
        JobRequestDto parsedJson4 = objectMapper.readValue(json, new TypeReference<>() {
        });
        parsedJson4.setId(parsedJson4.getId() + "4");
        JobFacade jobFacade = new JobFacadeTestConfiguration(List.of()).jobFacadeForTests();

        // when
        jobFacade.saveJob(parsedJson1);
        jobFacade.saveJob(parsedJson2);
        jobFacade.saveJob(parsedJson3);
        jobFacade.saveJob(parsedJson4);

        // then
        assertThat(jobFacade.findAllJobs()).hasSize(4);
    }

    @Test
    public void should_find_job_by_id_when_job_was_saved() throws JsonProcessingException {
        // given
        String json = """
                {
                    "title": "Backend Developer",
                    "street": "Centrum",
                    "city": "Wrocław",
                    "country_code": "PL",
                    "address_text": "Centrum, Wrocław",
                    "marker_icon": "go",
                    "workplace_type": "remote",
                    "company_name": "Gamesture Sp. z o.o.",
                    "company_url": "http://gamesture.com",
                    "company_size": "80",
                    "experience_level": "mid",
                    "latitude": "51.1078852",
                    "longitude": "17.0385376",
                    "published_at": "2023-04-19T11:00:14.909Z",
                    "remote_interview": true,
                    "open_to_hire_ukrainians": true,
                    "id": "http://gamesture.com/offers/gamesture-sp-z-o-o-backend-developer-wroclaw",
                    "display_offer": false,
                    "employment_types": [
                        {
                            "type": "permanent",
                            "salary": {
                                "from": 8000,
                                "to": 16000,
                                "currency": "pln"
                            }
                        }
                    ],
                    "company_logo_url": "https://bucket.justjoin.it/offers/company_logos/thumb/4d9a5369b294d3b34782e26178c82a78836d3073.png?1680604238",
                    "skills": [
                        {
                            "name": "Python",
                            "level": 1
                        },
                        {
                            "name": "SQL",
                            "level": 3
                        },
                        {
                            "name": "Golang",
                            "level": 3
                        }
                    ],
                    "remote": true,
                    "multilocation": [
                        {
                            "city": "Wrocław",
                            "street": "Centrum",
                            "slug": "gamesture-sp-z-o-o-backend-developer-wroclaw"
                        }
                    ],
                    "way_of_apply": "redirect"
                }""";
        JobRequestDto parsedJson = objectMapper.readValue(json, new TypeReference<>() {
        });
        JobFacade jobFacade = new JobFacadeTestConfiguration(List.of()).jobFacadeForTests();
        JobResponseDto jobResponseDto = jobFacade.saveJob(parsedJson);
        // when
        JobResponseDto jobById = jobFacade.findJobById(jobResponseDto.getId());

        // then
        assertThat(jobById).isEqualTo(JobResponseDto.builder()
                .id(jobById.getId())
                .url(jobResponseDto.getUrl())
                .title(jobResponseDto.getTitle())
                .city(jobResponseDto.getCity())
                .street(jobResponseDto.getStreet())
                .addressText(jobResponseDto.getCity())
                .countryCode(jobResponseDto.getCountryCode())
                .addressText(jobResponseDto.getAddressText())
                .markerIcon(jobResponseDto.getMarkerIcon())
                .workplaceType(jobResponseDto.getWorkplaceType())
                .companyName(jobResponseDto.getCompanyName())
                .companyUrl(jobResponseDto.getCompanyUrl())
                .companySize(jobResponseDto.getCompanySize())
                .experienceLevel(jobResponseDto.getExperienceLevel())
                .latitude(jobResponseDto.getLatitude())
                .longitude(jobResponseDto.getLongitude())
                .publishedAt(jobResponseDto.getPublishedAt())
                .remoteInterview(jobResponseDto.getRemoteInterview())
                .openToHireUkrainians(jobResponseDto.getOpenToHireUkrainians())
                .displayOffer(jobResponseDto.getDisplayOffer())
                .employmentTypes(jobResponseDto.getEmploymentTypes())
                .companyLogoUrl(jobResponseDto.getCompanyLogoUrl())
                .skills(jobResponseDto.getSkills())
                .remote(jobResponseDto.getRemote())
                .multilocation(jobResponseDto.getMultilocation())
                .wayOfApply(jobResponseDto.getWayOfApply()).build()
        );
    }

    @Test
    public void should_throw_not_found_exception_when_job_not_found() {
        // given
        JobFacade jobFacade = new JobFacadeTestConfiguration(List.of()).jobFacadeForTests();
        assertThat(jobFacade.findAllJobs()).isEmpty();

        // when
        Throwable thrown = catchThrowable(() -> jobFacade.findJobById(100L));

        // then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(JobNotFoundException.class)
                .hasMessage("Job with id 100 not found");
    }

    @Test
    public void should_throw_duplicate_key_exception_when_with_job_url_exists() throws JsonProcessingException {
        // given
        String json = """
                {
                    "title": "Backend Developer",
                    "street": "Centrum",
                    "city": "Wrocław",
                    "country_code": "PL",
                    "address_text": "Centrum, Wrocław",
                    "marker_icon": "go",
                    "workplace_type": "remote",
                    "company_name": "Gamesture Sp. z o.o.",
                    "company_url": "http://gamesture.com",
                    "company_size": "80",
                    "experience_level": "mid",
                    "latitude": "51.1078852",
                    "longitude": "17.0385376",
                    "published_at": "2023-04-19T11:00:14.909Z",
                    "remote_interview": true,
                    "open_to_hire_ukrainians": true,
                    "id": "http://gamesture.com/offers/gamesture-sp-z-o-o-backend-developer-wroclaw",
                    "display_offer": false,
                    "employment_types": [
                        {
                            "type": "permanent",
                            "salary": {
                                "from": 8000,
                                "to": 16000,
                                "currency": "pln"
                            }
                        }
                    ],
                    "company_logo_url": "https://bucket.justjoin.it/offers/company_logos/thumb/4d9a5369b294d3b34782e26178c82a78836d3073.png?1680604238",
                    "skills": [
                        {
                            "name": "Python",
                            "level": 1
                        },
                        {
                            "name": "SQL",
                            "level": 3
                        },
                        {
                            "name": "Golang",
                            "level": 3
                        }
                    ],
                    "remote": true,
                    "multilocation": [
                        {
                            "city": "Wrocław",
                            "street": "Centrum",
                            "slug": "gamesture-sp-z-o-o-backend-developer-wroclaw"
                        }
                    ],
                    "way_of_apply": "redirect"
                }""";
        JobRequestDto parsedJson = objectMapper.readValue(json, new TypeReference<>() {
        });
        JobFacade jobFacade = new JobFacadeTestConfiguration(List.of()).jobFacadeForTests();
        JobResponseDto jobResponseDto = jobFacade.saveJob(parsedJson);
        Long savedId = jobResponseDto.getId();
        assertThat(jobFacade.findJobById(savedId).getId()).isEqualTo(savedId);
        String json2 = """
                {
                	"title": "Backend Developer",
                	"street": "Centrum",
                	"city": "Katowice",
                	"country_code": "PL",
                	"address_text": "Centrum, Katowice",
                	"marker_icon": "go",
                	"workplace_type": "remote",
                	"company_name": "Gamesture Sp. z o.o.",
                	"company_url": "http://gamesture.com",
                	"company_size": "80",
                	"experience_level": "mid",
                	"latitude": "50.2648919",
                	"longitude": "19.0237815",
                	"published_at": "2023-04-19T11:00:14.909Z",
                	"remote_interview": true,
                	"open_to_hire_ukrainians": true,
                	"id": "http://gamesture.com/offers/gamesture-sp-z-o-o-backend-developer-wroclaw",
                	"display_offer": false,
                	"employment_types": [
                		{
                			"type": "permanent",
                			"salary": {
                				"from": 8000,
                				"to": 16000,
                				"currency": "pln"
                			}
                		}
                	],
                	"company_logo_url": "https://bucket.justjoin.it/offers/company_logos/thumb/9f1d514b22d4560f196b98981f9752c5d5cc44ea.png?1680604189",
                	"skills": [
                		{
                			"name": "Python",
                			"level": 1
                		},
                		{
                			"name": "SQL",
                			"level": 3
                		},
                		{
                			"name": "Golang",
                			"level": 3
                		}
                	],
                	"remote": true,
                	"multilocation": [
                		{
                			"city": "Katowice",
                			"street": "Centrum",
                			"slug": "gamesture-sp-z-o-o-backend-developer-katowice"
                		}
                	],
                	"way_of_apply": "redirect"
                }""";
        JobRequestDto parsedJson2 = objectMapper.readValue(json2, new TypeReference<>() {
        });
        // when
        Throwable thrown = catchThrowable(() -> jobFacade.saveJob(parsedJson2));

        // then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(DuplicateKeyException.class)
                .hasMessage("Job with url [http://gamesture.com/offers/gamesture-sp-z-o-o-backend-developer-wroclaw] already exists");
    }

}
