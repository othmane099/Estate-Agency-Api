package com.odev.myestateagencyapi.service;

import com.odev.myestateagencyapi.exception.EntityNotFoundException;
import com.odev.myestateagencyapi.exception.ErrorCodes;
import com.odev.myestateagencyapi.exception.InvalidEntityException;
import com.odev.myestateagencyapi.model.PropertyType;
import com.odev.myestateagencyapi.repository.PropertyTypeRepository;
import com.odev.myestateagencyapi.validator.PropertyTypeValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class PropertyTypeService {

    private final PropertyTypeRepository propertyTypeRepository;

    public PropertyType save(PropertyType type){
        List<String> errors = PropertyTypeValidator.validate(type);
        if (!errors.isEmpty()) {
            log.error("PropertyType is not valid {}", type);
            throw new InvalidEntityException("PropertyType is not valid", ErrorCodes.PROPERTY_TYPE_NOT_VALID, errors);
        }
        Optional<PropertyType> typeTemp = propertyTypeRepository.findByName(type.getName());
        if (typeTemp.isPresent()){
            log.error("PropertyType with name {} already exist", type.getName());
            throw new InvalidEntityException("PropertyType's name already exist", ErrorCodes.PROPERTY_TYPE_ALREADY_EXISTS);
        }
        return propertyTypeRepository.save(type);
    }

    public List<PropertyType> findAll(){
        return propertyTypeRepository.findAll();
    }

    public void deleteById(Long id){
        if (id == null)
            throw new NullPointerException("PropertyType ID is null");

        Optional<PropertyType> type = propertyTypeRepository.findById(id);
        if (type.isEmpty())
            throw new EntityNotFoundException("PropertyType with ID= "+id+" not found", ErrorCodes.PROPERTY_TYPE_NOT_FOUND);

        propertyTypeRepository.deleteById(id);
    }

    public PropertyType findByName(String typeName){
        if (typeName == null)
            throw new NullPointerException("PropertyType name is null");

        Optional<PropertyType> typeTemp = propertyTypeRepository.findByName(typeName);
        if (typeTemp.isEmpty()){
            log.error("PropertyType with name={} not found", typeName);
            throw new InvalidEntityException("PropertyType with name="+typeName+" not found", ErrorCodes.PROPERTY_TYPE_NOT_FOUND);
        }

        return typeTemp.get();
    }
}
