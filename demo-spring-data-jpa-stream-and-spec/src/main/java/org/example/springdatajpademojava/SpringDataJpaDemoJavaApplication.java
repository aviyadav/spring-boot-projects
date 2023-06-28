package org.example.springdatajpademojava;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringDataJpaDemoJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaDemoJavaApplication.class, args);
    }
}

// run - gradle bootRun , open http://localhost:8080/customer_findall