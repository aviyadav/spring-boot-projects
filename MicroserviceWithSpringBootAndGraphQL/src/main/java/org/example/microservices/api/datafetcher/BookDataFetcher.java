package org.example.microservices.api.datafetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.example.microservices.api.entity.Book;
import org.example.microservices.api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookDataFetcher implements DataFetcher<Book> {

    @Autowired
    private BookRepository repository;

    @Override
    public Book get(DataFetchingEnvironment environment) throws Exception {

        String bookId =  environment.getArgument("id");
        return repository.findById(bookId).get();
    }
}
