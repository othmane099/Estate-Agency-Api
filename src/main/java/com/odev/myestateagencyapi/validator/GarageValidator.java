package com.odev.myestateagencyapi.validator;

import com.odev.myestateagencyapi.model.Garage;

import java.util.ArrayList;
import java.util.List;

public class GarageValidator {

    public static List<String> validate(Garage garage) {
        List<String> errors = new ArrayList<>();

        if (garage == null) {
            errors.add("Garage is null");
            errors.add("Garage must have a numbers");
            return errors;
        }

        if (garage.getGarageNumber() == null || garage.getGarageNumber().isEmpty())
        {
            errors.add("Garage must has numbers");
        }

        return errors;
    }
}
