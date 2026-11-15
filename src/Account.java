public class Account {

    private int accountNumber;
    private String accountName;
    private AccountType accountType;
    private double openingBalance;
    private double currentBalance;

    // Constructor
    public Account(int accountNumber,
                   String accountName,
                   AccountType accountType,
                   double openingBalance) {

        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.accountType = accountType;
        this.openingBalance = openingBalance;
        this.currentBalance = openingBalance;
    }

    // Getters

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public double getOpeningBalance() {
        return openingBalance;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    // Update current balance

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    // Display account details

    public void displayAccount() {

        System.out.println("----------------------------------");
        System.out.println("Account Number  : " + accountNumber);
        System.out.println("Account Name    : " + accountName);
        System.out.println("Account Type    : " + accountType);
        System.out.println("Opening Balance : ₹" + openingBalance);
        System.out.println("Current Balance : ₹" + currentBalance);
        System.out.println("----------------------------------");
    }
}