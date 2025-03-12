package com.example.casse_tete.service;

import com.example.casse_tete.model.Solution;
import com.example.casse_tete.repo.SolutionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolutionService {

    private final SolutionRepo solutionRepo;
    private static final int EXPECTED_RESULT = 66;
    private final CalculService calculService;

    @Autowired
    public SolutionService(SolutionRepo solutionRepo, CalculService calculService) {
        this.solutionRepo = solutionRepo;
        this.calculService = calculService;
    }

    public Solution postSolution(Solution solution) {
        long startTime = System.currentTimeMillis();
        if (solution == null || solution.getGridData() == null || solution.getGridData().trim().isEmpty()) {
            throw new IllegalArgumentException("Solution must have a valid gridData");
        }

        try {
            int result = calculService.calculResult(calculService.parseJson(solution.getGridData()));
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

    public Solution getSolution(Long id) {
        Optional<Solution> solution = solutionRepo.findById(id);
        return solution.orElseThrow(() -> new RuntimeException("Solution with ID " + id + " not found"));
    }

    public List<Solution> getAllSolutions() {
        List<Solution> solutions = solutionRepo.findAll();
        if (solutions.isEmpty()) {
            throw new RuntimeException("No solutions found");
        }
        return solutions;
    }

    public void deleteSolution(Long id) {
        if (!solutionRepo.existsById(id)) {
            throw new RuntimeException("Solution with ID " + id + " not found");
        }
        solutionRepo.deleteById(id);
    }

    public void deleteAllSolutions() {
        solutionRepo.deleteAll();
    }

    public Solution updateSolution(Solution solution) {

        Optional<Solution> existingSolution = solutionRepo.findById(solution.getId());
        if (existingSolution.isEmpty()) {
            throw new RuntimeException("Solution with ID " + solution.getId() + " not found");
        }
        return postSolution(solution);
    }
}
