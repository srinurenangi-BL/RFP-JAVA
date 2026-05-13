package Day14.Stacks_and_Queue;

public class Stack<T> {
    private final MyLinkedList<T> linkedList;

    public Stack() {
        this.linkedList = new MyLinkedList<>();
    }

    // UC 1: Push internally calls add()
    public void push(T data) {
        linkedList.add(data);
    }

    // UC 2: Peak and Pop
    public T peek() {
        return linkedList.peek();
    }

    public void pop() {
        linkedList.pop();
    }

    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    public void display() {
        linkedList.printList();
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(70);
        stack.push(30);
        stack.push(56);
        System.out.print("Stack Sequence (Top to Bottom): ");
        stack.display(); // 56 -> 30 -> 70

        System.out.println("Beginning Pop operations until empty:");
        while (!stack.isEmpty()) {
            System.out.println("Peak: " + stack.peek());
            stack.pop();
        }
    }
}