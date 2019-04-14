package com.springThyme.cheesemvc.models.data;

import com.springThyme.cheesemvc.models.User;

import java.util.Collection;
import java.util.HashMap;

public class UserManager {
    private static HashMap<Integer, User> users= new HashMap<>();

    public static Collection< User> getAll() {
        return users.values();
    }

    public static void addUser(User user) {
        users.put(user.getID(), user);
    }

    public static User getByID(int ID) {
        return users.get(ID);
    }
}
