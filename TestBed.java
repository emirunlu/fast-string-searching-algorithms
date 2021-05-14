import java.util.ArrayList;

public class TestBed {
    public static void main(String[] args) {
        String fullString = "GCATCGCAGAGAGTATACAGTACG";
        String toBeSearched = "GCAGAGAG";

        ArrayList<StringSearchAlgorithm> algorithms = new ArrayList<>();

        algorithms.add(new BruteForce());
        algorithms.add(new KarpRapin());
        algorithms.add(new KnuthMorrisPratt());

        for (int i = 0; i < algorithms.size(); i++) {
            long startTime = System.nanoTime();
            algorithms.get(i).search(toBeSearched,fullString);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println(algorithms.get(i).getName()+" duration: "+duration + " nanoseconds");//TODO make this less bad
        }
    }
}
