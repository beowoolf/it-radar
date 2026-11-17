package pl.mojezapiski.itradar.domain.job;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.StreamSupport;

public class InMemoryJobRepository implements JobRepository {

    Map<Long, Job> database = new ConcurrentHashMap<>();

    @Override
    public boolean existsByUrl(String getUrl) {
        long count = database.values()
                .stream()
                .filter(job -> job.getUrl().equals(getUrl))
                .count();
        return count == 1;
    }

    @Override
    public <S extends Job> S save(S entity) {
        if (database.values().stream().anyMatch(job -> job.getUrl().equals(entity.getUrl()))) {
            throw new DuplicateKeyException(String.format("Job with url [%s] already exists", entity.getUrl()));
        }
        Long id = new Random().nextLong();
        Job job = new Job(
                id,
                entity.getUrl(),
                entity.getTitle(),
                entity.getStreet(),
                entity.getCity(),
                entity.getCountryCode(),
                entity.getAddressText(),
                entity.getMarkerIcon(),
                entity.getWorkplaceType(),
                entity.getCompanyName(),
                entity.getCompanyUrl(),
                entity.getCompanySize(),
                entity.getExperienceLevel(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getPublishedAt(),
                entity.getRemoteInterview(),
                entity.getOpenToHireUkrainians(),
                entity.getDisplayOffer(),
                entity.getCompanyLogoUrl(),
                entity.getRemote(),
                entity.getWayOfApply(),
                new HashSet<>()
        );
        database.put(id, job);
        return (S) job;
    }

    @Override
    public <S extends Job> List<S> saveAll(Iterable<S> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::save)
                .toList();
    }

    @Override
    public Optional<Job> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Job> findAll() {
        return database.values().stream().toList();
    }

    @Override
    public List<Job> findAllById(Iterable<Long> longs) {
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
    public void delete(Job entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Job> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Job> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Job> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Job> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Job> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Job> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Job> entities) {
        JobRepository.super.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<Job> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Job getOne(Long aLong) {
        return null;
    }

    @Override
    public Job getById(Long aLong) {
        return null;
    }

    @Override
    public Job getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Job> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Job> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Job> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Job> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Job> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Job, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

}
