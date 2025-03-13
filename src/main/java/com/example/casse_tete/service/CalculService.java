package com.example.casse_tete.service;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CalculService {

    private static final int FIRST_INPUT = 13;
    private static final int SECOND_INPUT = 12;
    private static final int THIRD_INPUT = 11;
    private static final int FOURTH_INPUT = 10;

    public int calculA(int B, int C) {
        return (FIRST_INPUT * B) / C;
    }

    public int calculB(int E) {
        return SECOND_INPUT * E;
    }

    public int calculC(int G, int H, int I) {
        return (G * H) / I;
    }

    public int calculResult(List<Integer> numbers) {

        if (numbers.size() < 9) {
            throw new IllegalArgumentException("Invalid input: At least 9 numbers are required.");
        }

        int A = numbers.get(0);
        int B = numbers.get(1);
        int C = numbers.get(2);
        int D = numbers.get(3);
        int E = numbers.get(4);
        int F = numbers.get(5);
        int G = numbers.get(6);
        int H = numbers.get(7);
        int I = numbers.get(8);

        int aResult = calculA(B, C);
        int bResult = calculB(E);
        int cResult = calculC(G, H, I);

        return A + aResult + D + bResult - F - THIRD_INPUT + cResult - FOURTH_INPUT;
    }
}
