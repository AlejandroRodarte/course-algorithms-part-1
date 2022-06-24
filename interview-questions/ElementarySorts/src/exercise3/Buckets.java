package exercise3;

// buckets class that stores an array of pebbles
public class Buckets {
    private final Pebble[] pebbles;
    private boolean sorted = false;
    public int swapCalls = 0;
    public int colorCalls = 0;

    public Buckets(Pebble[] pebbles) {
        this.pebbles = pebbles;
    }

    // get pebble color
    public Pebble color(int i) {
        return this.pebbles[i];
    }

    // swap pebbles
    public void swap(int i, int j) {
        Pebble swap = this.pebbles[j];
        this.pebbles[j] = this.pebbles[i];
        this.pebbles[i] = swap;
    }

    public void sort() {
        if (this.sorted) return;

        // three index pointers
        // pointer: sweeps the array until it reaches the current tail
        // head: keeps track of where the last RED pebble was stored
        // tail: keeps track of where the last BLUE pebble was stored
        int pointer = 0;
        int head = 0;
        int tail = this.pebbles.length - 1;

        while (pointer <= tail) {
            this.colorCalls++;
            Pebble pebble = color(pointer);
            switch (pebble) {
                // if pebble is white, do nothing; move to next pebble
                case WHITE:
                    pointer++;
                    break;
                // if pebble is RED, swap pebble[pointer] with pebble[head]
                // pebble[head] is always WHITE as BLUE pebbles would get sent to the end of the array
                // knowing this allows us to move to the next pebble
                case RED:
                    this.swapCalls++;
                    swap(pointer++, head++);
                    break;
                // is pebble is BLUE, swap pebble[pointer] with pebble[tail]
                // pebble[tail], in this case, can be either RED or WHITE, so we can NOT
                // move to the next pebble yet
                case BLUE:
                    this.swapCalls++;
                    swap(pointer, tail--);
                    break;
                default:
                    break;
            }
        }

        this.sorted = true;
    }

    public Pebble[] getPebbles() {
        return pebbles;
    }
}
