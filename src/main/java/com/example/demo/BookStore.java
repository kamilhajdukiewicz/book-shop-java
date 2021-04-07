package com.example.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public final class BookStore {

    private static BookStore bookStoreInstance;

    private BookStore() throws IOException {
        books = new ArrayList<Book>();
        readBooksFromCSVFile();
    }

    public static BookStore getBookStoreInstance() throws IOException {
        if(bookStoreInstance == null)
        {
            bookStoreInstance = new BookStore();
        }
        return bookStoreInstance;
    }

    public String getAllBooksAsJSONString() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = prettyGson.toJson(books);
        System.out.println("\nConverted JSONObject ==> " + prettyJson);
        return prettyJson;
    }

    public void addNewBookToBookStore(Book newBook) {
        books.add(newBook);
    }

    private void readBooksFromCSVFile() throws IOException {
        String row;
        File csvFile = new File(pathToCsv);
        if (csvFile.isFile()) {
            BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(";");
                addNewBook(data);
            }
            csvReader.close();
        }
    };

    private void addNewBook(String[] data){
        try {
            books.add(new Book(data[0], data[1], data[2], data[3], data[4], Integer.parseInt(data[5])));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private ArrayList<Book> books;
    private String pathToCsv = "C:\\Users\\Kamil\\Desktop\\ProjektJava\\books.csv";
}
