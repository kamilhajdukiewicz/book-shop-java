package com.example.demo;

public class Book {

    public Book(String id, String title, String author, String publisher, String genre, int yop)
    {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.yop = yop;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return title;
    }

    public String getPublisher() {
        return title;
    }

    public String getGenre() {
        return title;
    }

    public String getId() {
        return title;
    }

    public int getYop() {
        return yop;
    }

    public void setId(String id) {
        this.id = id;
    };

    public void setTitle(String title) {
        this.title = title;
    };

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setYop(int yop) {
        this.yop = yop;
    }

    private String id;
    private String title;
    private String author;
    private String publisher;
    private String genre;
    private int yop;
}
