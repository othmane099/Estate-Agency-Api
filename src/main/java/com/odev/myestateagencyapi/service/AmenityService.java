package com.odev.myestateagencyapi.service;

import com.odev.myestateagencyapi.exception.EntityNotFoundException;
import com.odev.myestateagencyapi.exception.ErrorCodes;
import com.odev.myestateagencyapi.exception.InvalidEntityException;
import com.odev.myestateagencyapi.model.Amenity;
import com.odev.myestateagencyapi.repository.AmenityRepository;
import com.odev.myestateagencyapi.validator.AmenityValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class AmenityService {

    private final AmenityRepository amenityRepository;

    public Amenity save(Amenity amenity){
        List<String> errors = AmenityValidator.validate(amenity);
        if (!errors.isEmpty()) {
            log.error("Amenity is not valid {}", amenity);
            throw new InvalidEntityException("Amenity is not valid", ErrorCodes.AMENITY_NOT_VALID, errors);
        }

        Optional<Amenity> amenityTemp = amenityRepository.findByName(amenity.getName());
        if (amenityTemp.isPresent()){
            log.error("Amenity with name {} already exist", amenity.getName());
            throw new InvalidEntityException("Amenity's name already exist", ErrorCodes.AMENITY_ALREADY_EXISTS);
        }
        return amenityRepository.save(amenity);
    }

    public List<Amenity> findAll(){
        return amenityRepository.findAll();
    }

    public void deleteById(Long id){
        amenityRepository.deleteById(this.findById(id).getId());
    }

    public Amenity findById(Long id) {
        if (id == null)
            throw new NullPointerException("Amenity ID is null");

        Optional<Amenity> amenity = amenityRepository.findById(id);
        if (amenity.isEmpty())
            throw new EntityNotFoundException("Amenity with ID= "+id+" not found", ErrorCodes.AMENITY_NOT_FOUND);
        return amenity.get();
    }
}
