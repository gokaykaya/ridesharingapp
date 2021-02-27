package com.adesso.rideshare.repository.impl;

import com.adess.rideshare.model.City;
import com.adess.rideshare.model.TravelPlanModel;
import com.adesso.rideshare.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.Date;
import java.util.stream.Collectors;

@Repository
public class TravelPlanRepository implements CrudRepository<TravelPlanModel> {

    private Collection<TravelPlanModel> travelPlans;

    public TravelPlanRepository() {
        this.travelPlans = new ArrayList<>();
        TravelPlanModel tp1 = new TravelPlanModel();
        tp1.setId(UUID.randomUUID());
        tp1.setDeparture("Ankara");
        tp1.setDestination("İstanbul");
        tp1.setDescription("Randomly Created");
        tp1.setDepartDate(new Date());
        tp1.setPublished(false);
        tp1.setSeatCount(4);
        tp1.setAvailableSeatCount(3);
        travelPlans.add(tp1);
    }

    @Override
    public TravelPlanModel findOne(UUID id) {
        return this.travelPlans.stream().filter(t -> t.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public TravelPlanModel save(TravelPlanModel entity) {
        TravelPlanModel travelPlanModel = this.findOne(entity.getId());
        UUID uuid;
        if(travelPlanModel != null) {
            uuid = travelPlanModel.getId();
            this.travelPlans.remove(travelPlanModel);
        } else {
            uuid = UUID.randomUUID();
        }

        entity.setId(uuid);
        this.travelPlans.add(entity);

        return entity;
    }

    @Override
    public Iterable<TravelPlanModel> findAll() {
        return this.travelPlans;
    }

    @Override
    public Iterable<TravelPlanModel> findAllPublished() {
        return this.travelPlans.stream().filter(x -> x.isPublished()).collect(Collectors.toList());
    }

    @Override
    public Iterable<TravelPlanModel> findByDepartureAndDestination(String departure, String destination) {
        return this.travelPlans.stream().filter(t -> t.getDeparture().equals(departure) && t.getDestination().equals(destination) && t.isPublished()).collect(Collectors.toList());
    }
}
