package Day14.Stacks_and_Queue;

class MyLinkedList<T> {
    Node<T> head;

    static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) { this.data = data; }
    }

    // Adds to the front (Used for Stack Push)
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
    }

    // Adds to the end (Used for Queue Enqueue)
    public void append(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    // Removes from the front (Used for Stack Pop and Queue Dequeue)
    public T pop() {
        if (head == null) return null;
        T data = head.data;
        head = head.next;
        return data;
    }

    public T peek() {
        return (head != null) ? head.data : null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void printList() {
        Node<T> temp = head;
        while (temp != null) {
            System.out.print(temp.data + (temp.next != null ? " -> " : ""));
            temp = temp.next;
        }
        System.out.println();
    }
}
