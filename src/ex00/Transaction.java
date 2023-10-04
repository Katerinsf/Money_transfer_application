package ex00;

import java.util.UUID;

public class Transaction {
    public enum Category {
        DEBIT,
        CREDIT
    }

    private UUID id;
    private User recipient;
    private User sender;
    private Category category;
    private double amount;

    public Transaction(UUID newId, User newRecipient, User newSender, Category newCategory, double newAmount) {
        id = newId;
        recipient = newRecipient;
        sender = newSender;
        category = newCategory;
        if ((category == Category.CREDIT && newAmount < 0) || (category == Category.DEBIT && newAmount > 0)) {
            amount = newAmount;
        } else {
            System.err.println("DEBIT - positive amounts only, CREDIT - negative amounts only");
            amount = 0;
        }
    }

    public UUID getId() {
        return id;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public Category getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public void setId(UUID newId) {
        id = newId;
    }

    public void setRecipient(User newRecipient) {
        recipient = newRecipient;
    }

    public void setSender(User newSender) {
        sender = newSender;
    }

    public void setCategory(Category newCategory) {
        if (category != newCategory) {
            amount *= -1;
            category = newCategory;
        }
    }

    public void setAmount(double newAmount) {
        if ((category == Category.CREDIT && newAmount < 0) || (category == Category.DEBIT && newAmount > 0)) {
            amount = newAmount;
        } else {
            System.err.println("DEBIT - positive amounts only, CREDIT - negative amounts only");
        }
    }

}
