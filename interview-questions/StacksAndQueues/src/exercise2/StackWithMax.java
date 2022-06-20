package exercise2;

/**
 * Exercise 2: Stack with max.
 * Create a data structure that efficiently supports the stack operations (push and pop)
 * and also a return-the-maximum operation.
 * Assume the elements are real numbers so that you can compare them.
 */
public class StackWithMax {

    private final Stack<Integer> numbers = new Stack<>();
    private final Stack<Integer> maximums = new Stack<>();

    private static class Node {
        int item;
        Node next;
    }

    public static void main(String[] args) {
        StackWithMax stack = new StackWithMax();

        stack.push(20);
        System.out.println("Maximum: " + stack.max());
        stack.push(19);
        System.out.println("Maximum: " + stack.max());
        stack.push(21);
        System.out.println("Maximum: " + stack.max());
        System.out.println(stack.pop());
        System.out.println("Maximum: " + stack.max());
        stack.push(22);
        System.out.println("Maximum: " + stack.max());
        stack.push(25);
        System.out.println("Maximum: " + stack.max());
        System.out.println(stack.pop());
        System.out.println("Maximum: " + stack.max());
        System.out.println(stack.pop());
        System.out.println("Maximum: " + stack.max());
    }

    // push operation
    public void push(int number) {
        // push item
        this.numbers.push(number);
        // if max stack is empty, no maximum exists, meaning new item is also max item
        if (this.maximums.isEmpty()) {
            this.maximums.push(number);
            return;
        }
        // mas stack is NOT empty: get current max item from stack and push greatest number
        // between this and the new item
        int max = this.max();
        this.maximums.push(Math.max(number, max));
    }

    // pop operation: simply pop from both stacks
    public int pop() {
        this.maximums.pop();
        return this.numbers.pop();
    }

    // since max stack keeps track of current max number, we can simply read from top of max stack
    public int max() {
        return this.maximums.top();
    }
}
