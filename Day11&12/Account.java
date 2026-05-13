class Account {
    private double balance;

    public Account(double initialBalance) {
        if (initialBalance > 0.0) this.balance = initialBalance;
    }

    public void debit(double amount) {
        if (amount > balance) {
            System.out.println("Debit amount exceeded account balance.");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        }
    }

    public double getBalance() { return balance; }
}