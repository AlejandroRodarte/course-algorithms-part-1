import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q = (Item[]) new Object[1];
    // we only need to track queue's tail as we are swapping last item to first index (in case it happens)
    private int tail = 0;
    private int itemCount = 0;

    public RandomizedQueue() { }

    // random iterator implementation
    private class RandomIterator implements Iterator<Item> {
        // start at queue's head
        private int currentIndex = 0;
        private final Item[] array;

        // when iterator is called, we copy the randomized queue and shuffle the copy
        public RandomIterator() {
            this.array = (Item[]) new Object[itemCount];
            for (int i = 0; i < itemCount; i++) this.array[i] = q[i];
            StdRandom.shuffle(this.array);
        }

        // there is an item to render if current pointer is not at the end of the queue
        @Override
        public boolean hasNext() {
            return currentIndex != tail;
        }

        // there is a next item if current pointer is not at the end of the copied queue
        @Override
        public Item next() {
            if (currentIndex == tail) throw new NoSuchElementException("No more items to return.");
            return this.array[currentIndex++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove() not supported.");
        }
    }

    // queue is empty if item count is 0
    public boolean isEmpty() {
        return this.itemCount == 0;
    }

    // size = itemCount field
    public int size() { return this.itemCount; }

    // queueing works the same as the implementation we made lectures back
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Item can not be null.");
        this.q[this.tail++] = item;
        this.itemCount++;
        if (this.tail == this.q.length) this.resize(2 * this.q.length);
    }

    // dequeueing a random item
    public Item dequeue() {
        if (this.isEmpty()) throw new NoSuchElementException("Queue is empty.");
        // choose random index [0, tail); remember, q[tail] = null, so we need to choose
        // between 0 and (tail - 1)
        int randomIndex = StdRandom.uniform(0, this.tail);
        Item item = this.q[randomIndex];
        this.q[randomIndex] = null;
        // decrease item count
        this.itemCount--;
        // if there are still items to dequeue...
        if (this.itemCount > 0) {
            // move last item in queue to dequeued item's index
            Item lastItem = this.q[this.tail - 1];
            this.q[randomIndex] = lastItem;
            this.q[this.tail - 1] = null;
            if (this.itemCount == (this.q.length / 4))
                this.resize(this.q.length / 2);
        }
        this.tail--;
        return item;
    }

    // sample(): return random item from queue, yet not remove it
    public Item sample() {
        if (this.isEmpty()) throw new NoSuchElementException("Queue is empty.");
        int randomIndex = StdRandom.uniform(0, this.tail);
        return this.q[randomIndex];
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < this.itemCount; i++) copy[i] = this.q[i];
        this.q = copy;
    }

    // unit tests; call all methods
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();

        queue.enqueue("855");
        System.out.println(queue.dequeue());
        queue.enqueue("122");
        queue.enqueue("249");
        queue.enqueue("299");
        System.out.println(queue.dequeue());

        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext())
            System.out.print(iterator.next() + ", ");
        Iterator<String> iterator2 = queue.iterator();
        while (iterator2.hasNext())
            System.out.print(iterator2.next() + ", ");
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }
}
