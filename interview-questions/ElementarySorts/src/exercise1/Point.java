package exercise1;

// 2D-coordinate model
public class Point implements Comparable<Point> {
    // x-y coordinates
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // comparing points
    @Override
    public int compareTo(Point that) {
        if (this.isSmaller(that)) return -1;
        if (this.isEqual(that)) return 0;
        return 1;
    }

    // determine if point A is equal to point B
    public boolean isEqual(Point that) {
        return this.x == that.x && this.y == that.y;
    }

    // determine if point A is smaller than point B
    public boolean isSmaller(Point that) {
        return this.x < that.x || (this.x == that.x && this.y < that.y);
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
