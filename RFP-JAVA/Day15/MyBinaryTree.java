package Day15;

public class MyBinaryTree<K extends Comparable<K>> {
    private MyBinaryNode<K> root;

    public void add(K key) {
        this.root = this.addRecursive(root, key);
    }

    private MyBinaryNode<K> addRecursive(MyBinaryNode<K> current, K key) {
        if (current == null) return new MyBinaryNode<>(key);
        int compareResult = key.compareTo(current.key);
        if (compareResult < 0) current.left = addRecursive(current.left, key);
        else if (compareResult > 0) current.right = addRecursive(current.right, key);
        return current;
    }

    public int getSize() {
        return this.getSizeRecursive(root);
    }

    private int getSizeRecursive(MyBinaryNode<K> current) {
        return current == null ? 0 : 1 + this.getSizeRecursive(current.left) + this.getSizeRecursive(current.right);
    }

    public boolean search(K key) {
        return searchRecursive(root, key);
    }

    private boolean searchRecursive(MyBinaryNode<K> current, K key) {
        if (current == null) return false;
        if (current.key.equals(key)) return true;
        return key.compareTo(current.key) < 0 
                ? searchRecursive(current.left, key) 
                : searchRecursive(current.right, key);
    }
}
