import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;


import exceptions.AccountNotFoundException;
import exceptions.DuplicateAccountException;
import exceptions.InvalidAmountException;
import exceptions.InsufficientBalanceException;

public class AccountingSystem {

    private HashMap<Integer, Account> accounts;
    private ArrayList<Transaction> transactions;

    // Constructor
    public AccountingSystem() {
        accounts = new HashMap<>();
        transactions = new ArrayList<>();
    }

    // Create Account
    public void createAccount(int accountNumber,
                              String accountName,
                              AccountType accountType,
                              double openingBalance)
            throws DuplicateAccountException, InvalidAmountException {

        if (accounts.containsKey(accountNumber)) {
            throw new DuplicateAccountException(
                    "Account number already exists."
            );
        }

        if (openingBalance < 0) {
            throw new InvalidAmountException(
                    "Opening balance cannot be negative."
            );
        }

        Account account = new Account(     //obj creation ,calls constructor of account.java 
                accountNumber,
                accountName,
                accountType,
                openingBalance
        );

        accounts.put(accountNumber, account);

        System.out.println("Account created successfully!");
    }

    // Search Account
    public Account searchAccount(int accountNumber) {

        return accounts.get(accountNumber);
    }

    // Display All Accounts
    public void displayAllAccounts() {

        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }

        System.out.println("\n========== ALL ACCOUNTS ==========");

        for (Account account : accounts.values()) {
            account.displayAccount(); //calls method from account.java
        }
    }

    // Display Balance
    public void displayBalance(int accountNumber)
            throws AccountNotFoundException {

        Account account = accounts.get(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException(
                    "Account not found."
            );
        }

        System.out.println("\nAccount Name: "
                + account.getAccountName());//calls getter from account.java

        System.out.println("Current Balance: ₹"
                + account.getCurrentBalance());
    }

    // Record Income
    public void recordIncome(int accountNumber,
                             double amount,
                             String description)
            throws AccountNotFoundException,
                   InvalidAmountException {

        Account account = accounts.get(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException(
                    "Account not found."
            );
        }

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Income amount must be greater than zero."
            );
        }

        double newBalance =
                account.getCurrentBalance() + amount;//currentbalance+ newone //mrthod inside account.java

        account.setCurrentBalance(newBalance);

        String transactionId =
                "T" + (transactions.size() + 1);//tells how many element are inside arraylist + 1(newone)

        Transaction transaction = new Transaction(
                transactionId,
                accountNumber,
                "INCOME",
                amount,
                description
        );

        transactions.add(transaction);//is the array list

        System.out.println(
                "Income recorded successfully!"
        );
    }

    // Record Expense
    public void recordExpense(int accountNumber,
                              double amount,
                              String description)
            throws AccountNotFoundException,
                   InvalidAmountException,
                   InsufficientBalanceException {

        Account account = accounts.get(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException(
                    "Account not found."
            );
        }

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Expense amount must be greater than zero."
            );
        }

        if (amount > account.getCurrentBalance()) {
            throw new InsufficientBalanceException(
                    "Insufficient balance. "
                    + "Available balance: ₹"
                    + account.getCurrentBalance()
            );
        }

        double newBalance =
                account.getCurrentBalance() - amount;

        account.setCurrentBalance(newBalance);

        String transactionId =
                "T" + (transactions.size() + 1);

        Transaction transaction = new Transaction(
                transactionId,
                accountNumber,
                "EXPENSE",
                amount,
                description
        );

        transactions.add(transaction);

        System.out.println(
                "Expense recorded successfully!"
        );
    }

   
    // Debit Transaction
public void debitAccount(int accountNumber,
    double amount,
    String description)
throws AccountNotFoundException,
InvalidAmountException,
InsufficientBalanceException {

Account account = accounts.get(accountNumber);

if (account == null) {
throw new AccountNotFoundException(
"Account not found."
);
}

if (amount <= 0) {
throw new InvalidAmountException(
"Debit amount must be greater than zero."
);
}

double currentBalance = account.getCurrentBalance();
double newBalance;

// Debit increases Asset and Expense accounts
if (account.getAccountType() == AccountType.ASSET ||
account.getAccountType() == AccountType.EXPENSE) {

newBalance = currentBalance + amount;

}
// Debit decreases Liability, Capital and Income accounts
else {

if (amount > currentBalance) {
throw new InsufficientBalanceException(
"Insufficient balance. Available balance: ₹"
+ currentBalance
);
}

newBalance = currentBalance - amount;//if not asset or expense the debit decreases 
}

account.setCurrentBalance(newBalance);

String transactionId =
"T" + (transactions.size() + 1);

Transaction transaction = new Transaction(
transactionId,
accountNumber,
"DEBIT",
amount,
description
);

transactions.add(transaction);

System.out.println(
"Debit transaction successful!"
);
}

    
    // Credit Transaction
public void creditAccount(int accountNumber,
    double amount,
    String description)
throws AccountNotFoundException,
InvalidAmountException,
InsufficientBalanceException {

Account account = accounts.get(accountNumber);

if (account == null) {
throw new AccountNotFoundException(
"Account not found."
);
}

if (amount <= 0) {
throw new InvalidAmountException(
"Credit amount must be greater than zero."
);
}

double currentBalance = account.getCurrentBalance();
double newBalance;

// Credit increases Liability, Capital and Income accounts
if (account.getAccountType() == AccountType.LIABILITY ||
account.getAccountType() == AccountType.CAPITAL ||
account.getAccountType() == AccountType.INCOME) {

newBalance = currentBalance + amount;

}
// Credit decreases Asset and Expense accounts
else {

if (amount > currentBalance) {
throw new InsufficientBalanceException(
"Insufficient balance. Available balance: ₹"
+ currentBalance
);
}

newBalance = currentBalance - amount;//for asset and expense
}

account.setCurrentBalance(newBalance);

String transactionId =
"T" + (transactions.size() + 1);

Transaction transaction = new Transaction(
transactionId,
accountNumber,
"CREDIT",
amount,
description
);

transactions.add(transaction);

System.out.println(
"Credit transaction successful!"
);
}

    // Generate Financial Report
public void generateReport() {

    if (transactions.isEmpty()) {
        System.out.println(
                "No transactions available."
        );
        return;// void type therefore stop executiong this method and come out of it
    }

    double totalIncome = 0;
    double totalExpense = 0;
    double totalDebit = 0;
    double totalCredit = 0;

    System.out.println(
            "\n========== FINANCIAL TRANSACTION REPORT =========="
    );

    for (Transaction transaction : transactions) { //class name ,temp var:array list contaion all transc

        transaction.displayTransaction();

        if (transaction.getTransactionType().equals("INCOME")) {
            totalIncome += transaction.getAmount();
        }

        if (transaction.getTransactionType().equals("EXPENSE")) {
            totalExpense += transaction.getAmount();
        }

        if (transaction.getTransactionType().equals("DEBIT")) {
            totalDebit += transaction.getAmount();
        }

        if (transaction.getTransactionType().equals("CREDIT")) {
            totalCredit += transaction.getAmount();
        }
    }

    System.out.println("\n============== REPORT SUMMARY ==============");

    System.out.println("Total Income  : ₹" + totalIncome);
    System.out.println("Total Expense : ₹" + totalExpense);
    System.out.println("Total Debit   : ₹" + totalDebit);
    System.out.println("Total Credit  : ₹" + totalCredit);

    System.out.println(
            "============================================"
    );
}
// Save Accounts to File
public void saveAccounts() {

    try {

        FileWriter writer = new FileWriter("accounts.txt");

        for (Account account : accounts.values()) {

            writer.write(
                    account.getAccountNumber() + "," +
                    account.getAccountName() + "," +
                    account.getAccountType() + "," +
                    account.getOpeningBalance() + "," +
                    account.getCurrentBalance() + "\n"
            );
        }

        writer.close();

        System.out.println("Accounts saved successfully!");

    } catch (IOException e) {

        System.out.println(
                "Error while saving accounts."
        );
    }
}

// Save Transactions to File
public void saveTransactions() {

    try { //file cant be createrd disk prblm fline cant be writtem

        FileWriter writer = new FileWriter("transactions.txt");//file writer  is a java class

        for (Transaction transaction : transactions) {

            writer.write(// info written in file
                    transaction.getTransactionId() + "," + //comma used as seperators
                    transaction.getAccountNumber() + "," +
                    transaction.getTransactionType() + "," +
                    transaction.getAmount() + "," +
                    transaction.getDescription() + "," +
                    transaction.getDate() + "\n"
            );
        }

        writer.close();

        System.out.println("Transactions saved successfully!");

    } catch (IOException e) {

        System.out.println(
                "Error while saving transactions."
        );
    }
}
// Load Accounts from File
public void loadAccounts() {

    try {

        BufferedReader reader =// hepls us read the file effeciently line by line
                new BufferedReader(
                        new FileReader("accounts.txt")
                );

        String line; // store one line from the file

        while ((line = reader.readLine()) != null) {// reader.readline()- reads one line from the file - keeps reading lines as lomg as there is another line available

            String[] data = line.split(","); //"101,disha,asset,100" -> data[0]="101"  data[1]="disha". data[2]='asset'


            int accountNumber =
                    Integer.parseInt(data[0]);//data[0] is a string  but we need 101, so  string-> integer

            String accountName = data[1];

            AccountType accountType =
                    AccountType.valueOf(data[2]); // valeof()-> convert asset into enum -> AccountType.ASSET

            double openingBalance =
                    Double.parseDouble(data[3]);

            double currentBalance =
                    Double.parseDouble(data[4]);

            Account account = new Account(
                    accountNumber,
                    accountName,
                    accountType,
                    openingBalance
            );

            account.setCurrentBalance(currentBalance);

            accounts.put(accountNumber, account); //STORE THIS ACC. OBJ IN THE HASH MAP USING 101 AS ITS KEY, ACCOUNT AARE BACK IN MEMORY
        }

        reader.close();

        System.out.println("Accounts loaded successfully!");

    } catch (IOException e) {

        System.out.println(
                "No previous account data found."
        );
    }
}
// Load Transactions from File
public void loadTransactions() {

    try {

        BufferedReader reader =
                new BufferedReader(
                        new FileReader("transactions.txt")
                );

        String line;

        while ((line = reader.readLine()) != null) {

            String[] data = line.split(",");

            String transactionId = data[0];

            int accountNumber =
                    Integer.parseInt(data[1]);

            String transactionType = data[2];

            double amount =
                    Double.parseDouble(data[3]);

            String description = data[4];

            java.time.LocalDate date =
                    java.time.LocalDate.parse(data[5]);

            Transaction transaction =
                    new Transaction(
                            transactionId,
                            accountNumber,
                            transactionType,
                            amount,
                            description,
                            date
                    );

            transactions.add(transaction);
        }

        reader.close();

        System.out.println(
                "Transactions loaded successfully!"
        );

    } catch (IOException e) {

        System.out.println(
                "No previous transaction data found."
        );
    }
}
}

