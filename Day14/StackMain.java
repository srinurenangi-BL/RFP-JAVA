package Day14;

public class StackMain {
    // Internal LinkedList Logic for the Stack
    static class Stack<T> {
        private Node<T> head;

        static class Node<T> {
            T data;
            Node<T> next;
            Node(T data) { this.data = data; }
        }

        // UC 1: Ability to create a Stack by pushing nodes
        public void push(T data) {
            Node<T> newNode = new Node<>(data);
            if (head != null) {
                newNode.next = head;
            }
            head = newNode;
        }

        // UC 2: Ability to peak and pop from the Stack till it is empty
        public void pop() {
            if (head == null) {
                System.out.println("Stack is empty.");
                return;
            }
            System.out.println("Popping: " + head.data);
            head = head.next;
        }

        public T peak() {
            return (head != null) ? head.data : null;
        }

        public void printStack() {
            Node<T> temp = head;
            while (temp != null) {
                System.out.print(temp.data + (temp.next != null ? " -> " : ""));
                temp = temp.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(70);
        stack.push(30);
        stack.push(56);
        
        System.out.print("Stack Sequence: ");
        stack.printStack();

        System.out.println("Top element (Peak): " + stack.peak());
        
        while (stack.peak() != null) {
            stack.pop();
        }
    }
}
