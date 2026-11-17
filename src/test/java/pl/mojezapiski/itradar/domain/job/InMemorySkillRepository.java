package pl.mojezapiski.itradar.domain.job;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import pl.mojezapiski.itradar.domain.job.dto.SkillOccurrencesDto;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class InMemorySkillRepository implements SkillRepository {

    Map<Long, Skill> database = new ConcurrentHashMap<>();

    @Override
    public Optional<Skill> getByName(String getName) {
        Optional<Skill> optionalSkill = database.values()
                .stream()
                .filter(skill -> skill.getName().equals(getName))
                .findFirst();
        return optionalSkill;
    }

    @Override
    public List<SkillOccurrencesDto> topSkillOccurrences() {
        return null;
    }

    @Override
    public List<SkillOccurrencesDto> topSkillOccurrencesRelatedTo(String skill) {
        return null;
    }

    @Override
    public <S extends Skill> S save(S entity) {
        if (database.values().stream().anyMatch(skill -> skill.getName().equals(entity.getName()))) {
            throw new DuplicateKeyException(String.format("Skill with name [%s] already exists", entity.getName()));
        }
        Long id = new Random().nextLong();
        Skill skill = new Skill(
                id,
                entity.getName(),
                new HashSet<>()
        );
        database.put(id, skill);
        return (S) skill;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Skill> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Skill> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Skill> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Skill getOne(Long aLong) {
        return null;
    }

    @Override
    public Skill getById(Long aLong) {
        return null;
    }

    @Override
    public Skill getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Skill> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Skill> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Skill> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Skill> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Skill> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Skill> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Skill, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Skill> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Skill> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Skill> findAll() {
        return null;
    }

    @Override
    public List<Skill> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Skill entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Skill> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Skill> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Skill> findAll(Pageable pageable) {
        return null;
    }
}
