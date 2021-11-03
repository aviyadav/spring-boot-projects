package com.sample.amis.demo.services.books.service.presentation;

import com.sample.amis.demo.services.books.service.application.BookService;
import com.sample.amis.demo.services.books.service.domain.Book;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable String bookId){
        return bookService.getBook(bookId);
    }

    @PostMapping("/books")
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @PutMapping("/books/{id}")
    public void updateBook(@PathVariable String bookId, @RequestBody Book book) {
        bookService.updateBook(bookId, book);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable String bookId) {
        bookService.deleteBook(bookId);
    }
}
