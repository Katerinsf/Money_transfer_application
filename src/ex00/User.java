package ex00;

public class User {
    private int id;
    private String name;
    private double balance;

    public User(int newId, String newName, double newBalance) {
        id = newId;
        name = newName;
        if (newBalance < 0) {
            System.err.println("Balance cannot be negative");
            balance = 0;
        } else {
            balance = newBalance;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setId(int newId) {
        id = newId;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setBalance(double newBalance) {
        if (newBalance < 0) {
            System.err.println("Balance cannot be negative");
        } else {
            balance = newBalance;
        }
    }

}
