package ex01;

import ex00.User;

public class Program {
    public static void main(String[] args) {
        System.out.println(" - Creating of user1 - ");
        ex00.User user1 = new ex00.User(1, "Rose", 1000);
        printUserInfo(user1);
        user1.setId(10);
        user1.setName("Rosanna");
        user1.setBalance(-2000);
        printUserInfo(user1);

        System.out.println(" - Creating of user2 - ");
        ex00.User user2 = new User(2, "Jenny", -678.901);
        printUserInfo(user2);
        user2.setId(20);
        user2.setName("Jen");
        user2.setBalance(2000);
        printUserInfo(user2);
    }

    public static void printUserInfo(User user) {
        System.out.printf("Id: %-3d   Name: %-8s    Balance: %-10.2f\n\n", user.getId(), user.getName(), user.getBalance());
    }
}
