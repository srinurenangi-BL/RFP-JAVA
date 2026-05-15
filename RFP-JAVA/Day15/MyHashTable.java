package Day15;

import java.util.ArrayList;

public class MyHashTable<K, V> {
    private final int numBuckets;
    ArrayList<MyMapNode<K, V>> bucketArray;

    public MyHashTable(int numBuckets) {
        this.numBuckets = numBuckets;
        this.bucketArray = new ArrayList<>(numBuckets);
        for (int i = 0; i < numBuckets; i++) {
            this.bucketArray.add(null);
        }
    }

    private int getBucketIndex(K key) {
        int hashCode = Math.abs(key.hashCode());
        return hashCode % numBuckets;
    }

    public V get(K key) {
        int index = this.getBucketIndex(key);
        MyMapNode<K, V> head = this.bucketArray.get(index);
        while (head != null) {
            if (head.key.equals(key)) return head.value;
            head = head.next;
        }
        return null;
    }

    public void add(K key, V value) {
        int index = this.getBucketIndex(key);
        MyMapNode<K, V> head = this.bucketArray.get(index);
        MyMapNode<K, V> temp = head;
        while (temp != null) {
            if (temp.key.equals(key)) {
                temp.value = value;
                return;
            }
            temp = temp.next;
        }
        MyMapNode<K, V> newNode = new MyMapNode<>(key, value);
        newNode.next = head;
        this.bucketArray.set(index, newNode);
    }

    public void remove(K key) {
        int index = this.getBucketIndex(key);
        MyMapNode<K, V> head = this.bucketArray.get(index);
        MyMapNode<K, V> prev = null;
        while (head != null) {
            if (head.key.equals(key)) break;
            prev = head;
            head = head.next;
        }
        if (head == null) return;
        if (prev != null) prev.next = head.next;
        else this.bucketArray.set(index, head.next);
    }

    @Override
    public String toString() {
        return "HashTable{" + bucketArray + "}";
    }
}
