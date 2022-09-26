package org.example.microservices.api.datafetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.example.microservices.api.entity.Book;
import org.example.microservices.api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllBookDataFetcher implements DataFetcher<List<Book>> {
    @Autowired
    private BookRepository repository;

    @Override
    public List<Book> get(DataFetchingEnvironment environment) {
        return repository.findAll();
    }
}
