package com.booking.Controller;

import com.booking.Dto.PropertyDto;
import com.booking.Repository.PropertyRepository;
import com.booking.entity.Country;
import com.booking.entity.CountryRepository;
import com.booking.entity.Property;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {
    private PropertyRepository propertyRepository;
    private CountryRepository countryRepository;

    public PropertyController(PropertyRepository propertyRepository, CountryRepository countryRepository) {
        this.propertyRepository = propertyRepository;
        this.countryRepository = countryRepository;
    }




@PostMapping("/add")
    public ResponseEntity<String> addnewaproperty(@RequestBody PropertyDto propertydto){

    Property user=new Property();
    user.setGuest(propertydto.getGuest());
    user.setBedroom(propertydto.getBedroom());
    user.setNightlyPrice(propertydto.getNightlyPrice());
user.setPropertyName(propertydto.getPropertyName());
    List<Country> country = countryRepository.findBycountryName(propertydto.getCountry());
    for(Country c:country){
        System.out.println(c.getId());
        user.setCountry(c);
    }
propertyRepository.save(user);

    return new ResponseEntity<>("Property added", HttpStatus.CREATED);
    }

    @GetMapping ("/{location}")
    public ResponseEntity<List<Property>> findproperty( @PathVariable  String location){
        List<Property> properties= propertyRepository.findByLocation(location);
        return new ResponseEntity<>(properties,HttpStatus.OK);
    }
}
