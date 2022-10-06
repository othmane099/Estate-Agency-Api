package com.odev.myestateagencyapi.validator;

import com.odev.myestateagencyapi.model.City;

import java.util.ArrayList;
import java.util.List;

public class CityValidator {

    public static List<String> validate(City city) {
        List<String> errors = new ArrayList<>();

        if (city == null) {
            errors.add("City is null");
            errors.add("City must have a name");
            return errors;
        }

        if (city.getName() == null || city.getName().isEmpty())
        {
            errors.add("City must has a name");
        }

        return errors;
    }
}
