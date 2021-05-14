public class KnuthMorrisPratt implements StringSearchAlgorithm{

    @Override
    public void search(String partial, String full) {
        int fullLen = full.length();
        int[] lps = preprocessKMP(partial);

        int pi = 0;
        int fi = 0;
        while (fi < fullLen){
            if(partial.charAt(pi) == full.charAt(fi)){
                fi++;
                pi++;
            }
            if(pi == partial.length()){
                System.out.println("found instance of "+partial+" starting at character "+(fi-pi));
                pi = lps[pi - 1];
            }
            else if (fi < fullLen && partial.charAt(pi) != full.charAt(fi)) {
                if (pi != 0)
                    pi = lps[pi - 1];
                else
                    fi = fi + 1;
            }
        }
    }

    public int[] preprocessKMP(String partial){
        int len = 0;
        int phraseLen = partial.length();
        int[] lps = new int[phraseLen]; //Longest Proper Prefix
        lps[0]=0;

        int i = 1;
        while (i < phraseLen){
            if (partial.charAt(i)==partial.charAt(len)){
                len++;
                lps[i] = len;
                i++;
            }else{
                if(len!=0){
                    len = lps[len - 1];
                }else{
                    lps[i] = len;
                    i++;
                }
            }
        }
        return lps;
    }

    @Override
    public String getName() {
        return "Knuth-Morris-Pratt";
    }
}

