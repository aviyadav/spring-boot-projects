package com.sample.amis.demo.services.books.service.infrastructure.persistence;

import com.sample.amis.demo.services.books.service.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {
}
