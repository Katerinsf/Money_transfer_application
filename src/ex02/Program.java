package ex02;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Lalisa", 1000);
        User user2 = new User("Jenny", 2000);
        User user3 = new User("Rose", 3000);
        User user4 = new User("Jisoo", 4000);

        UsersArrayList usersList = new UsersArrayList();
        usersList.addUser(user1);
        usersList.addUser(user2);
        usersList.addUser(user3);
        usersList.addUser(user4);

        System.out.printf("UsersList[%d]: ", 3);
        printUserInfo(usersList.getUserByIndex(3));
        System.out.printf("      User %d: ", 3);
        printUserInfo(user3);

        System.out.printf("UsersList(id = %d): ", 2);
        printUserInfo(usersList.getUserById(2));
        System.out.printf("           User %d: ", 2);
        printUserInfo(user2);
    }

    public static void printUserInfo(User user) {
        System.out.printf("Id: %-3d   Name: %-8s    Balance: %-10.2f\n\n", user.getId(), user.getName(), user.getBalance());
    }
}
