package ex04;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        TransactionsService service = new TransactionsService();
        service.addUser("Rose", 3000);
        service.addUser("Jenny", 500);

        System.out.println("- Print list of users -");
        UsersArrayList usersList = service.getUsersList();
        for (int i = 0; i < usersList.getNumberUsers(); i++) {
            printUserInfo(usersList.getUserByIndex(i));
        }

        System.out.println("\n- Print balance of users -");
        for (int i = 0; i < usersList.getNumberUsers(); i++) {
            System.out.println(service.getBalance(usersList.getUserByIndex(i).getId()));
        }

        System.out.println("\n- Performing and print transactions -");
        service.performTransaction(2, 1, 700);
        System.out.print("Balance after transaction 1: ");
        for (int i = 0; i < usersList.getNumberUsers(); i++) {
            System.out.print(service.getBalance(usersList.getUserByIndex(i).getId()) + "\t");
        }
        System.out.println();
        service.performTransaction(1, 2, 300);
        System.out.print("Balance after transaction 2: ");
        for (int i = 0; i < usersList.getNumberUsers(); i++) {
            System.out.print(service.getBalance(usersList.getUserByIndex(i).getId()) + "\t");
        }
        System.out.println();
        usersList = service.getUsersList();
        Transaction[] transactionsArray;
        for (int i = 1; i <= usersList.getNumberUsers(); i++) {
            transactionsArray = service.getTransList(i);
            for (int j = 0; j < 2; j++) {
                printTransactionInfo(transactionsArray[j]);
            }
        }

        System.out.println("\n- Removing and print transactions -");
        UUID id1 = service.getTransList(1)[0].getId();
        UUID id2 = service.getTransList(2)[1].getId();
        service.removeTransaction(1, id1);
        service.removeTransaction(2, id2);
        transactionsArray = service.checkTransList();
        for (int i = 0; i < transactionsArray.length; i++) {
            printTransactionInfo(transactionsArray[i]);
        }

    }

    public static void printUserInfo(User user) {
        System.out.printf("Id: %-3d   Name: %-8s    Balance: %-10.2f\n", user.getId(), user.getName(), user.getBalance());
    }

    public static void printTransactionInfo(Transaction transaction) {
        System.out.print("Id: " + transaction.getId() + "\t");
        System.out.printf("Recipient name: %-8s\t", transaction.getRecipient().getName());
        System.out.printf("Sender name: %-8s\t", transaction.getSender().getName());
        System.out.printf("Category: %-8s\t", transaction.getCategory());
        System.out.printf("Amount: %-10.2f\n", transaction.getAmount());
    }
}
