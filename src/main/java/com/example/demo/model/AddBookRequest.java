package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddBookRequest {

    private String title;
    private String author;
    private String publisher;
    private String genre;
    private int yop;

}
