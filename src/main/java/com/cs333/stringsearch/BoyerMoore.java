package com.cs333.stringsearch;

import java.util.Arrays;

public class BoyerMoore implements StringSearchAlgorithm {

    private static final String Name = "Boyer-Moore";

    @Override
    public void search(String partial, String full) {
        int patLength = partial.length();
        int fullLength = full.length();
        var pat = partial.toCharArray();
        var txt = full.toCharArray();

        if (patLength > fullLength)
            return;

        int[] badCharArray = new int[256];
        badCharacterHeuristic(pat, patLength, badCharArray);

        int shiftPattern = 0;
        do {
            int j = patLength - 1;

            while (j >= 0 && pat[j] == txt[shiftPattern + j]) {
                j--;
            }

            if (j < 0) {
                System.out.println(Name + " - Found instance of " + partial + " starting at character " + (shiftPattern + 1));
                shiftPattern += (shiftPattern + patLength < fullLength) ? patLength + 1 : 1;
            } else
                shiftPattern += Math.max(1, j - badCharArray[txt[shiftPattern + j]]);
        } while (shiftPattern <= (fullLength - patLength));

    }

    private void badCharacterHeuristic(char[] str, int length, int[] badCharArray) {
        Arrays.fill(badCharArray, -1);

        for (int i = 0; i < length; i++)
            badCharArray[str[i]] = i;
    }

    @Override
    public String getName() {
        return Name;
    }
}
