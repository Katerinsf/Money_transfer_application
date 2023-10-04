package ex03;

public interface UsersList {
    public void addUser(User newUser);
    public User getUserById(int userId);
    public User getUserByIndex(int userIndex);
    public int getNumberUsers();
}