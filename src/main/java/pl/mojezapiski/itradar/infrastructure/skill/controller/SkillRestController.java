package pl.mojezapiski.itradar.infrastructure.skill.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mojezapiski.itradar.domain.job.JobFacade;
import pl.mojezapiski.itradar.domain.job.dto.JobResponseDto;
import pl.mojezapiski.itradar.domain.job.dto.SkillOccurrencesDto;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/skills")
public class SkillRestController {

    private final JobFacade jobFacade;

    @GetMapping("/top")
    public ResponseEntity<List<SkillOccurrencesDto>> topSkillOccurrences() {
        List<SkillOccurrencesDto> skillOccurrences = jobFacade.topSkillOccurrences();
        return ResponseEntity.ok(skillOccurrences);
    }

    @GetMapping("/top/{skill}")
    public ResponseEntity<List<SkillOccurrencesDto>> findOfferById(@PathVariable String skill) {
        List<SkillOccurrencesDto> offerById = jobFacade.topSkillOccurrencesRelatedTo(skill);
        return ResponseEntity.ok(offerById);
    }

}
