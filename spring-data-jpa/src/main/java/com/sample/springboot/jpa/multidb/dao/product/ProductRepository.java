package com.sample.springboot.jpa.multidb.dao.product;

import com.sample.springboot.jpa.multidb.model.product.ProductMultipleDB;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<ProductMultipleDB, Integer> {
    
    List<ProductMultipleDB> findAllByPrice(double price, Pageable pageable);
}
