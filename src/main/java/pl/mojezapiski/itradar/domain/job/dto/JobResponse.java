package pl.mojezapiski.itradar.domain.job.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class JobResponse {

    @NotNull(message = "{title.not.null}")
    @NotEmpty(message = "{title.not.empty}")
    private String title;
    @NotNull(message = "{street.not.null}")
    @NotEmpty(message = "{street.not.empty}")
    private String street;
    @NotNull(message = "{city.not.null}")
    @NotEmpty(message = "{city.not.empty}")
    private String city;
    @JsonProperty("country_code")
    @NotNull(message = "{country_code.not.null}")
    @NotEmpty(message = "{country_code.not.empty}")
    private String countryCode;
    @JsonProperty("address_text")
    @NotNull(message = "{address_text.not.null}")
    @NotEmpty(message = "{address_text.not.empty}")
    private String addressText;
    @JsonProperty("marker_icon")
    @NotNull(message = "{marker_icon.not.null}")
    @NotEmpty(message = "{marker_icon.not.empty}")
    private String markerIcon;
    @JsonProperty("workplace_type")
    @NotNull(message = "{workplace_type.not.null}")
    @NotEmpty(message = "{workplace_type.not.empty}")
    private String workplaceType;
    @JsonProperty("company_name")
    @NotNull(message = "{company_name.not.null}")
    @NotEmpty(message = "{company_name.not.empty}")
    private String companyName;
    @JsonProperty("company_url")
    @NotNull(message = "{company_url.not.null}")
    @NotEmpty(message = "{company_url.not.empty}")
    private String companyUrl;
    @JsonProperty("company_size")
    @NotNull(message = "{company_size.not.null}")
    @NotEmpty(message = "{company_size.not.empty}")
    private String companySize;
    @JsonProperty("experience_level")
    @NotNull(message = "{experience_level.not.null}")
    @NotEmpty(message = "{experience_level.not.empty}")
    private String experienceLevel;
    @NotNull(message = "{latitude.not.null}")
    @NotEmpty(message = "{latitude.not.empty}")
    private String latitude;
    @NotNull(message = "{longitude.not.null}")
    @NotEmpty(message = "{longitude.not.empty}")
    private String longitude;
    @JsonProperty("published_at")
    @NotNull(message = "{published_at.not.null}")
    @NotEmpty(message = "{published_at.not.empty}")
    private String publishedAt;
    @JsonProperty("remote_interview")
    @NotNull(message = "{remote_interview.not.null}")
    private Boolean remoteInterview;
    @JsonProperty("open_to_hire_ukrainians")
    @NotNull(message = "{open_to_hire_ukrainians.not.null}")
    private Boolean openToHireUkrainians;
    @NotNull(message = "{id.not.null}")
    @NotEmpty(message = "{id.not.empty}")
    private String id;
    @JsonProperty("display_offer")
    @NotNull(message = "{display_offer.not.null}")
    private Boolean displayOffer;
    @JsonProperty("employment_types")
    @NotNull(message = "{employment_types.not.null}")
    private List<EmploymentType> employmentTypes = new ArrayList<EmploymentType>();
    @JsonProperty("company_logo_url")
    @NotNull(message = "{company_logo_url.not.null}")
    @NotEmpty(message = "{company_logo_url.not.empty}")
    private String companyLogoUrl;
    @NotNull(message = "{skills.not.null}")
    private List<Skill> skills = new ArrayList<Skill>();
    @NotNull(message = "{remote.not.null}")
    private Boolean remote;
    @NotNull(message = "{multilocation.not.null}")
    private List<Multilocation> multilocation = new ArrayList<Multilocation>();
    @JsonProperty("way_of_apply")
    @NotNull(message = "{way_of_apply.not.null}")
    @NotEmpty(message = "{way_of_apply.not.empty}")
    private String wayOfApply;

}
