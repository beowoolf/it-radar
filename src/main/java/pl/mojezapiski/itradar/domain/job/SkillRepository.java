package pl.mojezapiski.itradar.domain.job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.mojezapiski.itradar.domain.job.dto.SkillOccurrencesDto;

import java.util.List;
import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    Optional<Skill> getByName(String name);

    @Query("SELECT new pl.mojezapiski.itradar.domain.job.dto.SkillOccurrencesDto(s.name, COUNT(s.name)) " +
            "FROM Skill s " +
            "JOIN s.jobs job " +
            "GROUP BY s.name " +
            "ORDER BY COUNT(s.name) DESC")
    List<SkillOccurrencesDto> topSkillOccurrences();

    @Query("SELECT new pl.mojezapiski.itradar.domain.job.dto.SkillOccurrencesDto(s.name, COUNT(s.name)) " +
            "FROM Skill s " +
            "JOIN s.jobs job " +
            "WHERE s.name <> :skill AND job.id IN (" +
            "    SELECT j.id " +
            "    FROM Skill sk " +
            "    JOIN sk.jobs j " +
            "    WHERE sk.name = :skill" +
            ") " +
            "GROUP BY s.name " +
            "ORDER BY COUNT(s.name) DESC")
    List<SkillOccurrencesDto> topSkillOccurrencesRelatedTo(String skill);
}
