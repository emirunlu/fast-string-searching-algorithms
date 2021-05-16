package com.cs333.stringsearch;

public class KarpRapin implements StringSearchAlgorithm {

    private static final String Name = "Karp-Rapin";

    private static final int prime = 10000019;

    private static int hash(String word) {
        int m = word.length();
        int hashValue;
        int sum = 0;

        for (int i = 0; i < m; i++) {
            sum += (int) word.charAt(i) * Math.pow(2, m - i - 1);
        }
        hashValue = sum % prime;
        return hashValue;
    }

    private static int rehash(char a, char b, int h, int m) {
        return (int) ((h - (int) a * Math.pow(2, m - 1)) * 2 + b) % prime;
    }

    private static boolean checkCharByChar(String first, String second) {
        return first.equals(second);
    }

    @Override
    public void search(String phrase, String fullText) {
        int n = fullText.length();
        int m = phrase.length();

        int hashToBeSearched = hash(phrase);
        int fullSubHash;
        String nextSub;
        String prevSub;

        for (int i = 0; i < n - m + 1; i++) {
            String substring = fullText.substring(i, i + m);
            if (i == 0) {
                nextSub = substring;
                fullSubHash = hash(nextSub);
                if (hashToBeSearched == fullSubHash) {
                    if (checkCharByChar(phrase, nextSub)) {
                        System.out.println(Name + " - Found instance of " + phrase + " starting at character " + (i + 1));
                    }
                }
            } else {
                prevSub = fullText.substring(i - 1, i + m - 1);
                nextSub = substring;
                fullSubHash = hash(prevSub);
                if (hashToBeSearched == rehash(fullText.charAt(i - 1), nextSub.charAt(nextSub.length() - 1), fullSubHash, m)) {
                    if (checkCharByChar(phrase, nextSub)) {
                        System.out.println(Name + " - Found instance of " + phrase + " starting at character " + (i + 1));
                    }
                }
            }
        }
    }

    @Override
    public String getName() {
        return Name;
    }
}
