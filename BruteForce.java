public class BruteForce implements StringSearchAlgorithm {
    @Override
    public void search(String phrase, String fullText) {
        int phraseLen = phrase.length();
        int fullLen = fullText.length();
        for (int i = 0; i+phraseLen < fullLen; i++) {
            if(fullText.substring(i,i+phraseLen).equals(phrase)){
                System.out.println("found instance of "+phrase+" starting at character "+i);
                break;
            }
        }
    }

    @Override
    public String getName() {
        return "Brute Force";
    }
}
