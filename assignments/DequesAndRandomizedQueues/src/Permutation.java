import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        int printAmount = Integer.parseInt(args[0]);
        int wordCount = 0;

        while (!StdIn.isEmpty()) {
            wordCount++;
            String word = StdIn.readString();
            queue.enqueue(word);
        }

        if (printAmount >= 0 && printAmount <= wordCount)
            for (int i = printAmount; i > 0; i--)
                System.out.println(queue.dequeue());
        else throw new IllegalArgumentException("Print amount should be between 0 and the word count.");
    }
}
