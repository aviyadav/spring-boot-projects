package org.sample.spring.boot.marketapi.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sample.spring.boot.marketapi.dto.ProductCreateRequestDTO;
import org.sample.spring.boot.marketapi.dto.ProductUpdateRequestDTO;
import org.sample.spring.boot.marketapi.model.Product;
import org.sample.spring.boot.marketapi.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ProductController.ENDPOINT)
@RequiredArgsConstructor
public class ProductController {

    public static final String ENDPOINT = "product";
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody @Validated ProductCreateRequestDTO product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Product> update(
            @PathVariable("id") Long id, @RequestBody @Validated ProductUpdateRequestDTO request) {
        return new ResponseEntity<>(productService.update(id, request), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> get() {
        return ResponseEntity.ok(productService.getAll());
    }
}
