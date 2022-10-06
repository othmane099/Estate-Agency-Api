package com.odev.myestateagencyapi.validator;

import com.odev.myestateagencyapi.model.PropertyStatus;

import java.util.ArrayList;
import java.util.List;

public class PropertyStatusValidator {

    public static List<String> validate(PropertyStatus status) {
        List<String> errors = new ArrayList<>();

        if (status == null) {
            errors.add("PropertyStatus is null");
            errors.add("PropertyStatus must have a name");
            return errors;
        }

        if (status.getName() == null || status.getName().isEmpty())
        {
            errors.add("PropertyStatus must has a name");
        }

        return errors;
    }
}
