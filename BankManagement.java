import java.util.Scanner;

class BankAccount {
    String accountHolder;
    long accountNumber;
    double balance;

    // Create account
    void createAccount(String name, long number, double initialBalance) {
        accountHolder = name;
        accountNumber = number;
        balance = initialBalance;

        System.out.println("\nAccount created successfully!");
    }

    // Deposit money
    void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount! Enter an amount greater than 0.");
        } else {
            balance = balance + amount;
            System.out.println("Amount deposited successfully.");
        }
    }

    // Withdraw money
    void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount! Enter an amount greater than 0.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            balance = balance - amount;
            System.out.println("Amount withdrawn successfully.");
        }
    }

    // Check balance
    void checkBalance() {
        System.out.println("\n----- Account Details -----");
        System.out.println("Account Holder : " + accountHolder);
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Balance        : Rs. " + balance);
    }
}

public class BankManagement {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankAccount account = new BankAccount();

        boolean accountCreated = false;
        int choice = 0;

        do {
            System.out.println("\n===== BANK ACCOUNT MANAGEMENT SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        if (accountCreated) {
                            System.out.println("Account already exists!");
                            break;
                        }

                        sc.nextLine();

                        System.out.print("Enter account holder name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter account number: ");
                        long number = sc.nextLong();

                        System.out.print("Enter initial deposit: ");
                        double initialBalance = sc.nextDouble();

                        if (initialBalance < 0) {
                            System.out.println("Initial balance cannot be negative.");
                        } else {
                            account.createAccount(name, number, initialBalance);
                            accountCreated = true;
                        }
                        break;

                    case 2:
                        if (!accountCreated) {
                            System.out.println("Please create an account first.");
                            break;
                        }

                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = sc.nextDouble();

                        account.deposit(depositAmount);
                        break;

                    case 3:
                        if (!accountCreated) {
                            System.out.println("Please create an account first.");
                            break;
                        }

                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = sc.nextDouble();

                        account.withdraw(withdrawAmount);
                        break;

                    case 4:
                        if (!accountCreated) {
                            System.out.println("Please create an account first.");
                            break;
                        }

                        account.checkBalance();
                        break;

                    case 5:
                        System.out.println("\nThank you for using our Bank System!");
                        break;

                    default:
                        System.out.println("Invalid choice! Please enter 1 to 5.");
                }

            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid value.");
                sc.nextLine();
                choice = 0;
            }

        } while (choice != 5);

        sc.close();
    }
}