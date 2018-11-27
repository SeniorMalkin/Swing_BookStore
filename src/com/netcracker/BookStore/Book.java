package com.netcracker.BookStore;

import java.io.Serializable;
import java.util.List;

enum Genre {ScienceFiction,Satire,Drama,ActionAndAdventure,Romance,Mystery,Horror};
public class Book implements Serializable {
    private List<Author> authors;
    private String title;
    private Genre genre;
    private String publishOffice;
    private String year;
    private int qty;
    private double price;
    private String AgeRequire;

    public Book(List<Author> authors, String title, Genre genre, String publishOffice, String year, int qty, double price, String ageRequire) {
        this.authors = authors;
        this.title = title;
        this.genre = genre;
        this.publishOffice = publishOffice;
        this.year = year;
        this.qty = qty;
        this.price = price;
        this.AgeRequire = ageRequire;
    }

    public Book(List<Author> authors, String title, Genre genre, double price) {
        this.authors = authors;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.publishOffice = "ACT";
        this.year = "2018";
        this.qty = 1;
        this.AgeRequire = "0+";
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getPublishOffice() {
        return publishOffice;
    }

    public void setPublishOffice(String publishOffice) {
        this.publishOffice = publishOffice;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAgeRequire() {
        return AgeRequire;
    }

    public void setAgeRequire(String ageRequire) {
        AgeRequire = ageRequire;
    }
}
