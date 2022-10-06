package com.odev.myestateagencyapi.sec.validator;

import com.odev.myestateagencyapi.sec.entities.AppUser;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AppUserValidator {

  public static List<String> validate(AppUser appUser) {
    List<String> errors = new ArrayList<>();

    if (appUser == null) {
      errors.add("User object should not be null");
      return errors;
    }

    if (!StringUtils.hasLength(appUser.getUsername())) {
      errors.add("User's email is required");
    }
    if (!StringUtils.hasLength(appUser.getPassword())) {
      errors.add("User's password is required");
    }

    return errors;
  }

}
