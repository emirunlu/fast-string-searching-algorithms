public class ZhuTakaoka implements StringSearchAlgorithm {
	public void search(String partial, String full) {
		String s = full;
		String pat = partial;

		int slen = s.length();
		int patlen = pat.length();

		// Preparing the alphabet
		String alphabet = "";
		for (int i = 0; i < slen; i++) {
			if (s.indexOf(s.charAt(i)) == i) {
				alphabet += s.charAt(i);
			}
		}
		int alphabetSize = alphabet.length();

		// Preprocessing
		int[] patIndexInAlphabet = new int[patlen];
		for (int i = 0; i < patlen; i++) {
			int j = alphabet.indexOf(pat.charAt(i));
			if (j > -1) {
				patIndexInAlphabet[i] = j;
			}
		}
		int ZT[][] = new int[alphabetSize][alphabetSize];
		for (int i = 0; i < alphabetSize; i++) {
			for (int j = 0; j < alphabetSize; j++)
				if (j == patIndexInAlphabet[0]) {
					ZT[i][j] = patlen - 1;
				} else {
					ZT[i][j] = patlen;
				}
		}
		for (int i = 1; i < patlen - 1; i++) {
			ZT[patIndexInAlphabet[i - 1]][patIndexInAlphabet[i]] = patlen - 1 - i;
		}
		int bmGs[] = new int[patlen];
		int suffixes[] = new int[patlen];
		suffixes[patlen - 1] = patlen;
		int g = patlen - 1;
		int f = 0;
		for (int i = patlen - 2; i >= 0; --i) {
			if (i > g && suffixes[i + patlen - 1 - f] < i - g) {
				suffixes[i] = suffixes[i + patlen - 1 - f];
			} else {
				if (i < g) {
					g = i;
				}
				f = i;
				while (g >= 0 && pat.charAt(g) == pat.charAt(g + patlen - 1 - f)) {
					g--;
				}
				suffixes[i] = f - g;
			}
		}
		for (int i = 0; i < patlen; ++i) {
			bmGs[i] = patlen;
		}
		int jj = 0;
		for (int i = patlen - 1; i >= 0; --i)
			if (suffixes[i] == i + 1)
				for (; jj < patlen - 1 - i; jj++)
					if (bmGs[jj] == patlen)
						bmGs[jj] = patlen - 1 - i;
		for (int i = 0; i <= patlen - 2; i++)
			bmGs[patlen - 1 - suffixes[i]] = patlen - 1 - i;

		// Search
		int j = 0;
		while (j <= slen - patlen) {
			int i = patlen - 1;
			while (i > -1 && pat.charAt(i) == s.charAt(i + j)) {
				i--;
			}
			if (i < 0) {
				System.out.println("Index of Result: " + j);
				j += bmGs[0];
			} else {
				int bmGsn = bmGs[i];
				int ztn = ZT[alphabet.indexOf(s.charAt(j + patlen - 2))][alphabet.indexOf(s.charAt(j + patlen - 1))];
				if (bmGsn > ztn) {
					j += bmGsn;
				} else {
					j += ztn;
				}
			}
		}
	}

	public String getName() {
		return "Zhu-Takaoka";
	}
}
