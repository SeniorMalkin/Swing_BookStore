package com.netcracker.BookStore;

import java.io.Serializable;

public class Author implements Serializable {
    private  String name;
    private String email;
    private char gender;



    public Author(String name, String email, char gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }
    public String getEmail(){ return email;}
    public char getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Name:" + name + "\n" + "Email:" + email + "\nGender:" + gender;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Author aut = (Author) obj;
        return name.equals(aut.name) && email.equals(aut.email) && gender == aut.gender;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result += email.hashCode();
        result += (int) gender;
        return result;
    }
}
