package Day14.Stacks_and_Queue;

public class Queue<T> {
    private final MyLinkedList<T> linkedList;

    public Queue() {
        this.linkedList = new MyLinkedList<>();
    }

    // UC 3: Enqueue internally calls append()
    public void enqueue(T data) {
        linkedList.append(data);
    }

    // UC 4: Dequeue removes from the beginning
    public void dequeue() {
        linkedList.pop();
    }

    public void display() {
        linkedList.printList();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(56);
        queue.enqueue(30);
        queue.enqueue(70);
        System.out.print("Queue Sequence: ");
        queue.display(); // 56 -> 30 -> 70

        System.out.println("Performing Dequeue...");
        queue.dequeue();
        queue.display(); // 30 -> 70
    }
}
