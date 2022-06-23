package exercise1;

import edu.princeton.cs.algs4.Shell;

/**
 * Question 1
 * Exercise 1: Intersection of two sets.
 * Given two arrays a[] and b[],
 * each containing nnn distinct 2D points in the plane,
 * design a sub-quadratic algorithm to count the number of points
 * that are contained both in array a[] and array b[].
 */
public class Main {
    public static void main(String[] args) {
        // 1. define a[]
        Point[] a = new Point[]{
            new Point(5, 4),
            new Point(3, 2),
            new Point(8, 6),
            new Point(5, 4),
            new Point(1, 2),
            new Point(1, 8),
            new Point(4, 5),
            new Point(5, 9)
        };

        // 2. define b[]
        Point[] b = new Point[]{
            new Point(4, 7),
            new Point(1, 7),
            new Point(3, 2),
            new Point(1, 2),
            new Point(5, 6),
            new Point(8, 4),
            new Point(3, 5),
            new Point(8, 6)
        };

        // 3. sort a[] and b[] with shell-sort
        Shell.sort(a);
        Shell.sort(b);

        // definer outer and inner array for outer and inner for loops
        // outer array should be the array with the LOWEST amount of items
        Point[] outerArr = a.length < b.length ? a : b;
        Point[] innerArr = a.length < b.length ? b : a;

        // define intersection output array
        Point[] intersection = new Point[outerArr.length];

        // inner pointer array to avoid evaluating duplicate coordinates
        int pointer = 0;

        // outer loop sweeps entire array in search of intersected elements in inner array
        for (int i = 0; i < outerArr.length; i++) {
            // inner loop starts in current pointer value, not index 0
            for (int j = pointer; j < innerArr.length; j++) {
                // intersection found; set coordinate on output, increment pointer and exit
                if (innerArr[j].isEqual(outerArr[i])) {
                    intersection[i] = outerArr[i];
                    pointer++;
                    break;
                }
                // intersection not found, yet inner coordinate is smaller than outer coordinate
                // move pointer as inner coordinate should never be evaluated again
                else if (innerArr[j].isSmaller(outerArr[i])) {
                    pointer++;
                    continue;
                }
                // intersection not found, yet inner coordinate is bigger than outer coordinate
                // break inner for loop and continue with next outer coordinate
                else break;
            }
        }

        for (Point point : intersection) {
            if (point != null)
                System.out.println("(" + point.getX() + ", " + point.getY() + ")");
        }
    }
}
