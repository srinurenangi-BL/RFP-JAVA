package Day14;

public class SortedListMain {
    static class SortedLinkedList<T extends Comparable<T>> {
        Node<T> head;

        static class Node<T> {
            T data;
            Node<T> next;
            Node(T data) { this.data = data; }
        }

        public void addSorted(T data) {
            Node<T> newNode = new Node<>(data);
            // If list is empty or new data is smaller than head
            if (head == null || head.data.compareTo(data) > 0) {
                newNode.next = head;
                head = newNode;
            } else {
                Node<T> current = head;
                // Find the node after which the new node should be inserted
                while (current.next != null && current.next.data.compareTo(data) < 0) {
                    current = current.next;
                }
                newNode.next = current.next;
                current.next = newNode;
            }
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

    public static void main(String[] args) {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();
        list.addSorted(56);
        list.addSorted(30);
        list.addSorted(40);
        list.addSorted(70);

        System.out.print("Sorted Sequence: ");
        list.printList(); // Expected: 30 -> 40 -> 56 -> 70
    }
}