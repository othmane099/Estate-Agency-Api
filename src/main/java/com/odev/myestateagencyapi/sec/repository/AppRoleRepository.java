package com.odev.myestateagencyapi.sec.repository;

import com.odev.myestateagencyapi.sec.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    Optional<AppRole> findByRoleName(String roleName);

}
