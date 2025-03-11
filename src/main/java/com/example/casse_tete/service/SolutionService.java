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

    private static int ALPHA = 13;
    private static int BRAVO = 12;
    private static int CHARLIE = 11;
    private static int DELTA = 10;
    private static int RESULT_TO_FIND = 66;
    private static final long NANO_SEC_IN_MILLISECOND = 1_000_000;

    @Autowired
    public SolutionService(SolutionRepo solutionRepo) {
        this.solutionRepo = solutionRepo;
    }

    public static JsonNode parseJson(String gridData) {
        try {
            return new ObjectMapper().readTree(gridData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while parsing gridData");
        }
    }

    private int calculAlpha(JsonNode jsonNode){
        try {
            int B = jsonNode.get("B").asInt();
            int C = jsonNode.get("C").asInt();
            return (ALPHA * B) / C;
        } catch (Exception ex) {
            throw new RuntimeException("Error while calculating alpha :" + ex.getMessage());
        }
    }

    private int calculBravo(JsonNode jsonNode){
        try{
            int E = jsonNode.get("E").asInt();
            return BRAVO * E;
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error while calculating bravo :" + ex.getMessage());
        }
    }

    private int calculCharlie(JsonNode jsonNode){
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

            int alphaResult = calculAlpha(jsonNode);
            int bravoResult = calculBravo(jsonNode);
            int charlieResult = calculCharlie(jsonNode);
            int result = A + alphaResult + D + bravoResult - F - CHARLIE + charlieResult - DELTA ;
            return result;
        } catch (Exception ex) {
            throw new RuntimeException("Error while calculating result");
        }
    }

    public Solution postSolution(Solution solution) {
        if (solution == null || solution.getGridData() == null || solution.getGridData().trim().isEmpty()) {
            throw new IllegalArgumentException("Solution must have a valid gridData");
        }

        long startTime = System.nanoTime();
        try {
            int result = calculateResult(parseJson(solution.getGridData()));

            if(result == RESULT_TO_FIND ){
                solution.setCorrect(true);
            }else{
                solution.setCorrect(false);
            }

            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / NANO_SEC_IN_MILLISECOND;

            solution.setDuration_is_ms(duration);
            solution.setResult(result);
            return solutionRepo.save(solution);
        } catch (Exception e) {
            throw new RuntimeException("Failed to process solution: Invalid gridData format", e);
        }
    }

}
