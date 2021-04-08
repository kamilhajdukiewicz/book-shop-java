package com.example.demo.web;

import com.example.demo.Book;
import com.example.demo.BookStore;
import com.example.demo.model.AddBookRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
public class TestController {

    @GetMapping(path = "bookstore/book")
    public String getBooks(@RequestParam(required = false) String genre, @RequestParam(required = false) String publisher) throws IOException {
        String response = "{ }";
        System.out.println(genre);
        System.out.println(publisher);
        if(genre == null && publisher == null) {
            response = BookStore.getBookStoreInstance().getAllBooksAsJSONString();
        }
        else if(genre != null) {
            response = BookStore.getBookStoreInstance().getBooksByGenre(genre);
        }
        else {
            response = BookStore.getBookStoreInstance().getBooksFromPublisher(publisher);
        }

        return response;
    }

    @GetMapping(path = "bookstore/verify/{id}")
    public String checkIfTheBookIsAvailable(@PathVariable(value = "id") String id) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "https://books-zut.nw.r.appspot.com/store/book/" + id;
        String respond;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
            respond = BookStore.getBookStoreInstance().parseJSON(response, id);
        } catch (HttpServerErrorException e) {
            respond = e.getMessage();
        }
        return respond;
    };

    @PostMapping(value = "bookstore/book")
    public String addBook(@RequestBody(required = true) AddBookRequest book) throws IOException {
        String id = UUID.randomUUID().toString();
        Book newBook = new Book(id, book.getTitle(), book.getAuthor(), book.getPublisher(), book.getGenre(), book.getYop());
        BookStore.getBookStoreInstance().addNewBookToBookStore(newBook);
        return "Id = " + id;
    }

    @PutMapping("bookstore/book/{id}")
    public String editBook(@PathVariable(value = "id") String id, @RequestBody(required = true) AddBookRequest book) throws IOException {
        return BookStore.getBookStoreInstance().editBookById(id, book.getTitle(), book.getAuthor(), book.getPublisher(), book.getGenre(), book.getYop());
    };

    @DeleteMapping(value = "bookstore/book/{id}")
    public String deleteBookById(@PathVariable String id) throws IOException {
        BookStore.getBookStoreInstance().deleteBookById(id);
        return "Status : Book Removed";
    };

}
