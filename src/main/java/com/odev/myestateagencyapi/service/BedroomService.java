package com.odev.myestateagencyapi.service;

import com.odev.myestateagencyapi.exception.EntityNotFoundException;
import com.odev.myestateagencyapi.exception.ErrorCodes;
import com.odev.myestateagencyapi.exception.InvalidEntityException;
import com.odev.myestateagencyapi.model.Bedroom;
import com.odev.myestateagencyapi.repository.BedroomRepository;
import com.odev.myestateagencyapi.validator.BedroomValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class BedroomService {

    private final BedroomRepository bedroomRepository;

    public Bedroom save(Bedroom bedroom){
        List<String> errors = BedroomValidator.validate(bedroom);
        if (!errors.isEmpty()) {
            log.error("Bedroom is not valid {}", bedroom);
            throw new InvalidEntityException("PropertyStatus is not valid", ErrorCodes.BEDROOM_NOT_VALID, errors);
        }
        Optional<Bedroom> bedroomTemp = bedroomRepository.findByBedNumber(bedroom.getBedNumber());
        if (bedroomTemp.isPresent()){
            log.error("Bedroom with number {} already exist", bedroom.getBedNumber());
            throw new InvalidEntityException("Bedroom's number already exist", ErrorCodes.BEDROOM_ALREADY_EXISTS);
        }
        return bedroomRepository.save(bedroom);
    }

    public List<Bedroom> findAll(){
        return bedroomRepository.findAll();
    }

    public void deleteById(Long id){
        if (id == null)
            throw new NullPointerException("Bedroom ID is null");

        Optional<Bedroom> bedroom = bedroomRepository.findById(id);
        if (bedroom.isEmpty())
            throw new EntityNotFoundException("Bedroom with ID= "+id+" not found", ErrorCodes.BEDROOM_NOT_FOUND);

        bedroomRepository.deleteById(id);
    }

    public Bedroom findByNumber(String nbr){
        if (nbr == null)
            throw new NullPointerException("Bedroom number is null");

        Optional<Bedroom> nbrTemp = bedroomRepository.findByBedNumber(nbr);
        if (nbrTemp.isEmpty()){
            log.error("Bedroom with number={} not found", nbrTemp);
            throw new InvalidEntityException("Bedroom with number="+nbrTemp+" not found", ErrorCodes.BEDROOM_NOT_FOUND);
        }

        return nbrTemp.get();
    }
}
