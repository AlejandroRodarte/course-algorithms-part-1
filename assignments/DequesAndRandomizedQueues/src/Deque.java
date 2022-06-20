import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    // pointer to the first node in the list
    private Node first = null;
    // pointer to the last node in the list
    private Node last = null;
    // numeric tracker of the current list size
    private int size = 0;

    // constructor: initialize empty list
    public Deque() { }

    // custom node class for this data structure
    private class Node {
        // arbitrary item that the node holds
        Item item;
        // reference to the next node in the list
        Node next;
        // reference to the previous node in the list
        Node previous;
    }

    // front-to-back iterator for the deque data structure
    private class FrontToBackIterator implements Iterator<Item> {
        // reference to the first node in the list
        Node current = first;

        // there is an item to show if the current node is not null
        @Override
        public boolean hasNext() {
            return this.current != null;
        }

        // get current node item
        @Override
        public Item next() {
            if (this.current == null) throw new NoSuchElementException("No more items to loop through.");

            // store pointer to current node item
            Item item = this.current.item;
            // move pointer to the next node in the list
            this.current = this.current.next;
            return item;
        }

        // remove() not supported
        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove() not supported.");
        }
    }

    // list is empty: size of list is 0
    public boolean isEmpty() { return this.size == 0; }

    // size of list: return size numeric tracker
    public int size() {
        return this.size;
    }

    // add new node to the front of the list
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Item can not be null.");

        // check if list was empty before inserting
        boolean wasEmpty = this.isEmpty();

        // store pointer to current first node in list (let it be called "old first node")
        Node oldFirst = this.first;

        // create new node; set item (let it be called "new first node")
        this.first = new Node();
        this.first.item = item;
        // set new first node next/previous references:
        // <- means previous reference
        // -> means next reference
        // (null) <- (new first node) -> (old first node)
        this.first.next = oldFirst;
        this.first.previous = null;

        // increment current size
        this.size++;

        // list was empty before inserting: it's our first node in the list
        // set last node pointer equal to the first node pointer
        if (wasEmpty)
            this.last = this.first;
        // list was NOT empty before inserting: it's our nth node in the list
        // <- means previous reference
        // -> means next reference
        // (new first node) <- (old first node)
        else
            oldFirst.previous = this.first;
    }

    // add new node to the back of the list
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Item can not be null.");

        // check if list was empty before inserting
        boolean wasEmpty = this.isEmpty();

        // store pointer to current last node in list (let it be called "old last node")
        Node oldLast = this.last;

        // create new node; set item (let it be called "new last node")
        this.last = new Node();
        this.last.item = item;
        // set new last node next/previous references:
        // <- means previous reference
        // -> means next reference
        // (old last node) <- (new last node) -> (null)
        this.last.next = null;
        this.last.previous = oldLast;

        // increase list size
        this.size++;

        // list was empty before inserting: it's our first node in the list
        // set first node pointer equal to the last node pointer
        if (wasEmpty)
            this.first = this.last;
        // list was NOT empty before inserting: it's our nth node in the list
        // <- means previous reference
        // -> means next reference
        // (old last node) -> (new last node)
        else
            oldLast.next = this.last;
    }

    // remove item from front of the list
    public Item removeFirst() {
        if (this.isEmpty()) throw new NoSuchElementException("Deque is empty.");

        // store pointer to current first node item (let it be called "old first node")
        Item item = this.first.item;
        // move first node pointer forward (next node in the list, let it be called "new first node")
        this.first = this.first.next;

        // decrease list size
        this.size--;

        // list is NOT empty after operation: there is at least 1 node in the list
        // (null; old first node that was just removed) <- (new first node)
        if (!this.isEmpty())
            this.first.previous = null;
        // list is empty after operation: we had only one node in the list
        // (first and last node pointers point to the same node)
        // we need to set last node pointer to null to avoid loitering
        else
            this.last = this.first;

        return item;
    }

    // remove item from back of the list
    public Item removeLast() {
        if (this.isEmpty()) throw new NoSuchElementException("Deque is empty.");

        // store pointer to current last node item (let it be called "old last node")
        Item item = this.last.item;
        // move last node pointer backward
        // (previous node in the list, now the true last node, let it be called "new first node")
        this.last = this.last.previous;

        // decrease list size
        this.size--;

        // list is NOT empty after operation: there is at least 1 node in the list
        // (new last node) -> (null; old last node that was just removed)
        if (!this.isEmpty())
            this.last.next = null;
        // list is empty after operation: we had only one node in the list
        // (first and last node pointers point to the same node)
        // we need to set first node pointer to null to avoid loitering
        else
            this.first = this.last;

        return item;
    }

    // iterator: instance of our custom front-to-back iterator
    @Override
    public Iterator<Item> iterator() {
        return new FrontToBackIterator();
    }

    // unit tests of our data structure
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        System.out.println("isEmpty: " + deque.isEmpty());

        deque.addFirst("1");
        deque.addLast("2");
        deque.addFirst("3");
        deque.addLast("4");

        System.out.println("isEmpty: " + deque.isEmpty());
        System.out.println("size: " + deque.size());

        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeLast());

        System.out.println("isEmpty: " + deque.isEmpty());
        System.out.println("size: " + deque.size());

        deque.addLast("5");
        deque.addFirst("6");
        deque.addFirst("7");

        System.out.println("isEmpty: " + deque.isEmpty());
        System.out.println("size: " + deque.size());

        Iterator<String> iterator = deque.iterator();

        System.out.println("Printing current items (front-to-back):");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        System.out.println(deque.removeLast());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeFirst());

        System.out.println("isEmpty: " + deque.isEmpty());
        System.out.println("size: " + deque.size());

        System.out.println(deque.removeFirst());

        System.out.println("isEmpty: " + deque.isEmpty());
        System.out.println("size: " + deque.size());
    }
}
