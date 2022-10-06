package com.odev.myestateagencyapi.repository;

import com.odev.myestateagencyapi.model.Bedroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BedroomRepository extends JpaRepository<Bedroom, Long> {
    Optional<Bedroom> findByBedNumber(String bedNbr);
}
