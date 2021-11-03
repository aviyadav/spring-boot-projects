package org.sample.springboot.jpa;

import java.util.HashSet;
import java.util.Set;
import org.sample.springboot.jpa.model.Address;
import org.sample.springboot.jpa.model.User;
import org.sample.springboot.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaElementCollectionDemoApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaElementCollectionDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        
        Set<String> phoneNumbers = new HashSet<>();
        phoneNumbers.add("+91-9422267889");
        phoneNumbers.add("+91-9422243189");
        phoneNumbers.add("+91-9422243192");
        phoneNumbers.add("+91-9422243199");
        
        
        Set<Address> addresses = new HashSet<>();
        addresses.add(new Address("123", "Golf View Road", "Bangalore", "Karnataka", "India", "560008"));
        addresses.add(new Address("Plot-3224", "Electronic City", "Bangalore", "Karnataka", "India", "560001"));
        
        User user = new User("Name Only", "email@mail.com", phoneNumbers, addresses);
        userRepository.save(user);
    }
}
