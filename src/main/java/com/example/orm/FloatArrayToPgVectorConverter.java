package com.example.orm;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Converter
public class FloatArrayToPgVectorConverter implements AttributeConverter<float[], String> {

    @Override
    public String convertToDatabaseColumn(float[] attribute) {
        if (attribute == null) return null;
        return "[" + IntStream.range(0,attribute.length)
                .mapToObj(i->String.valueOf(attribute[i]))
                .collect(Collectors.joining(", ")) + "]";
    }

    @Override
    public float[] convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        String cleaned = dbData.replaceAll("[\\[\\]]", "");
        String[] parts = cleaned.split(",");
        float[] arr = new float[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Float.parseFloat(parts[i].trim());
        }
        return arr;
    }
}

