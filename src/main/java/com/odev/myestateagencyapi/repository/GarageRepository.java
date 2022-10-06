package com.odev.myestateagencyapi.repository;

import com.odev.myestateagencyapi.model.Garage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GarageRepository extends JpaRepository<Garage, Long> {
    Optional<Garage> findByGarageNumber(String garageNbr);
}
