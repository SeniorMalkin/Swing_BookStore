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

    @Override
    public String toString() {
        return "Author: name:" + name + "  email:" + email + " gender:" + gender;
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
