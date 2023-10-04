package ex02;

public class UserIdsGenerator {
    private static int userId = 0;
    private static UserIdsGenerator instance;

    private UserIdsGenerator() { }

    public static UserIdsGenerator getInstance() {
        if (instance == null) {
            instance = new UserIdsGenerator();
        }
        return instance;
    }

    public static int generateId() {
        userId++;
        return userId;
    }

}
