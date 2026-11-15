import java.util.Scanner;

import exceptions.AccountNotFoundException;
import exceptions.DuplicateAccountException;
import exceptions.InvalidAmountException;
import exceptions.InsufficientBalanceException;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        AccountingSystem system = new AccountingSystem();
    
        system.loadAccounts();
        system.loadTransactions();
    
        int choice;
        do {

            System.out.println("\n========================================");
            System.out.println("   ENTERPRISE ACCOUNTING MANAGEMENT");
            System.out.println("========================================");
            System.out.println("1. Create Account");
            System.out.println("2. View All Accounts");
            System.out.println("3. Search Account");
            System.out.println("4. Record Income");
            System.out.println("5. Record Expense");
            System.out.println("6. Perform Debit Transaction");
            System.out.println("7. Perform Credit Transaction");
            System.out.println("8. Display Account Balance");
            System.out.println("9. Generate Financial Report");
            System.out.println("10. Exit");
            System.out.println("========================================");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            try {

                switch (choice) {

                    case 1:

    System.out.print("Enter Account Number: ");
    int accountNumber = scanner.nextInt();

    scanner.nextLine();

    System.out.print("Enter Account Name: ");
    String accountName = scanner.nextLine();

    System.out.print(
            "Enter Account Type (ASSET/LIABILITY/CAPITAL/INCOME/EXPENSE): "
    );

    String accountTypeInput = scanner.nextLine();

    AccountType accountType;

    try {

        accountType =
                AccountType.valueOf(
                        accountTypeInput.trim().toUpperCase()
                );

    } catch (IllegalArgumentException e) {

        System.out.println(
                "ERROR: Invalid account type."
        );

        System.out.println(
                "Please use: ASSET, LIABILITY, CAPITAL, INCOME or EXPENSE."
        );

        break;
    }

    System.out.print("Enter Opening Balance: ");
    double openingBalance = scanner.nextDouble();

    system.createAccount(
            accountNumber,
            accountName,
            accountType,
            openingBalance
    );

    break;
                    

                    case 2:
                        system.displayAllAccounts();
                        break;

                    case 3:
                        System.out.print("Enter Account Number: ");
                        int searchNumber = scanner.nextInt();

                        Account account =
                                system.searchAccount(searchNumber);

                        if (account == null) {
                            System.out.println("Account not found.");
                        } else {
                            System.out.println("\nAccount Found!");
                            account.displayAccount();
                        }

                        break;

                    case 4:
                        System.out.print("Enter Account Number: ");
                        int incomeAccount = scanner.nextInt();

                        System.out.print("Enter Income Amount: ");
                        double incomeAmount = scanner.nextDouble();

                        scanner.nextLine();

                        System.out.print("Enter Description: ");
                        String incomeDescription = scanner.nextLine();

                        system.recordIncome(
                                incomeAccount,
                                incomeAmount,
                                incomeDescription
                        );

                        break;

                    case 5:
                        System.out.print("Enter Account Number: ");
                        int expenseAccount = scanner.nextInt();

                        System.out.print("Enter Expense Amount: ");
                        double expenseAmount = scanner.nextDouble();

                        scanner.nextLine();

                        System.out.print("Enter Description: ");
                        String expenseDescription = scanner.nextLine();

                        system.recordExpense(
                                expenseAccount,
                                expenseAmount,
                                expenseDescription
                        );

                        break;

                    case 6:
                        System.out.print("Enter Account Number: ");
                        int debitAccount = scanner.nextInt();

                        System.out.print("Enter Debit Amount: ");
                        double debitAmount = scanner.nextDouble();

                        scanner.nextLine();

                        System.out.print("Enter Description: ");
                        String debitDescription = scanner.nextLine();

                        system.debitAccount(
                                debitAccount,
                                debitAmount,
                                debitDescription
                        );

                        break;

                    case 7:
                        System.out.print("Enter Account Number: ");
                        int creditAccount = scanner.nextInt();

                        System.out.print("Enter Credit Amount: ");
                        double creditAmount = scanner.nextDouble();

                        scanner.nextLine();

                        System.out.print("Enter Description: ");
                        String creditDescription = scanner.nextLine();

                        system.creditAccount(
                                creditAccount,
                                creditAmount,
                                creditDescription
                        );

                        break;

                    case 8:
                        System.out.print("Enter Account Number: ");
                        int balanceAccount = scanner.nextInt();

                        system.displayBalance(balanceAccount);

                        break;

                    case 9:
                        system.generateReport();
                        break;

                        case 10:

                        system.saveAccounts();
                        system.saveTransactions();
                    
                        System.out.println(
                                "Thank you for using Enterprise Accounting Management System."
                        );
                    
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Please enter 1 to 10."
                        );
                }

            } catch (DuplicateAccountException e) {

                System.out.println("ERROR: " + e.getMessage());

            } catch (AccountNotFoundException e) {

                System.out.println("ERROR: " + e.getMessage());

            } catch (InvalidAmountException e) {

                System.out.println("ERROR: " + e.getMessage());

            } catch (InsufficientBalanceException e) {

                System.out.println("ERROR: " + e.getMessage());

            }

        } while (choice != 10);

        scanner.close();
    }
}