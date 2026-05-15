import java.util.Random;

public class DeckOfCards {
    public static void main(String[] args) {
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        String[] deck = new String[52];

        // Initialize deck
        for (int i = 0; i < deck.length; i++) {
            deck[i] = ranks[i % 13] + " of " + suits[i / 13];
        }

        // Shuffle
        Random rand = new Random();
        for (int i = 0; i < deck.length; i++) {
            int r = i + rand.nextInt(52 - i);
            String temp = deck[r];
            deck[r] = deck[i];
            deck[i] = temp;
        }

        // Distribute to 2D Array [Players][Cards]
        String[][] players = new String[4][9];
        int count = 0;
        for (int p = 0; p < 4; p++) {
            System.out.print("\nPlayer " + (p + 1) + ": ");
            for (int c = 0; c < 9; c++) {
                players[p][c] = deck[count++];
                System.out.print(players[p][c] + " | ");
            }
        }
    }
}