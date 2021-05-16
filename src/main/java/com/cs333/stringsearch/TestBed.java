package com.cs333.stringsearch;

import java.util.ArrayList;

public class TestBed {

    public static void main(String[] args) {
        String fullString = "GCATCGCAGAGAGTATACAGTACGGCAGAGAG";
        String toBeSearched = "GCAGAGAG";

        ArrayList<StringSearchAlgorithm> algorithms = new ArrayList<>();

        algorithms.add(new BruteForce());
        algorithms.add(new KarpRapin());
        algorithms.add(new KnuthMorrisPratt());
        algorithms.add(new ZhuTakaoka());
        algorithms.add(new BoyerMoore());

        for (StringSearchAlgorithm algorithm : algorithms) {
            long startTime = System.nanoTime();
            algorithm.search(toBeSearched, fullString);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println(algorithm.getName() + " duration: " + duration + " nanoseconds\n");//TODO make this less bad
        }
    }
}
