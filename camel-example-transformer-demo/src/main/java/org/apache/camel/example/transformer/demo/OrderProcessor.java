package org.apache.camel.example.transformer.demo;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class OrderProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Order order = exchange.getIn().getBody(Order.class);
        OrderResponse answer = new OrderResponse().setAccepted(true)
                .setOrderId(order.getOrderId())
                .setDescription(String.format("Order accepted:[item='%s' quantity='%s']", order.getItemId(), order.getQuantity()));
        exchange.getMessage().setBody(answer);
    }
}
