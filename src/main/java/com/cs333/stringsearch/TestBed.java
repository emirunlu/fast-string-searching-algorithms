package com.cs333.stringsearch;

import java.util.ArrayList;

public class TestBed {

    public static void main(String[] args) {
        String fullString = "GCATCGCAGAGAGTATACAGTACGGCAGAGAG";
        String toBeSearched = "CGCAGAGAGTATACAGTA";

        ArrayList<StringSearchAlgorithm> algorithms = new ArrayList<>();

        algorithms.add(new BruteForce());
        algorithms.add(new KarpRapin());
        algorithms.add(new KnuthMorrisPratt());
        algorithms.add(new ZhuTakaoka());
        algorithms.add(new BoyerMoore());

        for (StringSearchAlgorithm algorithm : algorithms) {
            try {
                long startTime = System.nanoTime();
                algorithm.search(toBeSearched, fullString);
                long endTime = System.nanoTime();
                float duration = (endTime - startTime) / 1000000.0f;
                System.out.println(algorithm.getName() + " duration: " + duration + " milliseconds\n");//TODO make this less bad
            } catch (Exception e) {
                System.out.println(algorithm.getName() + " has failed\n");
            }
        }
    }
}
