package com.data.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataDemoApplication implements CommandLineRunner {
    
    @Autowired
    private BookRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        
        Book book = new Book();
        book.setTitle("Book 1");
        book.setContent("This is Book 1");
        
        Book book2 = new Book();
        book2.setTitle("Book 2");
        book2.setContent("This is Book 2");
        
        repository.save(book);
        repository.save(book2);
    }

}
