package exercise3;

/**
 * Question 3: Dutch national flag. Given an array of n buckets,
 * each containing a red, white, or blue pebble, sort them by color.
 * The allowed operations are:
 * swap(i,j):  swap the pebble in bucket iii with the pebble in bucket jjj.
 * color(i): determine the color of the pebble in bucket iii.
 * The performance requirements are as follows:
 * At most n calls to color().
 * At most n calls to swap().
 * Constant extra space.
 */
public class Main {
    public static void main(String[] args) {
        Buckets buckets = new Buckets(new Pebble[]{
            Pebble.WHITE,
            Pebble.BLUE,
            Pebble.RED,
            Pebble.RED,
            Pebble.WHITE,
            Pebble.BLUE,
            Pebble.BLUE,
            Pebble.WHITE,
            Pebble.RED,
            Pebble.RED,
            Pebble.WHITE,
            Pebble.RED,
            Pebble.BLUE,
            Pebble.WHITE,
            Pebble.RED,
            Pebble.RED,
            Pebble.WHITE,
            Pebble.BLUE,
            Pebble.WHITE,
            Pebble.WHITE
        });
        buckets.sort();
        System.out.println("pebbles: " + buckets.getPebbles().length);
        System.out.println("swap() calls: " + buckets.swapCalls);
        System.out.println("color() calls: " + buckets.colorCalls);
        for (Pebble pebble : buckets.getPebbles())
            System.out.println(pebble);
    }
}
