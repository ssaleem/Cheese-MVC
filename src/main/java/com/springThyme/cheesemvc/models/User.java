package com.springThyme.cheesemvc.models;

import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Transient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long ID;

    @NotNull
    @Size(min = 5, max = 15, message="Name must be between 5 and 15 characters")
    private String name;

    @Email
    private String email;

    @NotNull
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @Transient  //this field is not stored in the DB table
    @NotNull(message = "Passwords do not match")
    private String verifyPassword;

    private Date joinDate;

    public User() {
        this.joinDate = new Date();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        System.out.println("password");
        this.password = password;
        checkPassword();
    }

    public void setVerifyPassword(String verifyPassword) {
        System.out.println("verifypassword");
        this.verifyPassword = verifyPassword;
        checkPassword();
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

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public long getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    private void checkPassword(){
        if(password == null || verifyPassword == null || !password.equals(verifyPassword)) {
            verifyPassword = null;
        }
    }
}
