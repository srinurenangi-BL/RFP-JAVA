package Day14;

public class QueueMain {
    static class Queue<T> {
        private Node<T> head;
        private Node<T> tail;

        static class Node<T> {
            T data;
            Node<T> next;
            Node(T data) { this.data = data; }
        }

        // UC 3: Ability to create a Queue by appending nodes
        public void enqueue(T data) {
            Node<T> newNode = new Node<>(data);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        // UC 4: Ability to dequeue from the beginning
        public void dequeue() {
            if (head == null) {
                System.out.println("Queue is empty.");
                return;
            }
            System.out.println("Dequeuing: " + head.data);
            head = head.next;
            if (head == null) tail = null; // If list becomes empty
        }

        public void printQueue() {
            Node<T> temp = head;
            while (temp != null) {
                System.out.print(temp.data + (temp.next != null ? " -> " : ""));
                temp = temp.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(56);
        queue.enqueue(30);
        queue.enqueue(70);
        
        System.out.print("Queue Sequence: ");
        queue.printQueue();

        queue.dequeue();
        System.out.print("After Dequeue: ");
        queue.printQueue();
    }
}