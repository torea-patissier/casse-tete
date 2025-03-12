package com.example.casse_tete.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ValidJsonService {

    private static final Set<String> EXPECTED_KEYS = Set.of("A", "B", "C", "D", "E", "F", "G", "H", "I");

    public void isValidJson (JsonNode jsonNode){
        System.out.println("\n \n Hello : \n \n" + EXPECTED_KEYS);

        for(String key :  EXPECTED_KEYS){
            if (!jsonNode.has(key)) {
                System.out.println('\n' + key + " is missing");
            }
        }
    }
}
