package org.sample.spring.boot.marketapi.listener;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sample.spring.boot.marketapi.event.BasketConfirmEvent;
import org.sample.spring.boot.marketapi.model.Product;
import org.sample.spring.boot.marketapi.service.ProductService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BasketListener {

  private final ProductService productService;

  // When you confirmed the basket, you must decrease the quantity of products in the basket
  @EventListener(BasketConfirmEvent.class)
  public void decreaseProductCount(BasketConfirmEvent event) {
    var products = productService.decreaseCounts(event.getProductIds());
    log.info(
        "Product counts decreased with names: {}, new quantities: {}",
        products.stream().map(Product::getName).collect(Collectors.toList()),
        products.stream().map(Product::getQuantity).collect(Collectors.toList()));
  }

  // Simulate E-mail
  @EventListener(BasketConfirmEvent.class)
  public void handle(BasketConfirmEvent event) {
    var productNames =
        productService.findAllByidIn(event.getProductIds()).stream()
            .map(Product::getName)
            .collect(Collectors.toList());
    log.info("E-mail: Your basket confirmed with products: {}", productNames);
  }
}