package com.example.casse_tete.service;

import org.springframework.stereotype.Service;

@Service
public class AlgoService {

    private static int min = 123456789;
    private static int max = 987654321;
    private static final int TO_MS = 1_000_000;
    private static final double TO_SC = 1_000_000_000.0;

    public static String algo() {
        long startTime = System.nanoTime();
        for (int i = min; i <= max; i++) {
            if (isUnique(i)) {
                System.out.println(i);
            }
        }
        long durationNs = System.nanoTime() - startTime; // Elapsed time in nanoseconds
        long durationMs = durationNs / TO_MS; // Convert to milliseconds
        double durationSec = durationNs / TO_SC; // Convert to seconds

        // Print results
        System.out.println("Duration: " + durationMs + " ms (" + durationSec + " sec)");

        return "Duration: " + durationMs + " ms (" + durationSec + " sec)";
    }

    public static boolean isUnique(int tryNumber) {
        String str = String.valueOf(tryNumber);
        if (str.length() != 9) return false;

        for (char c = '1'; c <= '9'; c++) {
            if (str.indexOf(c) == -1) return false;
            if (str.indexOf(c) != str.lastIndexOf(c)) return false;
        }
        return true;
    }
}
