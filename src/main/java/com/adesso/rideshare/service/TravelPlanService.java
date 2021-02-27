package com.adesso.rideshare.service;

import com.adess.rideshare.model.TravelPlanModel;
import com.adess.rideshare.model.TravelPlanSearchModel;

import java.util.List;
import java.util.UUID;

public interface TravelPlanService {

    void create(TravelPlanModel travelPlanModel);

    TravelPlanModel get(UUID travelPlanId);

    List<TravelPlanModel> list();

    void publishTravelPlan(UUID travelPlanId);

    void banTravelPlan(UUID travelPlanId);

    List<TravelPlanModel> search(TravelPlanSearchModel searchModel);

    boolean sendParticipationRequest(UUID travelPlanId, int count);
}
