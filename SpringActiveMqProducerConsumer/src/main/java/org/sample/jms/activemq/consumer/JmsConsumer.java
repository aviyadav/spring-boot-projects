package org.sample.jms.activemq.consumer;

import org.sample.jms.activemq.model.Product;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class JmsConsumer {

    @JmsListener(destination = "${jsa.activemq.queue.apple}", containerFactory = "jsaFactory")
    public void appleReceive(Product product, @Header("type") String productType) {
        if("iPhone".equals(productType)) {
            System.out.println("Received iPhone: " + product);
        } else if("iPad".equals(productType)) {
            System.out.println("Received iPad: " + product);
        }
    }

    @JmsListener(destination = "${jsa.activemq.queue.samsung}", containerFactory = "jsaFactory")
    public void samsungReceive(Product product, @Header("type") String productType) {
        if("SmartWatch".equals(productType)) {
            System.out.println("Received SmartWatch: " + product);
        } else if("SmartPhone".equals(productType)) {
            System.out.println("Received SmartPhone: " + product);
        }
    }
}
