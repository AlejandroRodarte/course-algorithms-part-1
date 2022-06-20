package exercise2;

import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {
    private Node first = null;

    private class Node {
        Item item;
        Node next;
    }

    private class ListIterator implements Iterator<Item> {
        Node current = first;

        public boolean hasNext() {
            return this.current.next != null;
        }

        public Item next() {
            Item item = this.current.item;
            this.current = this.current.next;
            return item;
        }
    }

    public boolean isEmpty() {
        return this.first == null;
    }

    public void push(Item item) {
        Node oldFirst = this.first;
        this.first = new Node();
        this.first.item = item;
        this.first.next = oldFirst;
    }

    public Item pop() {
        Item item = this.first.item;
        this.first = this.first.next;
        return item;
    }

    public Item top() {
        return this.first.item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }
}
