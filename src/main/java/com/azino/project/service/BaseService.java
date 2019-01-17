package com.azino.project.service;

import com.azino.project.model.IModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public class BaseService<Model extends IModel, Repository extends CrudRepository<Model, Long>>
        implements IService<Model> {

    protected Repository repository;

    public BaseService(Repository repository){
        this.repository = repository;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Model save(Model entity) {
        return (Model)repository.save(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterable<Model> saveAll(Iterable<Model> entities) {
        return (Iterable<Model>) repository.saveAll(entities);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Optional<Model> findById(Long id) {
        return (Optional<Model>) repository.findById(id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterable<Model> findAll() {
        return (Iterable<Model>) repository.findAll();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterable<Model> findAllById(Iterable<Long> ids) {
        return (Iterable<Model>) repository.findAllById(ids);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void delete(Model entity) {
        repository.delete(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void deleteAll(Iterable<Model> entities) {
        repository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
