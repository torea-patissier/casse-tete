package com.example.casse_tete.service;

import com.example.casse_tete.model.Solution;
import com.example.casse_tete.repo.SolutionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolutionService {

    private final SolutionRepo solutionRepo;

    @Autowired
    public SolutionService(SolutionRepo solutionRepo) {
        this.solutionRepo = solutionRepo;
    }

    public Solution postSolution(Solution solution) {
        if (solution == null || solution.getGridData() == null) {
            throw new IllegalArgumentException("Solution must not be null");
        }
        return solutionRepo.save(solution);
    }
}
