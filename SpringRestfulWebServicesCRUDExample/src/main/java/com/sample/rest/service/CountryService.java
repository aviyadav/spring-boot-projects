package com.sample.rest.service;

import com.sample.rest.bean.Country;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CountryService {

    static Map<Integer, Country> countryIdMap = getCountryIdMap();

    public CountryService() {
        
        if(countryIdMap == null) {
            countryIdMap = new HashMap<>();
            countryIdMap.put(1, new Country(1, "India", 1300000000));
            countryIdMap.put(2, new Country(2, "China", 1800000000));
            countryIdMap.put(3, new Country(3, "Nepal", 110000000));
            countryIdMap.put(4, new Country(4, "Bhutan", 15000000));
        }
    }

    private static Map<Integer, Country> getCountryIdMap() {
        return countryIdMap;
    }
    
    public List<Country> getAllCountries() {
        return new ArrayList<>(countryIdMap.values());
    }
    
    public Country getCountry(int id) {
        return countryIdMap.get(id);
    }
    
    public Country addCountry(Country country) {
        country.setId(getMaxId()+ 1);
        countryIdMap.put(country.getId(), country);
        return country;
    }
    
    public Country updateCountry(Country country) {
        if(country.getId() <= 0) {
            return null;
        }
        
        countryIdMap.put(country.getId(), country);
        return country;
    }
    
    public void deleteCountry(int id) {
        countryIdMap.remove(id);
    }

    private int getMaxId() {
        return countryIdMap.keySet().stream().mapToInt(v -> v).max().orElseThrow(RuntimeException::new);
    }
}
