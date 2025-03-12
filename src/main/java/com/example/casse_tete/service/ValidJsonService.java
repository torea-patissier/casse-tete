package com.example.casse_tete.service;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ValidJsonService {

    private static final Set<String> EXPECTED_KEYS = Set.of("A", "B", "C", "D", "E", "F", "G", "H", "I");

    public void show (){
        System.out.println("\n \n Hello : \n \n" + EXPECTED_KEYS);
    }
}
