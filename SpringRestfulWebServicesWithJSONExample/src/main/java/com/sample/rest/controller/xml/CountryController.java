package com.sample.rest.controller.xml;

import com.sample.rest.bean.Country;
import com.sample.rest.bean.CountryList;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

    @RequestMapping(value = "/countries", method = RequestMethod.GET, headers = "Accept=application/xml")
    public CountryList getCountries() {
        CountryList countryList = createCountryList();
        return countryList;
    }

    private CountryList createCountryList() {
        List<Country> listOfCountries = new ArrayList<>();
        listOfCountries.add(new Country(1, "India"));
        listOfCountries.add(new Country(2, "China"));
        listOfCountries.add(new Country(3, "Nepal"));
        listOfCountries.add(new Country(4, "Bhutan"));
        return new CountryList(listOfCountries);
    }
    
    @RequestMapping(value = "/country/{id}", method = RequestMethod.GET)
    public Country getCountryById(@PathVariable int id) {
        for (Country country : getCountries().getListOfCountries()) {
            if(country.getId() == id) {
                return country;
            }
        }
        
        return null;
    }
}
