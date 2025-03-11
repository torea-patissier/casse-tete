package com.example.casse_tete.service;

import com.example.casse_tete.model.Solution;
import com.example.casse_tete.repo.SolutionRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolutionService {

    private final SolutionRepo solutionRepo;
    private static final int FIRST_INPUT = 13;
    private static final int SECOND_INPUT = 12;
    private static final int THIRD_INPUT = 11;
    private static final int FOURTH_INPUT = 10;
    private static final int EXPECTED_RESULT = 66;

    @Autowired
    public SolutionService(SolutionRepo solutionRepo) {
        this.solutionRepo = solutionRepo;
    }

    private static JsonNode parseJson(String gridData) {
        try {
            return new ObjectMapper().readTree(gridData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while parsing gridData");
        }
    }

    private int calculA(JsonNode jsonNode){
        try {
            int B = jsonNode.get("B").asInt();
            int C = jsonNode.get("C").asInt();
            return (FIRST_INPUT * B) / C;
        } catch (Exception ex) {
            throw new RuntimeException("Error while calculating alpha :" + ex.getMessage());
        }
    }

    private int calculB(JsonNode jsonNode){
        try{
            int E = jsonNode.get("E").asInt();
            return SECOND_INPUT * E;
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error while calculating bravo :" + ex.getMessage());
        }
    }

    private int calculC(JsonNode jsonNode){
        try{
            int G = jsonNode.get("G").asInt();
            int H = jsonNode.get("H").asInt();
            int I = jsonNode.get("I").asInt();
            return (G * H) / I;
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error while calculating charlie :" + ex.getMessage());
        }
    }

    private int calculateResult(JsonNode jsonNode){
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

    public Solution postSolution(Solution solution) {
        long startTime = System.currentTimeMillis();
        if (solution == null || solution.getGridData() == null || solution.getGridData().trim().isEmpty()) {
            throw new IllegalArgumentException("Solution must have a valid gridData");
        }

        try {
            int result = calculateResult(parseJson(solution.getGridData()));
            if(result == EXPECTED_RESULT){
                solution.setCorrect(true);
            }

            solution.setResult(result);
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime ;
            solution.setDuration_in_ms(duration);
            return solutionRepo.save(solution);
        } catch (Exception e) {
            throw new RuntimeException("Failed to process solution: Invalid gridData format", e);
        }
    }
}
