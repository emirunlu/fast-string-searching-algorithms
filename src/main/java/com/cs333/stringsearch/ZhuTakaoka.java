package com.cs333.stringsearch;

public class ZhuTakaoka implements StringSearchAlgorithm {

    private static final String Name = "Zhu-Takaoka";

    @Override
    public void search(String partial, String full) {

        int sLen = full.length();
        int patLen = partial.length();

        // Preparing the alphabet
        String alphabet = "";
        for (int i = 0; i < sLen; i++) {
            if (full.indexOf(full.charAt(i)) == i) {
                alphabet += full.charAt(i);
            }
        }
        int alphabetSize = alphabet.length();

        // Preprocessing
        int[] patIndexInAlphabet = new int[patLen];
        for (int i = 0; i < patLen; i++) {
            int j = alphabet.indexOf(partial.charAt(i));
            if (j > -1) {
                patIndexInAlphabet[i] = j;
            }
        }
        int[][] ZT = new int[alphabetSize][alphabetSize];
        for (int i = 0; i < alphabetSize; i++) {
            for (int j = 0; j < alphabetSize; j++)
                if (j == patIndexInAlphabet[0]) {
                    ZT[i][j] = patLen - 1;
                } else {
                    ZT[i][j] = patLen;
                }
        }
        for (int i = 1; i < patLen - 1; i++) {
            ZT[patIndexInAlphabet[i - 1]][patIndexInAlphabet[i]] = patLen - 1 - i;
        }
        int[] bmGs = new int[patLen];
        int[] suffixes = new int[patLen];
        suffixes[patLen - 1] = patLen;
        int g = patLen - 1;
        int f = 0;
        for (int i = patLen - 2; i >= 0; --i) {
            if (i > g && suffixes[i + patLen - 1 - f] < i - g) {
                suffixes[i] = suffixes[i + patLen - 1 - f];
            } else {
                if (i < g) {
                    g = i;
                }
                f = i;
                while (g >= 0 && partial.charAt(g) == partial.charAt(g + patLen - 1 - f)) {
                    g--;
                }
                suffixes[i] = f - g;
            }
        }
        for (int i = 0; i < patLen; ++i) {
            bmGs[i] = patLen;
        }
        int jj = 0;
        for (int i = patLen - 1; i >= 0; --i)
            if (suffixes[i] == i + 1)
                for (; jj < patLen - 1 - i; jj++)
                    if (bmGs[jj] == patLen)
                        bmGs[jj] = patLen - 1 - i;
        for (int i = 0; i <= patLen - 2; i++)
            bmGs[patLen - 1 - suffixes[i]] = patLen - 1 - i;

        // Search
        int j = 0;
        while (j <= sLen - patLen) {
            int i = patLen - 1;
            while (i > -1 && partial.charAt(i) == full.charAt(i + j)) {
                i--;
            }
            if (i < 0) {
                System.out.println(Name + " - Found instance of " + partial + " starting at character " + (j + 1));
                j += bmGs[0];
            } else {
				int bmGsn = bmGs[i];
				if (j + patlen - 2 > -1) {
					int ztn = ZT[alphabet.indexOf(s.charAt(j + patlen - 2))][alphabet
							.indexOf(s.charAt(j + patlen - 1))];
					if (bmGsn > ztn) {
						j += bmGsn;
					} else {
						j += ztn;
					}
				} else {
					j += bmGsn;
				}
			}
        }
    }

    @Override
    public String getName() {
        return Name;
    }
}
