package com.odev.myestateagencyapi.service;

import com.odev.myestateagencyapi.exception.EntityNotFoundException;
import com.odev.myestateagencyapi.exception.ErrorCodes;
import com.odev.myestateagencyapi.exception.InvalidEntityException;
import com.odev.myestateagencyapi.model.Bathroom;
import com.odev.myestateagencyapi.repository.BathroomRepository;
import com.odev.myestateagencyapi.validator.BathroomValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class BathroomService {

    private final BathroomRepository bathroomRepository;

    public Bathroom save(Bathroom bathroom){
        List<String> errors = BathroomValidator.validate(bathroom);
        if (!errors.isEmpty()) {
            log.error("Bathroom is not valid {}", bathroom);
            throw new InvalidEntityException("Bathroom is not valid", ErrorCodes.BATHROOM_NOT_VALID, errors);
        }
        Optional<Bathroom> bathroomTemp = bathroomRepository.findByBathNumber(bathroom.getBathNumber());
        if (bathroomTemp.isPresent()){
            log.error("Bathroom with number {} already exist", bathroom.getBathNumber());
            throw new InvalidEntityException("Bathroom's name already exist", ErrorCodes.BATHROOM_ALREADY_EXISTS);
        }
        return bathroomRepository.save(bathroom);
    }

    public List<Bathroom> findAll(){
        return bathroomRepository.findAll();
    }

    public void deleteById(Long id){
        bathroomRepository.deleteById(this.findById(id).getId());
    }

    public Bathroom findById(Long id) {
        if (id == null)
            throw new NullPointerException("Bathroom ID is null");

        Optional<Bathroom> bathroom = bathroomRepository.findById(id);
        if (bathroom.isEmpty())
            throw new EntityNotFoundException("Bathroom with ID= "+id+" not found", ErrorCodes.BEDROOM_NOT_FOUND);
        return bathroom.get();
    }

    public Bathroom findByNumber(String nbr){
        if (nbr == null)
            throw new NullPointerException("Bathroom number is null");

        Optional<Bathroom> nbrTemp = bathroomRepository.findByBathNumber(nbr);
        if (nbrTemp.isEmpty()){
            log.error("Bathroom with number={} not found", nbrTemp);
            throw new InvalidEntityException("Bathroom with number="+nbrTemp+" not found", ErrorCodes.BATHROOM_NOT_FOUND);
        }

        return nbrTemp.get();
    }
}
