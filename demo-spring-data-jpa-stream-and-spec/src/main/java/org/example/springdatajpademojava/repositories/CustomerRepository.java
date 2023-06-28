package org.example.springdatajpademojava.repositories;

import org.example.springdatajpademojava.domain.Customer;
import org.example.springdatajpademojava.repositories.fragments.StreamableJpaSpecificationRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long>, JpaSpecificationExecutor<Customer>, StreamableJpaSpecificationRepository<Customer> {
    @Query("select c from Customer c")
    Stream<Customer> streamQueryAnnotation();

    @Query("select c from Customer c")
    Stream<Customer> streamWithPageable(Pageable pageable);
}
