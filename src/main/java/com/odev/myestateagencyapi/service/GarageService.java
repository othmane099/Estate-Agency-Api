package com.odev.myestateagencyapi.service;

import com.odev.myestateagencyapi.exception.EntityNotFoundException;
import com.odev.myestateagencyapi.exception.ErrorCodes;
import com.odev.myestateagencyapi.exception.InvalidEntityException;
import com.odev.myestateagencyapi.model.Garage;
import com.odev.myestateagencyapi.repository.GarageRepository;
import com.odev.myestateagencyapi.validator.GarageValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class GarageService {

    private final GarageRepository garageRepository;

    public Garage save(Garage garage){
        List<String> errors = GarageValidator.validate(garage);
        if (!errors.isEmpty()) {
            log.error("Garage is not valid {}", garage);
            throw new InvalidEntityException("Garage is not valid", ErrorCodes.GARAGE_NOT_VALID, errors);
        }
        Optional<Garage> garageTemp = garageRepository.findByGarageNumber(garage.getGarageNumber());
        if (garageTemp.isPresent()){
            log.error("Garage with number {} already exist", garage.getGarageNumber());
            throw new InvalidEntityException("Garage's number already exist", ErrorCodes.GARAGE_ALREADY_EXISTS);
        }
        return garageRepository.save(garage);
    }

    public List<Garage> findAll(){
        return garageRepository.findAll();
    }

    public void deleteById(Long id){
        if (id == null)
            throw new NullPointerException("Garage ID is null");

        Optional<Garage> garage = garageRepository.findById(id);
        if (garage.isEmpty())
            throw new EntityNotFoundException("Garage with ID= "+id+" not found", ErrorCodes.GARAGE_NOT_FOUND);

        garageRepository.deleteById(id);
    }

    public Garage findByNumber(String nbr){
        if (nbr == null)
            throw new NullPointerException("Garage number is null");

        Optional<Garage> nbrTemp = garageRepository.findByGarageNumber(nbr);
        if (nbrTemp.isEmpty()){
            log.error("Garage with number={} not found", nbrTemp);
            throw new InvalidEntityException("Garage with number="+nbrTemp+" not found", ErrorCodes.GARAGE_NOT_FOUND);
        }

        return nbrTemp.get();
    }
}
