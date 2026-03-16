package pl.mojezapiski.itradar.domain.job;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

    boolean existsByUrl(String offerUrl);

}
