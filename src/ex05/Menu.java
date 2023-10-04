package ex05;

import java.util.Scanner;
import java.util.UUID;
import static java.lang.Math.abs;

public class Menu {
    public enum Mode {
        PRODUCTION,
        DEV
    }
    private Mode mode;
    private final Scanner scanner = new Scanner(System.in);
    private final TransactionsService service = new TransactionsService();

    public Menu(String modeStr) {
        if (modeStr.equals("dev")) {
            this.mode = Mode.DEV;
        } else {
            this.mode = Mode.PRODUCTION;
        }
    }

    public void launch() {
        int choice = 0;
        do {
            printMenu();
            try {
                choice = inputChoice();
                if (choice > 0 && (mode.equals(Mode.DEV) && choice <= 7 || mode.equals(Mode.PRODUCTION) && choice <= 5)) {
                    performChoice(choice);
                } else {
                    throw new RuntimeException("You must enter the correct number of menu");
                }
            } catch (RuntimeException exception) {
                System.out.println(exception.getMessage());
            }
        } while (mode.equals(Mode.DEV) && choice < 7 || mode.equals(Mode.PRODUCTION) && choice < 5);
    }

    public void printMenu() {
        System.out.println("---------------------------------------------------------");
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        if (this.mode == Mode.PRODUCTION) {
            System.out.println("5. Finish execution");
        } else {
            System.out.println("5. DEV – remove a transfer by ID");
            System.out.println("6. DEV – check transfer validity");
            System.out.println("7. Finish execution");
        }
    }

    public int inputChoice() {
        int choice;
        String inputStr = scanner.nextLine();
        if (inputStr.length() == 1 && inputStr.charAt(0) > '0' && inputStr.charAt(0) < '9') {
            choice = (int)inputStr.charAt(0) - 48;
        } else {
            throw new RuntimeException("You must enter the correct number of menu");
        }
        return choice;
    }

    public void performChoice(int choice) {
        switch (choice) {
            case 1:
                System.out.println("Enter a user name and a balance");
                addUser();
                break;
            case 2:
                System.out.println("Enter a user ID");
                viewBalance();
                break;
            case 3:
                System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
                performTransaction();
                break;
            case 4:
                System.out.println("Enter a user ID");
                printTransactions();
                break;
            case 5:
                if (mode.equals(Mode.DEV)) {
                    System.out.println("Enter a user ID and a transfer ID");
                    removeTransaction();
                }
                break;
            case 6:
                checkTransList();
                break;
        }
    }


    public void addUser() {
        String[] inputStr = scanner.nextLine().trim().split(" ");
        String name;
        double balance;
        try {
            if (inputStr.length != 2) {
                throw new RuntimeException("Enter 2 arguments");
            }
            name = inputStr[0];
            balance = Integer.parseInt(inputStr[1]);
            service.addUser(name, balance);
            System.out.printf("User with id = %d is added\n", service.getUsersList().getNumberUsers());
        } catch (RuntimeException exception) {
            System.err.println(exception.getMessage());
//            addUser();
        }
    }

    public void viewBalance() {
        int id = 0;
        String[] inputStr = scanner.nextLine().trim().split(" ");
        try {
            if (inputStr.length != 1) {
                throw new RuntimeException("Enter 1 argument");
            }
            id = Integer.parseInt(inputStr[0]);
            System.out.println(service.getUsersList().getUserById(id).getName() + " - " + service.getBalance(id));
        } catch (RuntimeException exception) {
            System.err.println(exception.getMessage());
        }
    }

    public void performTransaction() {
        int senderId, recipientId;
        double amount;
        String[] inputStr = scanner.nextLine().trim().split(" ");
        try {
            if (inputStr.length != 3) {
                throw new RuntimeException("Enter 3 arguments");
            }
            senderId = Integer.parseInt(inputStr[0]);
            recipientId = Integer.parseInt(inputStr[1]);
            amount = Double.parseDouble(inputStr[2]);
            service.performTransaction(recipientId, senderId, amount);
            System.out.println("The transfer is completed");
        } catch (RuntimeException exception) {
            System.err.println(exception.getMessage());
        }
    }

    public void printTransactions() {
        int id;
        Transaction[] transactionsArray;
        String[] inputStr = scanner.nextLine().trim().split(" ");
        try {
            if (inputStr.length != 1) {
                throw new RuntimeException("Enter 1 argument");
            }
            id = Integer.parseInt(inputStr[0]);
            transactionsArray = service.getTransList(id);
            for (Transaction trans : transactionsArray) {
                if (trans.getCategory().equals(Transaction.Category.CREDIT)) {
                    System.out.print("To ");
                } else {
                    System.out.print("From ");
                }
                System.out.printf("%s(id = %d) ", trans.getSender().getName(), trans.getSender().getId());
                System.out.println(trans.getAmount() + " with id = " + trans.getId());
            }
        } catch (RuntimeException exception) {
            System.err.println(exception.getMessage());
        }
    }

    public void removeTransaction() {
        String[] inputStr = scanner.nextLine().trim().split(" ");
        int userId;
        UUID transId;
        try {
            if (inputStr.length != 2) {
                throw new RuntimeException("Enter 2 arguments");
            }
            userId = Integer.parseInt(inputStr[0]);
            transId = UUID.fromString(inputStr[1]);
            for (Transaction trans : service.getTransList(userId)) {
                if (trans.getId().equals(transId)) {
                    System.out.print("Transfer ");
                    if (trans.getCategory().equals(Transaction.Category.CREDIT)) {
                        System.out.print("to ");
                    } else {
                        System.out.print("from ");
                    }
                    System.out.printf("%s(id = %d) ", trans.getSender().getName(), trans.getSender().getId());
                    System.out.println(abs(trans.getAmount()));
                }
            }
            service.removeTransaction(userId, transId);
        } catch (RuntimeException exception) {
            System.err.println(exception.getMessage());
        }
    }

    public void checkTransList() {
        Transaction[] transArray = service.checkTransList();
        if (transArray.length != 0 && transArray[0] != null) {
            System.out.println("Check results:");
            for (Transaction trans : transArray) {
                System.out.printf("%s(id = %d) ", trans.getRecipient().getName(), trans.getRecipient().getId());
                System.out.print("has an unacknowledged transfer id = " + trans.getId());
                if (trans.getCategory().equals(Transaction.Category.CREDIT)) {
                    System.out.print(" to ");
                } else {
                    System.out.print(" from ");
                }
                System.out.printf("%s(id = %d) ", trans.getSender().getName(), trans.getSender().getId());
                System.out.println("for " + trans.getAmount());
            }
        } else {
            System.out.println("There are no unpaired transactions");
        }
    }
}