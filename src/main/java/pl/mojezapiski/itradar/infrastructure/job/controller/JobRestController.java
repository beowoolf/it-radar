package pl.mojezapiski.itradar.infrastructure.job.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mojezapiski.itradar.domain.job.JobFacade;
import pl.mojezapiski.itradar.domain.job.dto.JobRequestDto;
import pl.mojezapiski.itradar.domain.job.dto.JobResponseDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class JobRestController {

    private final JobFacade jobFacade;

    @GetMapping
    public ResponseEntity<List<JobResponseDto>> findAllOffers() {
        List<JobResponseDto> allOffers = jobFacade.findAllJobs();
        return ResponseEntity.ok(allOffers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponseDto> findOfferById(@PathVariable Long id) {
        JobResponseDto offerById = jobFacade.findJobById(id);
        return ResponseEntity.ok(offerById);
    }

    @PostMapping
    public ResponseEntity<JobResponseDto> addOffer(@RequestBody @Valid JobRequestDto offerDto) {
        JobResponseDto offerResponseDto = jobFacade.saveJob(offerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(offerResponseDto);
    }

}
