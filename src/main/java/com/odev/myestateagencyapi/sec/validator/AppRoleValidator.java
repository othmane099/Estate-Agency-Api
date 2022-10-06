package com.odev.myestateagencyapi.sec.validator;

import com.odev.myestateagencyapi.sec.entities.AppRole;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AppRoleValidator {

  public static List<String> validate(AppRole appRole) {
    List<String> errors = new ArrayList<>();

    if (appRole == null) {
      errors.add("Role object should not be null");
      return errors;
    }

    if (!StringUtils.hasLength(appRole.getRoleName())) {
      errors.add("Role's name is required");
    }

    return errors;
  }

}
