package com.example.casse_tete.controller;

import com.example.casse_tete.model.Solution;
import com.example.casse_tete.service.SolutionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getSolution/{id}")
    public ResponseEntity<Solution> getSolution(@PathVariable Long id){
        Solution solution = solutionService.getSolution(id);
        return new ResponseEntity<>(solution, HttpStatus.OK);
    }

    @GetMapping("/getAllSolutions")
    public ResponseEntity<List<Solution>> getAllSolutions(){
        List<Solution> solutions = solutionService.getAllSolutions();
        return new ResponseEntity<>(solutions, HttpStatus.OK);
    }
}
