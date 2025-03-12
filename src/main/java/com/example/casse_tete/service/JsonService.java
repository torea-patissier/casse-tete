package com.example.casse_tete.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class JsonService {

    private static final Set<String> EXPECTED_KEYS = Set.of("A", "B", "C", "D", "E", "F", "G", "H", "I");

    public void isValidJson(JsonNode jsonNode) {
        List<String> missingKeys = new ArrayList<>();
        List<String> invalidKeys = new ArrayList<>();

        for (String key : EXPECTED_KEYS) {
            JsonNode valueNode = jsonNode.get(key);

            if (valueNode == null) {
                missingKeys.add(key);
                continue;
            }

            if (!valueNode.isInt()) {
                invalidKeys.add(key + " (not an integer)");
            } else {
                int value = valueNode.asInt();
                if (value < 1 || value > 9) {
                    invalidKeys.add(key + " (value: " + value + " | should be between 1 and 9)");
                }
            }
        }

        if (!missingKeys.isEmpty() || !invalidKeys.isEmpty()){
            throw new RuntimeException(
                    (missingKeys.isEmpty() ? "" : "Missing keys: " + String.join(", ", missingKeys) + ". ") +
                            (invalidKeys.isEmpty() ? "" : "Invalid values: " + String.join(", ", invalidKeys) + ".")
            );
        }
    }

    public JsonNode parseJson(String gridData) {
        try {
            JsonNode json = new ObjectMapper().readTree(gridData);
            return json;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while parsing gridData : " + e);
        }
    }
}
