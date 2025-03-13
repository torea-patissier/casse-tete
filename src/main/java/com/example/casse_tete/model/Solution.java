package com.example.casse_tete.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="Solutions")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Solution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private List<Integer> gridData;
    private Integer result;
    private boolean isCorrect = false;
}
