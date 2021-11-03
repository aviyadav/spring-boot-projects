package org.sample.jms.activemq;

import org.sample.jms.activemq.model.Product;
import org.sample.jms.activemq.producer.JmsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringActiveMqProducerConsumerApplication implements CommandLineRunner {

    @Autowired
    JmsProducer producer;

    public static void main(String[] args) {
        SpringApplication.run(SpringActiveMqProducerConsumerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Product iPhoneXSMax = new Product("iPhone xS Max", "iPhone");
        Product iPadPro = new Product("iPad Pro", "iPad");

        producer.send(iPhoneXSMax, "apple");
        producer.send(iPadPro, "apple");

        Product samsungGalaxyS10 = new Product("Samsung Galaxy S10", "SmartPhone");
        Product galaxyS3 = new Product("Galaxy S3", "SmartWatch");

        producer.send(iPhoneXSMax, "samsung");
        producer.send(iPadPro, "samsung");
    }
}
