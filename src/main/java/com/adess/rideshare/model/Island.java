package com.adess.rideshare.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class Island {

    private static Island island_instance = null;
    private List<City> cities;

    private Island() {
        this.cities = new ArrayList<>();
    }

    public static Island getInstance() {
        if(island_instance == null) {
            island_instance = new Island();
            City a = new City("Ankara");
            City b = new City("Kırıkkale");
            a.addAdjacent(b);
            City c = new City("Kayseri");
            b.addAdjacent(c);
            City d = new City("Maras");
            c.addAdjacent(d);

            island_instance.addCity(a);
            island_instance.addCity(b);
            island_instance.addCity(c);
            island_instance.addCity(d);

        }

        return island_instance;
    }

    public void addCity(City c) {
        this.cities.add(c);
    }

    public List<City> getCities() {
        return cities;
    }

    public City getCityByName(String name) {
        return this.cities.stream().filter(x -> x.getName().equals(name)).findAny().orElse(null);
    }
}
