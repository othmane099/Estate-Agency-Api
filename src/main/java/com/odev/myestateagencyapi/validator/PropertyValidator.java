package com.odev.myestateagencyapi.validator;

import com.odev.myestateagencyapi.model.Property;

import java.util.ArrayList;
import java.util.List;

public class PropertyValidator {

    public static List<String> validate(Property property) {
        List<String> errors = new ArrayList<>();

        if (property == null) {
            errors.add("Property is null");
        }
        return errors;
    }

}
