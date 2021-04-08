package com.example.demo;

import org.jetbrains.annotations.NotNull;

public class Book {

    public Book(String id, @NotNull String title, @NotNull String author, @NotNull String publisher, String genre, int yop)
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
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getGenre() {
        return genre;
    }

    public String getId() {
        return id;
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
