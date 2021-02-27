package com.adesso.rideshare.service.impl;

import com.adess.rideshare.model.*;
import com.adesso.rideshare.repository.impl.TravelPlanRepository;
import com.adesso.rideshare.service.TravelPlanRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class TravelPlanRecommendationServiceImpl implements TravelPlanRecommendationService {

    @Autowired
    private TravelPlanRepository repository;


    @Override
    public List<TravelPlanModel> search(TravelPlanSearchModel searchModel) {
        List<TravelPlanModel> allPublishedPlans = (List<TravelPlanModel>) this.repository.findAllPublished();

        List<TravelPlanModel> result = new ArrayList<>();
        Island island = Island.getInstance();
        for(TravelPlanModel travelPlan : allPublishedPlans) {
            if(isContainingPath(island, searchModel, travelPlan)) {
                result.add(travelPlan);
            }
        }

        return result;
    }

    public boolean isContainingPath(Island island, TravelPlanSearchModel searchModel, TravelPlanModel travelPlan) {
        LinkedList<City> queue = new LinkedList<>();
        boolean isSearchPathFound = false;

        City searchDeparture = null;
        City searchDestination = null;
        City planDeparture = null;
        City planDestination = null;

        for(City city : island.getCities()) {
            city.setState(CityState.UNVISITED);
            if(city.getName().equals(searchModel.getDeparture())) {
                searchDeparture = city;
            } else if (city.getName().equals(searchModel.getDestination())) {
                searchDestination = city;
            } else if (city.getName().equals(travelPlan.getDeparture())) {
                planDeparture = city;
            } else if (city.getName().equals(travelPlan.getDestination())) {
                planDestination = city;
            }
        }

        planDeparture.setState(CityState.VISITING);
        queue.add(planDeparture);
        City c;
        while(!queue.isEmpty()) {
            c = queue.remove();
            if(c != null) {
                for(City adjacent : c.getAdjacents()) {
                    if(adjacent.getState() == CityState.UNVISITED) {
                        if(adjacent == planDestination) {
                            return false;
                        } else {
                            if(adjacent == searchDeparture) {
                                isSearchPathFound = true;
                            } else if(adjacent == searchDestination) {
                                if(isSearchPathFound) {
                                    return true;
                                } else {
                                    return false;
                                }
                            }

                            adjacent.setState(CityState.VISITING);
                            queue.add(adjacent);
                        }
                    }
                }

                c.setState(CityState.VISITED);
            }
        }

        return false;
    }
}
