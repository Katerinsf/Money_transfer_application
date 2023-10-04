package ex03;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Lalisa", 1000);
        User user2 = new User("Rose", 3000);

        Transaction trans1 = new Transaction(user2, user1, Transaction.Category.CREDIT, -700);
        Transaction trans2 = new Transaction(user1, user2, Transaction.Category.DEBIT, 100);
        Transaction trans3 = new Transaction(user2, user1, Transaction.Category.DEBIT, 500);
        Transaction trans4 = new Transaction(user1, user2, Transaction.Category.CREDIT, -300);

        TransactionsLinkedList list = new TransactionsLinkedList();
        list.addTransaction(trans1);
        list.addTransaction(trans2);
        list.addTransaction(trans3);
        list.addTransaction(trans4);

        System.out.println("List to array:");
        Transaction[] array = list.toArray();
        for (Transaction t : array) {
            printTransactionInfo(t);
        }

        System.out.println("Array after remove transaction 2:");
        list.removeTransaction(trans2.getId());
        array = list.toArray();
        for (Transaction t : array) {
            printTransactionInfo(t);
        }

        System.out.println("Checking deleting by non-existent index");
        try {
            list.removeTransaction(trans2.getId());
        } catch (TransactionNotFoundException error) {
            System.err.println(error.getMessage());
        }



    }

    public static void printTransactionInfo(Transaction transaction) {
        System.out.println("Id: " + transaction.getId());
        System.out.printf("Recipient name: %-8s\n", transaction.getRecipient().getName());
        System.out.printf("Sender name: %-8s\n", transaction.getSender().getName());
        System.out.println("Category: " + transaction.getCategory());
        System.out.printf("Amount: %-10.2f\n\n", transaction.getAmount());
    }
}
