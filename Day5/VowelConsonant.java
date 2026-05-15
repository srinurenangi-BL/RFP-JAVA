package Day5;

public class VowelConsonant {
    public static void main(String[] args) {
        char ch = 'e';
        ch = Character.toLowerCase(ch);
        
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
            System.out.println(ch + " is a Vowel");
        } else if (ch >= 'a' && ch <= 'z') {
            System.out.println(ch + " is a Consonant");
        } else {
            System.out.println("Not an alphabet");
        }
    }
}