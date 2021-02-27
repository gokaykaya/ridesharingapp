package com.adesso.rideshare.repository;

import com.adess.rideshare.model.TravelPlanModel;

import java.util.List;
import java.util.UUID;

public interface CrudRepository<T> {

    T findOne(UUID id);

    T save(T entity);

    Iterable<T> findAll();

    Iterable<T> findAllPublished();

    Iterable<T> findByDepartureAndDestination(String departure, String destination);
}
