package com.odev.myestateagencyapi.validator;

import com.odev.myestateagencyapi.model.Amenity;

import java.util.ArrayList;
import java.util.List;

public class AmenityValidator {
    public static List<String> validate(Amenity amenity) {
        List<String> errors = new ArrayList<>();

        if (amenity == null) {
            errors.add("Amenity is null");
            errors.add("Amenity must have a name");
            return errors;
        }

        if (amenity.getName() == null || amenity.getName().isEmpty())
        {
            errors.add("Amenity must has a name");
        }

        return errors;
    }
}
