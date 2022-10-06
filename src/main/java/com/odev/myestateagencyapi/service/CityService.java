package com.odev.myestateagencyapi.service;

import com.odev.myestateagencyapi.exception.EntityNotFoundException;
import com.odev.myestateagencyapi.exception.ErrorCodes;
import com.odev.myestateagencyapi.exception.InvalidEntityException;
import com.odev.myestateagencyapi.model.City;
import com.odev.myestateagencyapi.repository.CityRepository;
import com.odev.myestateagencyapi.validator.CityValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public City save(City city){
        List<String> errors = CityValidator.validate(city);
        if (!errors.isEmpty()) {
            log.error("City is not valid {}", city);
            throw new InvalidEntityException("PropertyStatus is not valid", ErrorCodes.CITY_NOT_VALID, errors);
        }
        Optional<City> cityTemp = cityRepository.findByName(city.getName());
        if (cityTemp.isPresent()){
            log.error("City with name {} already exist", city.getName());
            throw new InvalidEntityException("Bedroom's name already exist", ErrorCodes.CITY_ALREADY_EXISTS);
        }
        return cityRepository.save(city);
    }

    public List<City> findAll(){
        return cityRepository.findAll();
    }

    public void deleteById(Long id){
        if (id == null)
            throw new NullPointerException("City ID is null");

        Optional<City> bedroom = cityRepository.findById(id);
        if (bedroom.isEmpty())
            throw new EntityNotFoundException("City with ID= "+id+" not found", ErrorCodes.CITY_NOT_FOUND);

        cityRepository.deleteById(id);
    }
}
