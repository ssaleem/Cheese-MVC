package com.springThyme.cheesemvc.models;

import java.util.Date;
import java.util.Objects;

public class User {

    private static int nextID = 0;

    private int ID;
    private String name;
    private String email;
    private String password;
    private Date joinDate;

    public User() {
        this.joinDate = new Date();
        this.ID = ++nextID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getID() {
        return ID;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
