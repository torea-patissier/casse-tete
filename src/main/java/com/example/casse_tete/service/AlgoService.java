package com.example.casse_tete.service;

import org.springframework.stereotype.Service;

@Service
public class AlgoService {

    public void forLoop(){
        for(int i = 0; i < 10; i++){
            System.out.println("i = " + i);
        }
    }
}
