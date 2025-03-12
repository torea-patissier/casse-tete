package com.example.casse_tete.service;

import org.springframework.stereotype.Service;
import java.io.*;
import java.util.*;

@Service
public class AlgoService {

    /**
     * TODO : Save in db
     */

    private static final String FILE_PATH = "data/valid_results.txt";

    public String algo() {
        long startTime = System.nanoTime();
        List<List<Integer>> validPermutations = new ArrayList<>();
        List<Integer> digits = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        permute(digits, 0, validPermutations);

        long durationMs = (System.nanoTime() - startTime) / 1_000_000;

        System.out.println("Total valid numbers found: " + validPermutations.size());
        System.out.println("Duration: " + durationMs + " ms");

        saveToFile(validPermutations);
        return "Duration: " + durationMs + " ms";
    }

    private void permute(List<Integer> arr, int index, List<List<Integer>> validResults) {
        if (index == arr.size()) {
            List<Integer> numbers = new ArrayList<>(arr);
            if (isValid(numbers)) {
                validResults.add(numbers);
            }
            return;
        }

        for (int i = index; i < arr.size(); i++) {
            Collections.swap(arr, i, index);
            permute(arr, index + 1, validResults);
            Collections.swap(arr, i, index);
        }
    }

    private boolean isValid(List<Integer> numbers) {
        int a = numbers.get(0);
        int b = numbers.get(1);
        int c = numbers.get(2);
        int d = numbers.get(3);
        int e = numbers.get(4);
        int f = numbers.get(5);
        int g = numbers.get(6);
        int h = numbers.get(7);
        int i = numbers.get(8);

        if (b != 9 || c != 3 || e != 2 || f != 1 || i != 4) {
            return false;
        }

        int alpha = (13 * b) / c;
        int bravo = 12 * e;
        int charlie = (g * h) / i;
        int result = a + alpha + d + bravo - f - 11 + charlie - 10;
        boolean isValid = result == 66;

        if (!isValid) {
            System.out.println("Invalid: " + numbers + " → result = " + result);
        }

        return isValid;
    }

    private void saveToFile(List<List<Integer>> validResults) {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (List<Integer> numbers : validResults) {
                writer.write(numbers.toString() + "\n");
            }
            System.out.println("Data successfully saved to " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
