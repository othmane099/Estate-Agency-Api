package com.odev.myestateagencyapi.validator;

import com.odev.myestateagencyapi.model.Bedroom;

import java.util.ArrayList;
import java.util.List;

public class BedroomValidator {

    public static List<String> validate(Bedroom bedroom) {
        List<String> errors = new ArrayList<>();

        if (bedroom == null) {
            errors.add("Bedroom is null");
            errors.add("Bedroom must have numbers");
            return errors;
        }

        if ( bedroom.getBedNumber() == null || bedroom.getBedNumber().isEmpty())
        {
            errors.add("Bedroom must has numbers");
        }

        return errors;
    }
}
