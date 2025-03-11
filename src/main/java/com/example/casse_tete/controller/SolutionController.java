package com.example.casse_tete.controller;

import com.example.casse_tete.model.Solution;
import com.example.casse_tete.service.SolutionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolutionController {

    private final SolutionService solutionService;

    public SolutionController(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @PostMapping("/addSolution")
    public ResponseEntity<Solution> postSolution(@RequestBody Solution solution) {
        Solution solutionObj = solutionService.postSolution(solution);
        return new ResponseEntity<>(solutionObj, HttpStatus.CREATED);
    }
}
