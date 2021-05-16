package com.cs333.stringsearch;

public class BruteForce implements StringSearchAlgorithm {

    private static final String Name = "Brute-Force";

    @Override
    public void search(String phrase, String fullText) {
        int phraseLen = phrase.length();
        int fullLen = fullText.length();
        for (int i = 0; i + phraseLen < fullLen; i++) {
            if (fullText.substring(i, i + phraseLen).equals(phrase)) {
                System.out.println(Name + " - Found instance of " + phrase + " starting at character " + (i + 1) );
            }
        }
    }

    @Override
    public String getName() {
        return Name;
    }
}
