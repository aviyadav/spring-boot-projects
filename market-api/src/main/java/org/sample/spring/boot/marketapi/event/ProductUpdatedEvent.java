package org.sample.spring.boot.marketapi.event;

import lombok.Getter;
import org.sample.spring.boot.marketapi.model.Product;
import org.springframework.context.ApplicationEvent;

@Getter
public class ProductUpdatedEvent extends ApplicationEvent {

    private final Product product;

    public ProductUpdatedEvent(Object source, Product product) {
        super(source);
        this.product = product;
    }
}
