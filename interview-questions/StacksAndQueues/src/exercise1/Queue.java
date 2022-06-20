package exercise1;

/**
 * Exercise 1: Queue with two stacks.
 * Implement a queue with two stacks so that each queue operations
 * takes a constant amortized number of stack operations.
 */
public class Queue<Item> {
    private final Stack<Item> inbox;
    private final Stack<Item> outbox;

    public Queue() {
        this.inbox = new Stack<>();
        this.outbox = new Stack<>();
    }

    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
        queue.enqueue("6");
        System.out.println(queue.dequeue());
        queue.enqueue("7");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.enqueue("8");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }

    // enqueue: simply push new item to queue
    public void enqueue(Item item) {
        this.inbox.push(item);
    }

    // dequeue operation
    public Item dequeue() {
        // if second stack (outbox) is empty, empty first stack (inbox) and fill second stack (outbox)
        // this operation reverses the stack order
        if (outbox.isEmpty())
            while (!inbox.isEmpty()) this.outbox.push(this.inbox.pop());
        // reversed inbox stack in outbox allows us to dequeue
        return this.outbox.pop();
    }
}
