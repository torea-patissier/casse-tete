package com.example.casse_tete.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AlgoService {

    private static final int TO_MS = 1_000_000;
    private static final double TO_SC = 1_000_000_000.0;

    public String algo() {
        long startTime = System.nanoTime();

        List<Integer> uniqueNumbers = generateUniqueNumbers();

        long durationNs = System.nanoTime() - startTime;
        long durationMs = durationNs / TO_MS;
        double durationSec = durationNs / TO_SC;

        System.out.println("Total unique numbers generated: " + uniqueNumbers.size());
        System.out.println("Duration: " + durationMs + " ms (" + durationSec + " sec)");

        return "Duration: " + durationMs + " ms (" + durationSec + " sec)";
    }

    private List<Integer> generateUniqueNumbers() {
        List<Integer> result = new ArrayList<>();
        List<Character> digits = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9');

        System.out.println("Starting permutation process...");
        permute(digits, 0, result);
        return result;
    }

    private void permute(List<Character> arr, int index, List<Integer> result) {
        if (index == arr.size()) {
            StringBuilder sb = new StringBuilder();
            for (char c : arr) sb.append(c);
            int num = Integer.parseInt(sb.toString());
            result.add(num);
            System.out.println("Generated number: " + num);
            return;
        }

        for (int i = index; i < arr.size(); i++) {
            Collections.swap(arr, i, index);
            permute(arr, index + 1, result);
            Collections.swap(arr, i, index);
        }
    }
}
