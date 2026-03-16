package pl.mojezapiski.itradar.domain.job.dto;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class JobResponseDto implements Serializable {

    private Long id;
    private String url;
    private String title;
    private String street;
    private String city;
    private String countryCode;
    private String addressText;
    private String markerIcon;
    private String workplaceType;
    private String companyName;
    private String companyUrl;
    private String companySize;
    private String experienceLevel;
    private String latitude;
    private String longitude;
    private String publishedAt;
    private Boolean remoteInterview;
    private Boolean openToHireUkrainians;
    private Boolean displayOffer;
    private List<EmploymentType> employmentTypes = new ArrayList<EmploymentType>();
    private String companyLogoUrl;
    private List<Skill> skills = new ArrayList<Skill>();
    private Boolean remote;
    private List<Multilocation> multilocation = new ArrayList<Multilocation>();
    private String wayOfApply;

}
