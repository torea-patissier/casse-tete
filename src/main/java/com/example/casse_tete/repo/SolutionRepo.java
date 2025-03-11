package com.example.casse_tete.repo;

import com.example.casse_tete.model.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolutionRepo extends JpaRepository<Solution, Long> {
}
