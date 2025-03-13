package com.example.casse_tete.controller;

import com.example.casse_tete.model.Solution;
import com.example.casse_tete.service.AlgoService;
import com.example.casse_tete.service.SolutionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solutions")
public class SolutionController {

    private final SolutionService solutionService;
    private final AlgoService algoService;

    public SolutionController(SolutionService solutionService, AlgoService algoService) {
        this.solutionService = solutionService;
        this.algoService = algoService;
    }

    @PostMapping
    public ResponseEntity<Solution> postSolution(@RequestBody Solution solution) {
        Solution solutionObj = solutionService.postSolution(solution);
        return new ResponseEntity<>(solutionObj, HttpStatus.CREATED);
    }

    @GetMapping("/algorithm")
    public ResponseEntity<String> forLoop(){
        String algo = algoService.algorithm();
        return new ResponseEntity<>(algo, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solution> getSolution(@PathVariable Long id){
        Solution solution = solutionService.getSolution(id);
        return new ResponseEntity<>(solution, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Solution>> getAllSolutions(){
        List<Solution> solutions = solutionService.getAllSolutions();
        return new ResponseEntity<>(solutions, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSolution(@PathVariable Long id) {
        try {
            solutionService.deleteSolution(id);
            return new ResponseEntity<>("Solution deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllSolutions() {
        solutionService.deleteAllSolutions();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Solution> updateSolution(@RequestBody Solution solution, @PathVariable Long id) {
        if (solution == null || solution.getGridData() == null || solution.getGridData().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        solution.setId(id);
        Solution updatedSolution = solutionService.updateSolution(solution);
        return new ResponseEntity<>(updatedSolution, HttpStatus.OK);
    }
}
