package com.odev.myestateagencyapi.repository;

import com.odev.myestateagencyapi.model.PropertyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyStatusRepository extends JpaRepository<PropertyStatus, Long> {

    Optional<PropertyStatus> findByName(String name);
}
