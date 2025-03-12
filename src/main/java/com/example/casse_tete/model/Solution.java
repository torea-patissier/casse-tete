package com.example.casse_tete.model;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(nullable = false)
    private String gridData;

    @Column(nullable = false)
    private Integer result;

    @Column(nullable = false)
    private boolean isCorrect = false;

    @Column(nullable = false)
    private Long duration_in_ms;

}
