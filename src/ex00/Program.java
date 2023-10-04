package ex00;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        System.out.println(" - Creating of user1 - ");
        User user1 = new User(1, "Rose", 1000);
        printUserInfo(user1);
        user1.setId(10);
        user1.setName("Rosanna");
        user1.setBalance(-2000);
        printUserInfo(user1);

        System.out.println(" - Creating of user2 - ");
        User user2 = new User(2, "Jenny", -678.901);
        printUserInfo(user2);
        user2.setId(20);
        user2.setName("Jen");
        user2.setBalance(2000);
        printUserInfo(user2);

        System.out.println(" - Creating of transaction1 - ");
        Transaction transaction1 = new Transaction(UUID.randomUUID(), user1, user2, Transaction.Category.DEBIT, 500);
        printTransactionInfo(transaction1);
        transaction1.setId(UUID.randomUUID());
        transaction1.setRecipient(user2);
        transaction1.setSender(user1);
        transaction1.setCategory(Transaction.Category.CREDIT);
        printTransactionInfo(transaction1);
        transaction1.setAmount(-600);

        System.out.println(" - Creating of transaction2 - ");
        Transaction transaction2 = new Transaction(UUID.randomUUID(), user1, user2, Transaction.Category.CREDIT, 100);
        printTransactionInfo(transaction2);
        transaction2.setId(UUID.randomUUID());
        transaction2.setRecipient(user2);
        transaction2.setSender(user1);
        transaction2.setCategory(Transaction.Category.CREDIT);
        transaction2.setAmount(-100);
        printTransactionInfo(transaction2);

    }

    public static void printUserInfo(User user) {
        System.out.printf("Id: %-3d   Name: %-8s    Balance: %-10.2f\n\n", user.getId(), user.getName(), user.getBalance());
    }

    public static void printTransactionInfo(Transaction transaction) {
        System.out.println("Id: " + transaction.getId());
        System.out.printf("Recipient name: %-8s\n", transaction.getRecipient().getName());
        System.out.printf("Sender name: %-8s\n", transaction.getSender().getName());
        System.out.println("Category: " + transaction.getCategory());
        System.out.printf("Amount: %-10.2f\n\n", transaction.getAmount());
    }
}
