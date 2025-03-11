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

    private double calculAlpha(JsonNode jsonNode){
        try {
            int B = jsonNode.get("B").asInt();
            int C = jsonNode.get("C").asInt();
            return (13.0 * B) / C;
        } catch (Exception ex) {
            throw new RuntimeException("Error while calculating alpha :" + ex.getMessage());
        }
    }

    private double calculBravo(JsonNode jsonNode){
        try{
            int E = jsonNode.get("E").asInt();
            return 12.0 * E;
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error while calculating bravo :" + ex.getMessage());
        }
    }

    private double calculCharlie(JsonNode jsonNode){
        try{
            int G = jsonNode.get("G").asInt();
            int H = jsonNode.get("H").asInt();
            int I = jsonNode.get("I").asInt();
            return (G * H) / I;
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error while calculating charlie :" + ex.getMessage());
        }
    }

    private double calculateResult(JsonNode jsonNode){
        try {
            int A = jsonNode.get("A").asInt();
            int D = jsonNode.get("D").asInt();
            int F = jsonNode.get("F").asInt();

            double alphaResult = calculAlpha(jsonNode);
            double bravoResult = calculBravo(jsonNode);
            double charlieResult = calculCharlie(jsonNode);

            return A + alphaResult + D + bravoResult - F - 11 + charlieResult - 10 ;
        } catch (Exception ex) {
            throw new RuntimeException("Error while calculating result");
        }
    }

    public Solution postSolution(Solution solution) {
        if (solution == null || solution.getGridData() == null || solution.getGridData().trim().isEmpty()) {
            throw new IllegalArgumentException("Solution must have a valid gridData");
        }

        try {
            double result = calculateResult(parseJson(solution.getGridData()));

            if(result == 66.0 ){
                solution.setCorrect(true);
            }else{
                solution.setCorrect(false);
            }

            solution.setResult(result);
            return solutionRepo.save(solution);
        } catch (Exception e) {
            throw new RuntimeException("Failed to process solution: Invalid gridData format", e);
        }
    }

}
