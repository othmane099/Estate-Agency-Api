package com.odev.myestateagencyapi.sec.service.impl;


import com.odev.myestateagencyapi.exception.EntityNotFoundException;
import com.odev.myestateagencyapi.exception.ErrorCodes;
import com.odev.myestateagencyapi.exception.InvalidEntityException;
import com.odev.myestateagencyapi.sec.entities.AppRole;
import com.odev.myestateagencyapi.sec.entities.AppUser;
import com.odev.myestateagencyapi.sec.repository.AppRoleRepository;
import com.odev.myestateagencyapi.sec.repository.AppUserRepository;
import com.odev.myestateagencyapi.sec.service.AccountService;
import com.odev.myestateagencyapi.sec.validator.AppRoleValidator;
import com.odev.myestateagencyapi.sec.validator.AppUserValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    final private AppUserRepository appUserRepository;
    final private AppRoleRepository appRoleRepository;
    final private PasswordEncoder passwordEncoder;


    @Override
    public AppUser addNewUser(AppUser appUser) {
        List<String> errors = AppUserValidator.validate(appUser);
        if (!errors.isEmpty()) {
            log.error("User is not valid {}", appUser);
            throw new InvalidEntityException("User is not", ErrorCodes.UTILISATEUR_NOT_VALID, errors);
        }

        if(appUserRepository.findByUsername(appUser.getUsername()).isPresent()) {
            throw new InvalidEntityException("This username already exist in DB", ErrorCodes.UTILISATEUR_ALREADY_EXISTS,
                    Collections.singletonList("This username already exist in DB"));
        }

        String id = UUID.randomUUID().toString();
        String password = appUser.getPassword();
        appUser.setId(id);
        appUser.setPassword(passwordEncoder.encode(password));
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole addNewRole(AppRole appRole) {

        List<String> errors = AppRoleValidator.validate(appRole);
        if (!errors.isEmpty()) {
            log.error("This role is not valid {}", appRole);
            throw new InvalidEntityException("This role is not valid", ErrorCodes.ROLE_NOT_VALID, errors);
        }

        if(appUserRepository.findByUsername(appRole.getRoleName()).isPresent()) {
            throw new InvalidEntityException("This role already exist in DB", ErrorCodes.ROLE_ALREADY_EXISTS,
                    Collections.singletonList("This role already exist in DB"));
        }

        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        Optional<AppUser> appUser = appUserRepository.findByUsername(username);
        if (appUser.isEmpty())
            throw new EntityNotFoundException("Username: "+username+" not found", ErrorCodes.UTILISATEUR_NOT_FOUND);

        Optional<AppRole> appRole = appRoleRepository.findByRoleName(roleName);
        if (appRole.isEmpty())
            throw new EntityNotFoundException("Role: "+roleName+" not found", ErrorCodes.ROLE_NOT_FOUND);
        appUser.get().getAppRoles().add(appRole.get());
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        if (appUserRepository.findByUsername(username).isEmpty())
            throw new EntityNotFoundException("Username: "+username+" not found", ErrorCodes.UTILISATEUR_NOT_FOUND);
        return appUserRepository.findByUsername(username).get();
    }

    @Override
    public List<AppUser> listUsers() {
        return appUserRepository.findAll();
    }


}
