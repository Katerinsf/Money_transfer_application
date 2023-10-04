package ex03;

public class UsersArrayList implements UsersList {
    private int size = 10;
    private int number = 0;
    private User[] users = new User[size];

    public void addUser(User newUser) {
        number++;
        if (number > size) {
            User[] newUsersList = new User[size * 2];
            System.arraycopy(users, 0, newUsersList, 0, size);
            size *= 2;
            users = newUsersList;
        }
        users[number - 1] = newUser;
    }

    public User getUserById(int userId) {
        for (int i = 0; i < number; i++) {
            if (users[i].getId() == userId) {
                return users[i];
            }
        }
        throw new UserNotFoundException("User with this id was not found");
    }

    public User getUserByIndex(int userIndex) {
        if (userIndex < 0 || userIndex >= number) {
            throw new UserNotFoundException("User with this index was not found");
        }
        return users[userIndex];
    }

    public int getNumberUsers() {
        return number;
    }
}