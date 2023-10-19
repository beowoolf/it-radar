package pl.mojezapiski.itradar.domain.job;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobFacadeConfiguration {

    @Bean
    JobFacade jobFacade(JobFetchable jobFetchable, JobRepository jobRepository, SkillRepository skillRepository) {
        JobService offerService = new JobService(jobFetchable, jobRepository, skillRepository);
        return new JobFacade(jobRepository, offerService);
    }

}
