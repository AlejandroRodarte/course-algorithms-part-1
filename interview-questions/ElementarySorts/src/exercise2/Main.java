package exercise2;

import edu.princeton.cs.algs4.Shell;

/**
 * Exercise 2: Permutation.
 * Given two integer arrays of size n, design a sub-quadratic algorithm
 * to determine whether one is a permutation of the other.
 * That is, do they contain exactly the same entries but, possibly, in a different order.
 */
public class Main {
    public static void main(String[] args) {
        // 1. define the two arrays
        Integer[] a = new Integer[]{ 7, 4, 9, 2, 10 };
        Integer[] b = new Integer[]{ 10, 4, 7, 9, 2 };

        // 2. sort them
        Shell.sort(a);
        Shell.sort(b);

        boolean isPermutation = true;

        // 3. if arrays are of different length, permutation is impossible
        if (a.length != b.length) isPermutation = false;
        else {
            // 4. check for array equality; set permutation flag to false at first wrong value
            for (int i = 0;i < a.length; i++)
                if (!a[i].equals(b[i])) {
                    isPermutation = false;
                    break;
                };
        }

        System.out.println("isPermutation: " + isPermutation);
    }
}
