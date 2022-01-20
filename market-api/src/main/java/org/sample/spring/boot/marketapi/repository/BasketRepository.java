package org.sample.spring.boot.marketapi.repository;

import org.sample.spring.boot.marketapi.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {}
