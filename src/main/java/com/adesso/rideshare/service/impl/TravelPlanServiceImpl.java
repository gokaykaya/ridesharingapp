package com.adesso.rideshare.service.impl;

import com.adess.rideshare.model.TravelPlanModel;
import com.adess.rideshare.model.TravelPlanSearchModel;
import com.adesso.rideshare.repository.CrudRepository;
import com.adesso.rideshare.repository.impl.TravelPlanRepository;
import com.adesso.rideshare.service.TravelPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class TravelPlanServiceImpl implements TravelPlanService {

    @Autowired
    private CrudRepository<TravelPlanModel> repository;

    @Override
    public void create(TravelPlanModel travelPlanModel) {
        this.repository.save(travelPlanModel);
    }

    @Override
    public TravelPlanModel get(UUID travelPlanId) {
        return this.repository.findOne(travelPlanId);
    }

    @Override
    public List<TravelPlanModel> list() {
        return (List<TravelPlanModel>) this.repository.findAll();
    }

    @Override
    public void publishTravelPlan(UUID travelPlanId) {
        TravelPlanModel travelPlan = this.repository.findOne(travelPlanId);
        if(travelPlan == null) {
            throw new NoSuchElementException("Travel plan has not been found.");
        }
        travelPlan.setPublished(true);
        this.repository.save(travelPlan);
    }

    @Override
    public void banTravelPlan(UUID travelPlanId) {
        TravelPlanModel travelPlan = this.repository.findOne(travelPlanId);
        travelPlan.setPublished(false);
        this.repository.save(travelPlan);
    }

    @Override
    public List<TravelPlanModel> search(TravelPlanSearchModel searchModel) {
        return (List<TravelPlanModel>) this.repository.findByDepartureAndDestination(searchModel.getDeparture(), searchModel.getDestination());
    }

    @Override
    public boolean sendParticipationRequest(UUID travelPlanId, int count) {
        TravelPlanModel travelPlan = this.repository.findOne(travelPlanId);
        if(!travelPlan.isPublished()) {
            return false;
        }

        int seatCount = travelPlan.getSeatCount();
        int availableSeatCount = travelPlan.getAvailableSeatCount();
        if(availableSeatCount - count >= 0) {
            travelPlan.setAvailableSeatCount(availableSeatCount - count);
            this.repository.save(travelPlan);
            return true;
        }

        return false;
    }
}
