package com.example.casse_tete.service;

import com.example.casse_tete.model.Solution;
import com.example.casse_tete.repo.SolutionRepo;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AlgoService {

    private final SolutionRepo solutionRepo;
    private static final long TO_MS = 1_000_000L;
    private long PERMUTATION_COUNT = 0;
    private int RESULT = 66;

    public AlgoService(SolutionRepo solutionRepo) {
        this.solutionRepo = solutionRepo;
    }

    public String algorithm() {
        long startTime = System.nanoTime();

        List<List<Integer>> generatedSolutions = new ArrayList<>();
        List<Integer> digits = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        PERMUTATION_COUNT = 0;
        applyPermutation(digits, 0, generatedSolutions);
        registerSolutions(generatedSolutions);
        long durationMs = (System.nanoTime() - startTime) / TO_MS;
        return "Duration: " + durationMs + " ms, Permutations tested: " + PERMUTATION_COUNT + ", Solutions found: " + generatedSolutions.size() + " (saved in DB)";
    }

    private void applyPermutation(List<Integer> arr, int startIndex, List<List<Integer>> validResults) {
        if (startIndex == arr.size()) {
            PERMUTATION_COUNT++;
            List<Integer> numbers = new ArrayList<>(arr);

            if (isValidResult(numbers)) {
                validResults.add(numbers);
            }
            return;
        }

        for (int i = startIndex; i < arr.size(); i++) {
            Collections.swap(arr, i, startIndex);
            applyPermutation(arr, startIndex + 1, validResults);
            Collections.swap(arr, i, startIndex);
        }
    }

    private boolean isValidResult(List<Integer> numbers) {
        int a = numbers.get(0);
        int b = numbers.get(1);
        int c = numbers.get(2);
        int d = numbers.get(3);
        int e = numbers.get(4);
        int f = numbers.get(5);
        int g = numbers.get(6);
        int h = numbers.get(7);
        int i = numbers.get(8);

        int calculateA = (13 * b) / c;
        int calculateB = 12 * e;
        int calculateC = (g * h) / i;

        int result = a + calculateA + d + calculateB - f - 11 + calculateC - 10;
        return result == RESULT;
    }

    private void registerSolutions(List<List<Integer>> validResults) {
        for (List<Integer> numbers : validResults) {
            Solution solution = new Solution();
            solution.setGridData(numbers);
            solution.setResult(RESULT);
            solution.setAlgoGenerated(true);
            solution.setCorrect(true);
            solutionRepo.save(solution);
        }
    }
}
