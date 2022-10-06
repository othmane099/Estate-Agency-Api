package com.odev.myestateagencyapi.validator;

import com.odev.myestateagencyapi.model.Bathroom;

import java.util.ArrayList;
import java.util.List;

public class BathroomValidator {

    public static List<String> validate(Bathroom bathroom) {
        List<String> errors = new ArrayList<>();

        if (bathroom == null) {
            errors.add("Bathroom is null");
            errors.add("Bathroom must have numbers");
            return errors;
        }

        if (bathroom.getBathNumber() == null || bathroom.getBathNumber().isEmpty())
        {
            errors.add("Bathroom must has numbers");
        }

        return errors;
    }
}
