package com.adess.rideshare.model;

import java.util.ArrayList;
import java.util.List;

public class City {
    private String name;

    private List<City> adjacents;

    private CityState state;

    public City(String name) {
        this.name = name;
        this.adjacents = new ArrayList<>();
        this.state = CityState.UNVISITED;
    }

    public String getName() {
        return name;
    }

    public void addAdjacent(City adj) {
        this.adjacents.add(adj);
    }

    public List<City> getAdjacents() {
        return adjacents;
    }

    public CityState getState() {
        return state;
    }

    public void setState(CityState state) {
        this.state = state;
    }
}
