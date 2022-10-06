package com.odev.myestateagencyapi.validator;

import com.odev.myestateagencyapi.model.PropertyType;

import java.util.ArrayList;
import java.util.List;

public class PropertyTypeValidator {

    public static List<String> validate(PropertyType type) {
        List<String> errors = new ArrayList<>();

        if (type == null) {
            errors.add("PropertyType is null");
            errors.add("PropertyType must have a name");
            return errors;
        }

        if (type.getName() == null || type.getName().isEmpty())
        {
            errors.add("PropertyType must has a name");
        }

        return errors;
    }
}
