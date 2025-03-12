package com.example.casse_tete.service;

import org.springframework.stereotype.Service;

@Service
public class AlgoService {

    private static int min = 1234;
    private static int max = 4321;

    public static void forLoop() {

        for (int i = min; i <= max; i++) {
            if (isUnique(i)) {
                System.out.println("Nombre valide : " + i);
            }
        }
    }

    public static boolean isUnique(int tryNumber) {
        String str = String.valueOf(tryNumber);

        if (str.length() != 4) return false;

        for (char c = '1'; c <= '4'; c++) {
            if (str.indexOf(c) == -1){
                System.out.println("Nombre invalide A: " + tryNumber);
                return false;
            }

            if (str.indexOf(c) != str.lastIndexOf(c)){
                System.out.println("Nombre invalide B: " + tryNumber);
                return false;
            }
        }
        return true;
    }
}
