
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu {

    //Instance Variables
    Bank bank = new Bank();
    boolean exit;

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.runMenu();
    }

    public void runMenu() {
        printHeader();
        while (!exit) {
            printMenu();
            int choice = getMenuChoice();
            performAction(choice);
        }
    }

    private void printHeader() {
        System.out.println("+-----------------------------------+");
        System.out.println("|        Welcome to Mr.V's          |");
        System.out.println("|        Awesome Bank App           |");
        System.out.println("+-----------------------------------+");
    }

    private void printMenu() {
        displayHeader("Please make a selection");
        System.out.println("1) Create a new Account");
        System.out.println("2) Make a deposit");
        System.out.println("3) Make a withdrawal");
        System.out.println("4) List account balance");
        System.out.println("0) Exit");
    }

    private int getMenuChoice() {
        Scanner keyboard = new Scanner(System.in);
        int choice = -1;
        do {
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Numbers only please.");
            }
            if (choice < 0 || choice > 4) {
                System.out.println("Choice outside of range. Please chose again.");
            }
        } while (choice < 0 || choice > 4);
        return choice;
    }

    private void performAction(int choice) {
        switch (choice) {
            case 0:
                System.out.println("Thank you for using our application.");
                System.exit(0);
                break;
            case 1: {
                try {
                    createAnAccount();
                } catch (InvalidAccountTypeException ex) {
                    System.out.println("Account was not created successfully.");
                }
            }
            break;
            case 2:
                makeADeposit();
                break;
            case 3:
                makeAWithdrawal();
                break;
            case 4:
                listBalances();
                break;
            default:
                System.out.println("Unknown error has occured.");
        }
    }

    private String askQuestion(String question, List<String> answers) {
        String response = "";
        Scanner keyboard = new Scanner(System.in);
        boolean choices = ((answers == null) || answers.size() == 0) ? false : true;
        boolean firstRun = true;
        do {
            if (!firstRun) {
                System.out.println("Invalid selection. Please try again.");
            }
            System.out.print(question);
            if (choices) {
                System.out.print("(");
                for (int i = 0; i < answers.size() - 1; ++i) {
                    System.out.print(answers.get(i) + "/");
                }
                System.out.print(answers.get(answers.size() - 1));
                System.out.print("): ");
            }
            response = keyboard.nextLine();
            firstRun = false;
            if (!choices) {
                break;
            }
        } while (!answers.contains(response));
        return response;
    }

    private double getDeposit(String accountType) {
        Scanner keyboard = new Scanner(System.in);
        double initialDeposit = 0;
        Boolean valid = false;
        while (!valid) {
            System.out.print("Please enter an initial deposit: ");
            try {
                initialDeposit = Double.parseDouble(keyboard.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Deposit must be a number.");
            }
            if (accountType.equalsIgnoreCase("checking")) {
                if (initialDeposit < 100) {
                    System.out.println("Checking accounts require a minimum of $100 dollars to open.");
                } else {
                    valid = true;
                }
            } else if (accountType.equalsIgnoreCase("savings")) {
                if (initialDeposit < 50) {
                    System.out.println("Savings accounts require a minimum of $50 dollars to open.");
                } else {
                    valid = true;
                }
            }
        }
        return initialDeposit;
    }

    private void createAnAccount() throws InvalidAccountTypeException {
        displayHeader("Create an Account");
        //Get account information
        String accountType = askQuestion("Please enter an account type: ", Arrays.asList("checking", "savings"));
        String firstName = askQuestion("Please enter your first name: ", null);
        String lastName = askQuestion("Please enter your last name: ", null);
        String ssn = askQuestion("Please enter your social security number: ", null);
        double initialDeposit = getDeposit(accountType);
        //We can create an account now;
        Account account;
        if (accountType.equalsIgnoreCase("checking")) {
            account = new Checking(initialDeposit);
        } else if (accountType.equalsIgnoreCase("savings")) {
            account = new Savings(initialDeposit);
        } else {
            throw new InvalidAccountTypeException();
        }
        Customer customer = new Customer(firstName, lastName, ssn, account);
        bank.addCustomer(customer);
    }

    private double getDollarAmount(String question) {
        Scanner keyboard = new Scanner(System.in);
        System.out.print(question);
        double amount = 0;
        try {
            amount = Double.parseDouble(keyboard.nextLine());
        } catch (NumberFormatException e) {
            amount = 0;
        }
        return amount;
    }

    private void makeADeposit() {
        displayHeader("Make a Deposit");
        int account = selectAccount();
        if (account >= 0) {
            double amount = getDollarAmount("How much would you like to deposit?: ");
            bank.getCustomer(account).getAccount().deposit(amount);
        }
    }

    private void makeAWithdrawal() {
        displayHeader("Make a Withdrawal");
        int account = selectAccount();
        if (account >= 0) {
            double amount = getDollarAmount("How much would you like to withdraw?: ");
            bank.getCustomer(account).getAccount().withdraw(amount);
        }
    }

    private void listBalances() {
        displayHeader("List Account Details");
        int account = selectAccount();
        if (account >= 0) {
            displayHeader("Account Details");
            System.out.println(bank.getCustomer(account).getAccount());
        }
    }
    
    private void displayHeader(String message){
        System.out.println();
        int width = message.length() + 6;
        StringBuilder sb = new StringBuilder();
        sb.append("+");
        for(int i = 0; i < width; ++i){
            sb.append("-");
        }
        sb.append("+");
        System.out.println(sb.toString());
        System.out.println("|   " + message + "   |");
        System.out.println(sb.toString());
    }

    private int selectAccount() {
        Scanner keyboard = new Scanner(System.in);
        ArrayList<Customer> customers = bank.getCustomers();
        if (customers.size() <= 0) {
            System.out.println("No customers at your bank.");
            return -1;
        }
        System.out.println("Select an account:");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println("\t" + (i + 1) + ") " + customers.get(i).basicInfo());
        }
        int account;
        System.out.print("Please enter your selection: ");
        try {
            account = Integer.parseInt(keyboard.nextLine()) - 1;
        } catch (NumberFormatException e) {
            account = -1;
        }
        if (account < 0 || account > customers.size()) {
            System.out.println("Invalid account selected.");
            account = -1;
        }
        return account;
    }
}
