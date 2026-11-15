import java.time.LocalDate;

public class Transaction {

    private String transactionId;
    private int accountNumber;
    private String transactionType;
    private double amount;
    private String description;
    private LocalDate date;

    // Constructor
    public Transaction(String transactionId, int accountNumber,
                       String transactionType, double amount,
                       String description) {

        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.description = description;
        this.date = LocalDate.now();
    }
// Constructor for loading saved transactions
public Transaction(String transactionId,
    int accountNumber,
    String transactionType,
    double amount,
    String description,
    LocalDate date) {

this.transactionId = transactionId;
this.accountNumber = accountNumber;
this.transactionType = transactionType;
this.amount = amount;
this.description = description;
this.date = date;
}
    // Getters
    public String getTransactionId() {
        return transactionId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    // Display transaction details
    public void displayTransaction() {

        System.out.println("----------------------------------");
        System.out.println("Transaction ID : " + transactionId);
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Type           : " + transactionType);
        System.out.println("Amount         : ₹" + amount);
        System.out.println("Description    : " + description);
        System.out.println("Date           : " + date);
        System.out.println("----------------------------------");
    }
}