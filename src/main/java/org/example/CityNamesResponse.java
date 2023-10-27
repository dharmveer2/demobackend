package org.example;

import java.util.Set;

public class CityNamesResponse {
    private Set<String> cityNames;

    public CityNamesResponse(Set<String> cityNames) {
        this.cityNames = cityNames;
    }

    public Set<String> getCityNames() {
        return cityNames;
    }
}
