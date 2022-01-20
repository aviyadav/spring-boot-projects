package org.sample.spring.boot.marketapi.service;

import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sample.spring.boot.marketapi.dto.ProductCreateRequestDTO;
import org.sample.spring.boot.marketapi.dto.ProductUpdateRequestDTO;
import org.sample.spring.boot.marketapi.event.ProductUpdatedEvent;
import org.sample.spring.boot.marketapi.model.Product;
import org.sample.spring.boot.marketapi.repository.ProductRepository;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ApplicationEventMulticaster applicationEventMulticaster;

    public Product save(ProductCreateRequestDTO requestDTO) {
        var product = new Product();
        product.setPrice(requestDTO.getPrice());
        product.setName(requestDTO.getName());
        product.setQuantity(requestDTO.getQuantity());

        return productRepository.save(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public List<Product> findAllByidIn(Collection<Long> productIds) {
        return productRepository.findAllByIdIn(productIds);
    }

    public Product findById(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with given id: " + id));
    }

    public List<Product> decreaseCounts(Collection<Long> productIds) {
        var products = productRepository.findAllByIdIn(productIds);
        for (Product product : products) {
            var quantity = product.getQuantity();
            product.setQuantity(quantity - 1);
        }
        return productRepository.saveAll(products);
    }

    public Product update(Long id, ProductUpdateRequestDTO request) {
        var product = findById(id);
        product.setPrice(request.getPrice());
        productRepository.save(product);
        applicationEventMulticaster.multicastEvent(new ProductUpdatedEvent(this, product));
        return product;
    }
}
