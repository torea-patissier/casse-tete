package com.example.casse_tete.service;

import com.example.casse_tete.model.Solution;
import com.example.casse_tete.repo.SolutionRepo;
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

    private double calculAlpha(String gridData){
        try {
            JsonNode jsonNode = new ObjectMapper().readTree(gridData);
            int B = jsonNode.get("B").asInt();
            int C = jsonNode.get("C").asInt();
            return (13.0*B) / C;
        } catch (Exception ex) {
            throw new RuntimeException("Error while calculating alpha");
        }
    }

    private double calculateResult(String gridData){
        try {
            double calculatedAlpha = calculAlpha(gridData);
            return calculatedAlpha;
        } catch (Exception ex) {
            throw new RuntimeException("Error while calculating result");
        }
    }

    public Solution postSolution(Solution solution) {
        if (solution == null || solution.getGridData() == null || solution.getGridData().trim().isEmpty()) {
            throw new IllegalArgumentException("Solution must have a valid gridData");
        }

        try {
            double result = calculateResult(solution.getGridData());
            if(result == 66 ){
                solution.setCorrect(true);
            }
            solution.setCorrect(false);
            solution.setResult(result);
            return solutionRepo.save(solution);
        } catch (Exception e) {
            throw new RuntimeException("Failed to process solution: Invalid gridData format", e);
        }
    }

}
