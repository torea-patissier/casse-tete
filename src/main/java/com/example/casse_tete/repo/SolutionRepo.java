package com.example.casse_tete.repo;

import com.example.casse_tete.model.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolutionRepo extends JpaRepository<Solution, Long> {
    List<Solution> findByIsAlgoGenerated(boolean isAlgoGenerated);
}
