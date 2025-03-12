package com.example.casse_tete.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CalculService {

    private static final int FIRST_INPUT = 13;
    private static final int SECOND_INPUT = 12;
    private static final int THIRD_INPUT = 11;
    private static final int FOURTH_INPUT = 10;
    private static final Set<String> EXPECTED_KEYS = Set.of("A", "B", "C", "D", "E", "F", "G", "H", "I");

    static boolean isValidJson(JsonNode jsonNode) {
        for (String key : EXPECTED_KEYS) {
            if (!jsonNode.has(key)) {
                System.out.println("\n" + key + " is missing");
                return false;
            }
        }
        return true;
    }

    static JsonNode parseJson(String gridData) {
        try {
            JsonNode json = new ObjectMapper().readTree(gridData);
            return json;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while parsing gridData : " + e);
        }
    }

    public int calculA(JsonNode jsonNode){
        try {
            int B = jsonNode.get("B").asInt();
            int C = jsonNode.get("C").asInt();
            return (FIRST_INPUT * B) / C;
        } catch (Exception ex) {
            throw new RuntimeException("Error while calculating A :" + ex.getMessage());
        }
    }

    public int calculB(JsonNode jsonNode){
        try{
            int E = jsonNode.get("E").asInt();
            return SECOND_INPUT * E;
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error while calculating B :" + ex.getMessage());
        }
    }

    public int calculC(JsonNode jsonNode){
        try{
            int G = jsonNode.get("G").asInt();
            int H = jsonNode.get("H").asInt();
            int I = jsonNode.get("I").asInt();
            return (G * H) / I;
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error while calculating C :" + ex.getMessage());
        }
    }

    public int calculResult(JsonNode jsonNode){

        if (!isValidJson(jsonNode)) {
            throw new RuntimeException("Invalid JSON data provided.");
        }

        try {
            int A = jsonNode.get("A").asInt();
            int D = jsonNode.get("D").asInt();
            int F = jsonNode.get("F").asInt();

            int aResult = calculA(jsonNode);
            int bResult = calculB(jsonNode);
            int cResult = calculC(jsonNode);

            int result = A + aResult + D + bResult - F - THIRD_INPUT + cResult - FOURTH_INPUT;
            return result;
        } catch (Exception ex) {
            throw new RuntimeException("Error while calculating result");
        }
    }
}
