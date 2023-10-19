package pl.mojezapiski.itradar.integration.api;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import pl.mojezapiski.itradar.infrastructure.api.ApiValidationErrorDto;
import pl.mojezapiski.itradar.integration.BaseIntegrationTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ApiValidationFailedIntegrationTest extends BaseIntegrationTest {

    @Test
    public void should_return_400_bad_request_and_validation_message_when_empty_and_null_in_job_save_request() throws Exception {
        // given
        String jobToCreateJson = """
                {
                    "title": "",
                    "street": "",
                    "city": "",
                    "country_code": "",
                    "address_text": "",
                    "marker_icon": "",
                    "workplace_type": "",
                    "company_name": "",
                    "company_url": "",
                    "company_size": "",
                    "experience_level": "",
                    "latitude": "",
                    "longitude": "",
                    "published_at": "",
                    "id": "",
                    "employment_types": [
                        {
                            "type": "",
                            "salary": {
                                "currency": ""
                            }
                        }
                    ],
                    "company_logo_url": "",
                    "skills": [
                        {
                            "name": ""
                        },
                        {
                            "name": ""
                        },
                        {
                            "name": ""
                        }
                    ],
                    "multilocation": [
                        {
                            "city": "",
                            "street": "",
                            "slug": ""
                        }
                    ],
                    "way_of_apply": ""
                }
                """;
        // when
        ResultActions perform = mockMvc.perform(post("/jobs")
                .content(jobToCreateJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
        );
        // then
        MvcResult mvcResult = perform.andExpect(status().isBadRequest()).andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        ApiValidationErrorDto result = objectMapper.readValue(json, ApiValidationErrorDto.class);
        assertThat(result.messages()).containsExactlyInAnyOrder(
                "title must not be empty",
                "company_name must not be empty",
                "latitude must not be empty",
                "address_text must not be empty",
                "company_logo_url must not be empty",
                "company_url must not be empty",
                "experience_level must not be empty",
                "published_at must not be empty",
                "marker_icon must not be empty",
                "company_size must not be empty",
                "display_offer must not be null",
                "way_of_apply must not be empty",
                "id must not be empty",
                "workplace_type must not be empty",
                "remote_interview must not be null",
                "city must not be empty",
                "remote must not be null",
                "longitude must not be empty",
                "open_to_hire_ukrainians must not be null",
                "street must not be empty",
                "country_code must not be empty");
    }

}
