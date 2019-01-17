package com.azino.project.service;

import com.azino.project.model.IModel;

import java.util.Optional;

public interface IService<Model extends IModel> {

    Model save(Model entity);

    Iterable<Model> saveAll(Iterable<Model> entities);

    Optional<Model> findById(Long id);

    boolean existsById(Long id);

    Iterable<Model> findAll();

    Iterable<Model> findAllById(Iterable<Long> ids);

    long count();

    void deleteById(Long id);

    void delete(Model entity);

    void deleteAll(Iterable<Model> entities);

    void deleteAll();
}
