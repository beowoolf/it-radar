package pl.mojezapiski.itradar.domain.job;

import lombok.RequiredArgsConstructor;
import pl.mojezapiski.itradar.domain.job.dto.SkillOccurrencesDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JobService {

    private final JobFetchable jobOfferFetcher;
    private final JobRepository jobRepository;
    private final SkillRepository skillRepository;

    List<Job> fetchAllJobsAndSaveAllIfNotExists() {
        List<Job> jobOffers = fetchJobs();
        final List<Job> offers = filterNotExistingOffers(jobOffers);
        for (Job job : offers) {
            for (Skill skill : job.getSkills()) {
                Optional<Skill> optionalSkill = skillRepository.getByName(skill.getName());
                if (optionalSkill.isPresent())
                    skill.setId(optionalSkill.get().getId());
                else {
                    Skill saveSkill = skillRepository.save(skill);
                    skill.setId(saveSkill.getId());
                }
            }
        }
        return jobRepository.saveAll(offers);
    }

    public List<SkillOccurrencesDto> topSkillOccurrences() {
        return skillRepository.topSkillOccurrences();
    }

    public List<SkillOccurrencesDto> topSkillOccurrencesRelatedTo(String skill) {
        return skillRepository.topSkillOccurrencesRelatedTo(skill);
    }

    private List<Job> fetchJobs() {
        return jobOfferFetcher.fetchJobs()
                .stream()
                .map(JobMapper::mapFromJobResponseToJob)
                .toList();
    }

    private List<Job> filterNotExistingOffers(List<Job> jobOffers) {
        return jobOffers.stream()
                .filter(offerDto -> !offerDto.getUrl().isEmpty())
                .filter(offerDto -> !jobRepository.existsByUrl(offerDto.getUrl()))
                .collect(Collectors.toList());
    }

}
