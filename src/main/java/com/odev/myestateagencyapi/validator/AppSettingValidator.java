package com.odev.myestateagencyapi.validator;

import com.odev.myestateagencyapi.model.AppSetting;

import java.util.ArrayList;
import java.util.List;

public class AppSettingValidator {

    public static List<String> validate(AppSetting setting) {
        List<String> errors = new ArrayList<>();

        if (setting == null) {
            errors.add("AppSetting is null");
            errors.add("AppSetting must have reception mail");
            errors.add("AppSetting must have agency mail");
            errors.add("AppSetting must have agency phone");
            errors.add("AppSetting must have agency location");
            errors.add("AppSetting must have default image link");
            return errors;
        }

        if (setting.getReceptionMail() == null || setting.getReceptionMail().isEmpty())
        {
            errors.add("AppSetting must have reception mail");
        }

        if (setting.getAgencyMail() == null || setting.getAgencyMail().isEmpty())
        {
            errors.add("AppSetting must have agency mail");
        }

        if (setting.getAgencyPhone() == null || setting.getAgencyPhone().isEmpty())
        {
            errors.add("AppSetting must have agency phone");
        }

        if (setting.getAgencyLocation() == null || setting.getAgencyLocation().isEmpty())
        {
            errors.add("AppSetting must have agency location");
        }

        if (setting.getDefaultImageLink() == null || setting.getDefaultImageLink().isEmpty())
        {
            errors.add("AppSetting must have default image link");
        }

        return errors;
    }
}
