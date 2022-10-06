package com.odev.myestateagencyapi.repository;

import com.odev.myestateagencyapi.model.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyTypeRepository extends JpaRepository<PropertyType, Long> {
    Optional<PropertyType> findByName(String name);


}
