package org.sample.jms.activemq.producer;

import org.sample.jms.activemq.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


@Component
public class JmsProducer {

    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${jsa.activemq.queue.producer}")
    String queue;

    public void send(Product product, String companyName) {
        jmsTemplate.convertAndSend(queue, product, message -> {
            message.setStringProperty("company", companyName);
            return message;
        });
    }
}
