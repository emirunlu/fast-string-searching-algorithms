public class KarpRapin {

    public static final int prime = 100003;

    public static void main(String args[]) {
        String fullString = "GCATCGCAGAGAGTATACAGTACG";
        String toBeSearched = "GCAGAGAG";

        int n = fullString.length();
        int m = toBeSearched.length();

        int hashToBeSearched = hash(toBeSearched);
        int fullSubHash = 0;
        String nextSub = "";
        String prevSub = "";

        for (int i = 0; i < n - m; i++) {
            if (i == 0) {
                nextSub = fullString.substring(i, i + m);
                fullSubHash = hash(nextSub);
                if (hashToBeSearched == fullSubHash) {
                    if (checkCharByChar(toBeSearched, nextSub)) {
                        System.out.println("Match found in " + (i + 1) + " iteration");
                    }
                }
            } else {
                prevSub = fullString.substring(i - 1, i + m - 1);
                nextSub = fullString.substring(i, i + m);
                fullSubHash = hash(prevSub);
                if (hashToBeSearched == rehash(fullString.charAt(i - 1), nextSub.charAt(nextSub.length() - 1), fullSubHash, m)) {
                    if (checkCharByChar(toBeSearched, nextSub)) {
                        System.out.println("Match found in " + (i + 1) + " iteration");
                    }
                }
            }
        }
    }
    static int hash(String word) {
        int m = word.length();
        int hashValue = 0;
        int sum = 0;

        for (int i = 0; i < m; i++) {
            sum += (int) word.charAt(i) * Math.pow(2, m - i - 1);
        }
        hashValue = sum % prime;
        return hashValue;
    }

    static int rehash(char a, char b, int h, int m) {
        return (int) ((h - (int) a * Math.pow(2, m - 1)) * 2 + b) % prime;
    }

    static boolean checkCharByChar(String first, String second) {
        return first.equals(second);
    }
}
