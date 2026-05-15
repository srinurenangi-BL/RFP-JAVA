import java.util.ArrayList;
import java.util.Scanner;

class Stock {
    String name;
    int numberOfShares;
    double sharePrice;

    public Stock(String name, int numberOfShares, double sharePrice) {
        this.name = name;
        this.numberOfShares = numberOfShares;
        this.sharePrice = sharePrice;
    }

    public double calculateValue() {
        return numberOfShares * sharePrice;
    }
}

class StockPortfolio {
    ArrayList<Stock> stocks = new ArrayList<>();

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public void printReport() {
        double totalPortfolioValue = 0;
        System.out.println("\n--- Stock Report ---");
        for (Stock s : stocks) {
            double value = s.calculateValue();
            totalPortfolioValue += value;
            System.out.printf("Stock: %-10s | Shares: %-5d | Price: %-8.2f | Value: %-10.2f\n", 
                              s.name, s.numberOfShares, s.sharePrice, value);
        }
        System.out.println("--------------------------------------------------");
        System.out.printf("Total Portfolio Value: %.2f\n", totalPortfolioValue);
    }
}