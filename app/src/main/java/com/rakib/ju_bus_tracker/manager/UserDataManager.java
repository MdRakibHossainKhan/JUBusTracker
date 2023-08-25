package com.rakib.ju_bus_tracker.manager;

import com.rakib.ju_bus_tracker.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDataManager {
    public static List<User> users = new ArrayList<>();

    static {
        // Initialize hardcoded user data
        users.add(new User("student1", "123456", User.UserType.STUDENT));
        users.add(new User("driver1", "654321", User.UserType.DRIVER));
        users.add(new User("admin1", "123654", User.UserType.ADMIN));
    }

    public static User getUserById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
}
