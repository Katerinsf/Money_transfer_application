package ex05;

import java.util.UUID;

public class TransactionsService {
    private UsersArrayList usersList = new UsersArrayList();
    private int countRemoving;

    public void addUser(String name, double balance) {
        User newUser = new User(name, balance);
        usersList.addUser(newUser);
    }

    public UsersArrayList getUsersList() {
        return usersList;
    }

    public double getBalance(int id) {
        return usersList.getUserById(id).getBalance();
    }

    public void performTransaction(int recipientId, int senderId, double amount) {
        User recipient = usersList.getUserById(recipientId);
        User sender = usersList.getUserById(senderId);
        if (sender.getBalance() < amount) {
            throw new IllegalTransactionException("The amount exceeds user's residual balance");
        } else if (amount <= 0) {
            throw new IllegalTransactionException("Amount is negative");
        } else if (recipientId == senderId) {
            throw new IllegalTransactionException("Recipient is a sender");
        } else {
            Transaction transDebit = new Transaction(recipient, sender, Transaction.Category.DEBIT, amount);
            Transaction transCredit = new Transaction(sender, recipient, Transaction.Category.CREDIT, -amount);
            transCredit.setId(transDebit.getId());

            TransactionsLinkedList listRecipient = recipient.getTransList();
            listRecipient.addTransaction(transDebit);
            recipient.setTransList(listRecipient);
            recipient.setBalance(recipient.getBalance() + amount);

            TransactionsLinkedList listSender = sender.getTransList();
            listSender.addTransaction(transCredit);
            sender.setTransList(listSender);
            sender.setBalance(sender.getBalance() - amount);
        }
    }

    public Transaction[] getTransList(int id) {
        return usersList.getUserById(id).getTransList().toArray();
    }

    public void removeTransaction(int userId, UUID transId) {
        usersList.getUserById(userId).getTransList().removeTransaction(transId);
        countRemoving++;
    }

    public Transaction[] checkTransList() {
        Transaction[] array = new Transaction[countRemoving];
        User user, userSender;
        boolean flag;
        int j = 0;
        for (int i = 0; i < usersList.getNumberUsers(); i++) {
            user = usersList.getUserByIndex(i);
            for (Transaction trans : user.getTransList().toArray()) {
                flag = false;
                userSender = trans.getSender();
                for (Transaction transSender : userSender.getTransList().toArray()) {
                    if (trans.getId() == transSender.getId()) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    array[j++] = trans;
                }
            }
        }
        return array;
    }


}