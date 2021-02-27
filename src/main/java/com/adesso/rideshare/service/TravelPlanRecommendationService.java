package com.adesso.rideshare.service;

import com.adess.rideshare.model.TravelPlanModel;
import com.adess.rideshare.model.TravelPlanSearchModel;

import java.util.List;

public interface TravelPlanRecommendationService {

    List<TravelPlanModel> search(TravelPlanSearchModel searchModel);
}
