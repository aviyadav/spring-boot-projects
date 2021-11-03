package com.sample.spring.boot.basic.persistence.repo;

import com.sample.spring.boot.basic.persistence.model.Book;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByTitle(String title);
}
