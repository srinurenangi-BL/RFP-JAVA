package Day15;

public class DataStructureRunner {
    public static void main(String[] args) {
        // --- Hash Table Section ---
        String sentence = "Paranoids are not paranoid because they are paranoid but because they keep putting themselves deliberately into paranoid avoidable situations";
        String[] words = sentence.toLowerCase().split(" ");
        MyHashTable<String, Integer> hashTable = new MyHashTable<>(10);

        for (String word : words) {
            Integer value = hashTable.get(word);
            hashTable.add(word, (value == null) ? 1 : value + 1);
        }
        hashTable.remove("avoidable");
        System.out.println("Frequency Table after removing 'avoidable':\n" + hashTable);

        // --- BST Section ---
        MyBinaryTree<Integer> bst = new MyBinaryTree<>();
        int[] nodes = {56, 30, 70, 22, 40, 60, 95, 11, 65, 3, 16, 63, 67};
        for (int n : nodes) bst.add(n);

        System.out.println("\nBST Size: " + bst.getSize());
        System.out.println("Is 63 present in BST? " + bst.search(63));
    }
}
