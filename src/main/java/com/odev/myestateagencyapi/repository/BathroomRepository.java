package com.odev.myestateagencyapi.repository;

import com.odev.myestateagencyapi.model.Bathroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BathroomRepository extends JpaRepository<Bathroom, Long> {

    Optional<Bathroom> findByBathNumber(String bathNbr);
}
