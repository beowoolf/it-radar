package pl.mojezapiski.itradar.domain.job;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jobs")
public class Job {

    @Id
    @JdbcTypeCode(SqlTypes.BIGINT)
    @Column(name = "job_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String url;
    private String title;
    private String street;
    private String city;
    private String countryCode;
    private String addressText;
    private String markerIcon;
    private String workplaceType;
    private String companyName;
    @Column(nullable = false, length = 2048)
    private String companyUrl;
    private String companySize;
    private String experienceLevel;
    private String latitude;
    private String longitude;
    private String publishedAt;
    private Boolean remoteInterview;
    private Boolean openToHireUkrainians;
    private Boolean displayOffer;
    private String companyLogoUrl;
    private Boolean remote;
    private String wayOfApply;

    @ManyToMany//(cascade = CascadeType.PERSIST)
    @JoinTable(name = "m2m_job_skill",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills = new HashSet<>();

}
