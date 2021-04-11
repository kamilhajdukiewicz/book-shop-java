package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.ResponseEntity;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public String getBooksFromPublisher(String pub) {
        ArrayList<Book> availableBooks = new ArrayList<Book>();
        String prettyJson = "{ }";
        for(Book book : books)
        {
            if(book.getPublisher().equals(pub))
            {
                availableBooks.add(book);
            }
        }
        if(availableBooks.size() > 0) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            prettyJson = prettyGson.toJson(availableBooks);
        }
        return prettyJson;
    };

    public String getBooksByGenre(String gen) {
        ArrayList<Book> availableBooks = new ArrayList<Book>();
        String prettyJson = "{ }";
        for(Book book : books)
        {
            if(book.getGenre().equals(gen))
            {
                availableBooks.add(book);
            }
        }
        if(availableBooks.size() > 0) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            prettyJson = prettyGson.toJson(availableBooks);
        }
        return prettyJson;
    };

    public void deleteBookById(String id) {
        for(int i = 0; i < books.size(); i++)
        {
            if(books.get(i).getId().equals(id))
            {
                books.remove(i);
            }
        }
    }

    public String editBookById(String id, String title, String author, String publisher, String genre, int yop) {
        int index = 0;
        for(int i = 0; i < books.size(); i++)
        {
            if(books.get(i).getId().equals(id))
            {
                books.get(i).setTitle(title);
                books.get(i).setAuthor(author);
                books.get(i).setPublisher(publisher);
                books.get(i).setGenre(genre);
                books.get(i).setYop(yop);
                index = i;
            }
        }
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = prettyGson.toJson(books.get(index));
        return prettyJson;
    }

    public String parseJSON(ResponseEntity<String> response, String id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());

        String title = root.path("tt").asText();
        String author = root.path("at").asText();
        String publisher = root.path("pl").asText();
        String genre = root.path("gn").asText();
        int yop = Integer.parseInt(root.path("pub").asText());

        Book newBook = new Book(id, title, author, publisher, genre, yop);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = prettyGson.toJson(newBook);
        return prettyJson;
    }

    public String getRandomBook() {
        int size = books.size();
        Random random = new Random();
        int randomIndex = random.nextInt(size);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = prettyGson.toJson(books.get(randomIndex));
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
