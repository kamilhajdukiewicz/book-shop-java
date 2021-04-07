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
    public String getBooks() throws IOException {
        return BookStore.getBookStoreInstance().getAllBooksAsJSONString();
    }

    @GetMapping("bookstore/book?")
    @ResponseBody
    public String getBooksFromPublisher(@RequestParam String publisher) throws IOException {
        return BookStore.getBookStoreInstance().getBooksFromPublisher(publisher);
    }

    @PostMapping(value = "bookstore/book")
    public String addBook(@RequestBody(required = true) AddBookRequest book) throws IOException {
        String id = UUID.randomUUID().toString();
        Book newBook = new Book(id, book.getTitle(), book.getAuthor(), book.getPublisher(), book.getGenre(), book.getYop());
        BookStore.getBookStoreInstance().addNewBookToBookStore(newBook);
        return "Id = " + id;
    }

}
