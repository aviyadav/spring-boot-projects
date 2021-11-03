package org.sample.spring.integration;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("/integration/integration.xml")
public class IntegrationApplication {
    public static void main(String[] args) throws IOException {
        try (ConfigurableApplicationContext ctx = new SpringApplication(IntegrationApplication.class).run(args)) {
            System.out.println("Hit Enter to terminate");
            System.in.read();
        }
    }
}
