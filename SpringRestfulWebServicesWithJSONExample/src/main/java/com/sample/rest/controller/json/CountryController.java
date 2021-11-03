package com.sample.rest.controller.json;

import com.sample.rest.bean.Country;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

    @RequestMapping(value = "/countries", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Country> getCountries() {
        List<Country> listOfCountries = createCountryList();
        return listOfCountries;
    }

    private List<Country> createCountryList() {
        List<Country> listOfCountries = new ArrayList<>();
        listOfCountries.add(new Country(1, "India"));
        listOfCountries.add(new Country(2, "China"));
        listOfCountries.add(new Country(3, "Nepal"));
        listOfCountries.add(new Country(4, "Bhutan"));
        return listOfCountries;
    }
    
    @RequestMapping(value = "/country/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Country getCountryById(@PathVariable int id) {
        for (Country country : getCountries()) {
            if(country.getId() == id) {
                return country;
            }
        }
        
        return null;
    }
}
