package com.adesso.rideshare.controller;

import com.adess.rideshare.model.TravelPlanModel;
import com.adess.rideshare.model.TravelPlanSearchModel;
import com.adesso.rideshare.service.TravelPlanRecommendationService;
import com.adesso.rideshare.service.TravelPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/travelPlans")
public class TravelPlanRestController {

    @Autowired
    private TravelPlanService service;

    @Autowired
    private TravelPlanRecommendationService recommendationService;

    @GetMapping("")
    public List<TravelPlanModel> getTravelPlans() {
        return this.service.list();
    }

    @GetMapping("/get/{id}")
    public TravelPlanModel getTravelPlanById(@PathVariable("id") UUID id) {
        return this.service.get(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTravelPlan(@RequestBody TravelPlanModel model) {
        this.service.create(model);
        return ResponseEntity.ok("Travel plan is created.");
    }

    @GetMapping("/publish/{id}")
    public ResponseEntity<String> publishTravelPlan(@PathVariable("id") UUID id) {
        this.service.publishTravelPlan(id);
        return ResponseEntity.ok("Travel plan is published.");
    }

    @RequestMapping(value="/hide/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> hideTravelPlan(@PathVariable("id") UUID id) {
        this.service.banTravelPlan(id);
        return ResponseEntity.ok("Travel plan is hid.");
    }

    @GetMapping("/search")
    public List<TravelPlanModel> searchTravelPlans(@RequestBody TravelPlanSearchModel searchModel) {
        return this.service.search(searchModel);
    }

    @GetMapping("/searchRecommendations")
    public List<TravelPlanModel> searchTravelPlansRecommendations(@RequestBody TravelPlanSearchModel searchModel) {
        return this.recommendationService.search(searchModel);
    }

    @GetMapping("/requestSeat/{id}/{count}")
    public ResponseEntity<String> requestSeatToPlans(@PathVariable("id") UUID id, @PathVariable("count") int count) {
        if(this.service.sendParticipationRequest(id, count)) {
            return ResponseEntity.ok("Your seat is reserved");
        }

        return ResponseEntity.badRequest().body("No available seats for selected plan.");
    }


}
