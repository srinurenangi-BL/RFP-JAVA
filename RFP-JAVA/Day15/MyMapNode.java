package Day15;

public class MyMapNode<K, V> {
    K key;
    V value;
    MyMapNode<K, V> next;

    public MyMapNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    @Override
    public String toString() {
        StringBuilder nodeString = new StringBuilder();
        nodeString.append("{K=").append(key).append(", V=").append(value).append("}");
        if (next != null) nodeString.append("->").append(next);
        return nodeString.toString();
    }
}
