package com.example.demo.web;

import com.example.demo.Book;
import com.example.demo.BookStore;
import com.example.demo.model.AddBookRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "bookstore/book")
    public String addBook(@RequestBody(required = true) AddBookRequest book) throws IOException {
        String id = UUID.randomUUID().toString();
        Book newBook = new Book(id, book.getTitle(), book.getAuthor(), book.getPublisher(), book.getGenre(), book.getYop());
        BookStore.getBookStoreInstance().addNewBookToBookStore(newBook);
        return "Id = " + id;
    }

}
