package ex05;

import java.util.UUID;

public class TransactionsLinkedList {
    private class Node {
        public Node next;
        public Node prev;
        public Transaction transaction;

        public Node(Transaction transaction) {
            next = null;
            prev = null;
            this.transaction = transaction;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public void addTransaction(Transaction newTransaction) {
        Node newNode = new Node(newTransaction);
        if (size == 0) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
        size++;
    }

    public void removeTransaction(UUID id) {
        Node node = head;
        while (node != null) {
             if (node.transaction.getId().equals(id)) {
                 if (node == head) {
                     head = node.next;
                     if (head != null) {
                         head.prev = null;
                     }
                     if (node == tail) {
                         tail = null;
                     }
                 } else if (node == tail) {
                     tail = node.prev;
                     tail.next = null;
                 } else {
                     node.prev.next = node.next;
                     node.next.prev = node.prev;
                 }
                 size--;
                 return;
             }
             node = node.next;
        }
        throw new TransactionNotFoundException("This id was not found");
    }

    public Transaction[] toArray() {
        Transaction[] transactionArray = new Transaction[size];
        int i = 0;
        Node node = head;
        while (node != null) {
            transactionArray[i++] = node.transaction;
            node = node.next;
        }
        return transactionArray;
    }
}
