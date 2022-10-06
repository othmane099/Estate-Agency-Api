package com.odev.myestateagencyapi.service;

import com.odev.myestateagencyapi.exception.EntityNotFoundException;
import com.odev.myestateagencyapi.exception.ErrorCodes;
import com.odev.myestateagencyapi.exception.InvalidEntityException;
import com.odev.myestateagencyapi.model.PropertyStatus;
import com.odev.myestateagencyapi.repository.PropertyStatusRepository;
import com.odev.myestateagencyapi.validator.PropertyStatusValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class PropertyStatusService {

    private final PropertyStatusRepository propertyStatusRepository;

    public PropertyStatus save(PropertyStatus status){
        List<String> errors = PropertyStatusValidator.validate(status);
        if (!errors.isEmpty()) {
            log.error("PropertyStatus is not valid {}", status);
            throw new InvalidEntityException("PropertyStatus is not valid", ErrorCodes.PROPERTY_STATUS_NOT_VALID, errors);
        }
        Optional<PropertyStatus> statusTemp = propertyStatusRepository.findByName(status.getName());
        if (statusTemp.isPresent()){
            log.error("PropertyStatus with name {} already exist", status.getName());
            throw new InvalidEntityException("PropertyStatus's name already exist", ErrorCodes.PROPERTY_STATUS_ALREADY_EXISTS);
        }
        return propertyStatusRepository.save(status);
    }

    public List<PropertyStatus> findAll(){
        return propertyStatusRepository.findAll();
    }

    public void deleteById(Long id){
        if (id == null)
            throw new NullPointerException("PropertyStatus ID is null");

        Optional<PropertyStatus> status = propertyStatusRepository.findById(id);
        if (status.isEmpty())
            throw new EntityNotFoundException("PropertyStatus with ID= "+id+" not found", ErrorCodes.PROPERTY_STATUS_NOT_FOUND);

        propertyStatusRepository.deleteById(id);
    }

    public PropertyStatus findByName(String statusName){
        if (statusName == null)
            throw new NullPointerException("PropertyStatus name is null");

        Optional<PropertyStatus> statusTemp = propertyStatusRepository.findByName(statusName);
        if (statusTemp.isEmpty()){
            log.error("PropertyStatus with name={} not found", statusTemp);
            throw new InvalidEntityException("PropertyStatus with name="+statusTemp+" not found", ErrorCodes.PROPERTY_STATUS_NOT_FOUND);
        }

        return statusTemp.get();
    }
}
