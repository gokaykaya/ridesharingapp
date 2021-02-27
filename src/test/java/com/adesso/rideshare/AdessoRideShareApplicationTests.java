package com.adesso.rideshare;

import com.adess.rideshare.model.TravelPlanModel;
import com.adess.rideshare.model.TravelPlanSearchModel;
import com.adesso.rideshare.controller.TravelPlanRestController;
import com.adesso.rideshare.service.TravelPlanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class AdessoRideShareApplicationTests {

	@Autowired
	private TravelPlanRestController controller;

	@Autowired
	private TravelPlanService service;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	void contextServiceLoads() {
		assertThat(service).isNotNull();
	}

	@Test
	void getTravelPlanWorks() {
		assertThat(this.service.list()).isNotEmpty();
	}

	@Test
	void testSmth() {
		TravelPlanModel testTp = new TravelPlanModel();
		testTp.setDeparture("TestDept");
		testTp.setDestination("TestDest");
		testTp.setAvailableSeatCount(1);
		testTp.setSeatCount(4);

		testTp.setPublished(true);

		this.service.create(testTp);

		TravelPlanSearchModel search = new TravelPlanSearchModel();
		search.setDeparture(testTp.getDeparture());
		search.setDestination(testTp.getDestination());
		assertThat(this.service.search(search).get(0)).isEqualTo(testTp);
	}

}
