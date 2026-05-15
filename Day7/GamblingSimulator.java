package Day7;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GamblingSimulator {

    private static final Logger debugLogger = Logger.getLogger(GamblingSimulator.class.getName());

    private static final int INITIAL_STAKE = 100;
    private static final int BET_AMOUNT = 1;
    private static final double WIN_LIMIT_PERCENTAGE = 0.5;
    private static final double LOSS_LIMIT_PERCENTAGE = 0.5;
    private static final int PLAYING_DAYS_PER_MONTH = 20;

    private int totalProfitOrLoss = 0;
    private final List<Integer> dailyProfitOrLossRecord = new ArrayList<>();

    public GamblingSimulator() {
        debugLogger.setLevel(Level.INFO);
    }

    // UC 1: As a Gambler, start with a stake of $100 every day and bet $1 every game.
    public void initializeGambler() {
        debugLogger.info("Initialized Gambler with Stake: $" + INITIAL_STAKE + " and Bet: $" + BET_AMOUNT);
    }

    // UC 2: As a Gambler make $1 bet so either win or loose $1
    public boolean makeBet() {
        return Math.random() >= 0.5;
    }

    // UC 3: As a Calculative Gambler if won or lost 50% of the stake, resign for the day
    public int playSingleDay() {
        int currentStake = INITIAL_STAKE;
        int winLimit = (int) (INITIAL_STAKE + (INITIAL_STAKE * WIN_LIMIT_PERCENTAGE));
        int lossLimit = (int) (INITIAL_STAKE - (INITIAL_STAKE * LOSS_LIMIT_PERCENTAGE));

        while (currentStake > lossLimit && currentStake < winLimit) {
            if (makeBet()) {
                currentStake += BET_AMOUNT;
            } else {
                currentStake -= BET_AMOUNT;
            }
        }
        
        return currentStake - INITIAL_STAKE;
    }

    // UC 4: After 20 days of playing every day would like to know the total amount won or lost.
    public void playForDays(int numberOfDays) {
        for (int day = 1; day <= numberOfDays; day++) {
            int dailyResult = playSingleDay();
            dailyProfitOrLossRecord.add(dailyResult);
            totalProfitOrLoss += dailyResult;
        }
        debugLogger.info("Total Profit/Loss after " + numberOfDays + " days: $" + totalProfitOrLoss);
    }

    // UC 5: Each month would like to know the days won and lost and by how much.
    public void generateMonthlyReport() {
        int daysWon = 0;
        int daysLost = 0;
        int amountWon = 0;
        int amountLost = 0;

        for (int result : dailyProfitOrLossRecord) {
            if (result > 0) {
                daysWon++;
                amountWon += result;
            } else if (result < 0) {
                daysLost++;
                amountLost += Math.abs(result);
            }
        }

        debugLogger.info("Monthly Report -> Days Won: " + daysWon + " (Amount: $" + amountWon + 
                         "), Days Lost: " + daysLost + " (Amount: $" + amountLost + ")");
    }

    // UC 6: Know my luckiest day (max won) and unluckiest day (max lost)
    public void findLuckiestAndUnluckiestDays() {
        int luckiestDay = 0;
        int unluckiestDay = 0;
        int maxWin = 0;
        int maxLoss = 0;

        for (int day = 0; day < dailyProfitOrLossRecord.size(); day++) {
            int result = dailyProfitOrLossRecord.get(day);
            if (result > maxWin) {
                maxWin = result;
                luckiestDay = day + 1;
            } else if (result < maxLoss) {
                maxLoss = result;
                unluckiestDay = day + 1;
            }
        }

        debugLogger.info("Luckiest Day: Day " + luckiestDay + " with a win of $" + maxWin);
        debugLogger.info("Unluckiest Day: Day " + unluckiestDay + " with a loss of $" + Math.abs(maxLoss));
    }

    // UC 7: If won would like to continue playing next month or stop Gambling
    public boolean decideNextMonth() {
        if (totalProfitOrLoss > 0) {
            debugLogger.info("Result: Gambler is in profit ($" + totalProfitOrLoss + "). Continuing to play next month!");
            return true;
        } else {
            debugLogger.info("Result: Gambler is in loss or broke even ($" + totalProfitOrLoss + "). Stopping Gambling.");
            return false;
        }
    }

    public void startSimulation() {
        boolean continuePlaying = true;
        int monthCount = 1;

        while (continuePlaying) {
            debugLogger.info("--- Starting Month " + monthCount + " ---");
            initializeGambler();
            dailyProfitOrLossRecord.clear();
            totalProfitOrLoss = 0; 
            
            playForDays(PLAYING_DAYS_PER_MONTH);
            generateMonthlyReport();
            findLuckiestAndUnluckiestDays();
            
            continuePlaying = decideNextMonth();
            monthCount++;
            System.out.println(); 
        }
    }

    public static void main(String[] args) {
        GamblingSimulator simulator = new GamblingSimulator();
        simulator.startSimulation();
    }
}
