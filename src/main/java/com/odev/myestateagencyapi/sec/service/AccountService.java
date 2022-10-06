package com.odev.myestateagencyapi.sec.service;

import com.odev.myestateagencyapi.sec.entities.AppRole;
import com.odev.myestateagencyapi.sec.entities.AppUser;

import java.util.List;

public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String username, String roleName);
    AppUser loadUserByUsername(String username);
    List<AppUser> listUsers();
}
