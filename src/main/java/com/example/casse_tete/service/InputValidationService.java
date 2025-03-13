package com.example.casse_tete.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class InputValidationService {

    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 9;
    private static final int EXPECTED_SIZE = 9;

    public void isValidList(List<Integer> gridData) {
        if (gridData == null || gridData.size() != EXPECTED_SIZE) {
            throw new RuntimeException("Invalid input: Exactly " + EXPECTED_SIZE + " numbers are required.");
        }

        Set<Integer> uniqueNumbers = new HashSet<>();
        for (Integer value : gridData) {
            if (value == null) {
                throw new RuntimeException("Invalid value: Null values are not allowed.");
            }
            if (value < MIN_VALUE || value > MAX_VALUE) {
                throw new RuntimeException("Invalid value: " + value + " (should be between " + MIN_VALUE + " and " + MAX_VALUE + ").");
            }
            if (!uniqueNumbers.add(value)) {
                throw new RuntimeException("Invalid input: Duplicate value found: " + value);
            }
        }
    }
}
