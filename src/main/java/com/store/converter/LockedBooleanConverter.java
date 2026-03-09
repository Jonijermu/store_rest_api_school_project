package com.store.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class LockedBooleanConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {

        if (attribute == null) {
            return "n";
        }
        return attribute ? "y" : "n";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {

        if (dbData == null) {
            return false;
        }
        if ("y".equalsIgnoreCase(dbData)) return true;
        if ("n".equalsIgnoreCase(dbData)) return false;

        throw new IllegalArgumentException("Wrong n/k type in db: " +dbData);
    }
}
