package Day4;
public class SnakeAndLadderSimulator {

    // Constants for the Options (UC 3)
    public static final int NO_PLAY = 0;
    public static final int LADDER = 1;
    public static final int SNAKE = 2;
    public static final int WINNING_POSITION = 100;

    public static void main(String[] args) {
        
        // UC 1 & UC 7: Two players starting at position 0
        int[] positions = {0, 0}; 
        
        // UC 6: To report the total number of times the die was played
        int totalDiceRolls = 0; 
        
        // 0 represents Player 1, 1 represents Player 2
        int currentPlayer = 0; 

        System.out.println("Welcome to the Snake and Ladder Simulator!\n");

        // UC 4: Repeat till a player reaches the winning position 100
        while (positions[0] < WINNING_POSITION && positions[1] < WINNING_POSITION) {
            totalDiceRolls++;
            int playerDisplayNum = currentPlayer + 1;

            // UC 2: Roll the die to get a number between 1 and 6 using RANDOM
            int dieRoll = (int) (Math.floor(Math.random() * 10) % 6) + 1;

            // UC 3: Check for Options using RANDOM (0: No Play, 1: Ladder, 2: Snake)
            int option = (int) (Math.floor(Math.random() * 10) % 3);

            System.out.print("Roll #" + totalDiceRolls + " | Player " + playerDisplayNum + " rolls a " + dieRoll + " | ");

            switch (option) {
                case NO_PLAY:
                    System.out.println("Action: NO PLAY. Position remains: " + positions[currentPlayer]);
                    break;

                case LADDER:
                    // UC 5: Ensure player gets exact winning position 100 (ignores roll if it exceeds 100)
                    if (positions[currentPlayer] + dieRoll <= WINNING_POSITION) {
                        positions[currentPlayer] += dieRoll;
                    }
                    System.out.println("Action: LADDER! Moves ahead to: " + positions[currentPlayer]);
                    break;

                case SNAKE:
                    positions[currentPlayer] -= dieRoll;
                    // UC 4 (Note): If position moves below 0, player restarts from 0
                    if (positions[currentPlayer] < 0) {
                        positions[currentPlayer] = 0;
                    }
                    System.out.println("Action: SNAKE! Drops back to: " + positions[currentPlayer]);
                    break;
            }

            // Check if the current player has won the game
            if (positions[currentPlayer] == WINNING_POSITION) {
                System.out.println("\n🎉 GAME OVER! 🎉");
                System.out.println("Player " + playerDisplayNum + " WON the game!");
                break;
            }

            // UC 7: If a player gets a Ladder, they play again. Otherwise, switch turns.
            if (option == LADDER) {
                System.out.println("--> Player " + playerDisplayNum + " gets another turn for climbing a ladder!");
            } else {
                // Switch player turn
                currentPlayer = (currentPlayer == 0) ? 1 : 0;
            }
        }

        // UC 6: Report the total number of dice rolls to win the game
        System.out.println("\n--- Game Statistics ---");
        System.out.println("Total Dice Rolls to complete the game: " + totalDiceRolls);
        System.out.println("Final Position Player 1: " + positions[0]);
        System.out.println("Final Position Player 2: " + positions[1]);
    }
}
