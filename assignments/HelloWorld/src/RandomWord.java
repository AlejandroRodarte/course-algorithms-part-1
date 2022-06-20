import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String currentChampion = "";
        for (int i = 1; !StdIn.isEmpty(); i++) {
            String word = StdIn.readString();
            double probability = 1.0 / i;
            boolean willBecomeChampion = StdRandom.bernoulli(probability);
            if (willBecomeChampion) currentChampion = word;
        }
        System.out.println(currentChampion);
    }
}
