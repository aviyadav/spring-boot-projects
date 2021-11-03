package org.sample.springboot.jpa;

import java.util.Calendar;
import org.sample.springboot.jpa.model.Gender;
import org.sample.springboot.jpa.model.User;
import org.sample.springboot.jpa.model.UserProfile;
import org.sample.springboot.jpa.repository.UserProfileRepository;
import org.sample.springboot.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaOneToOneDemoApplication implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserProfileRepository userProfileRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaOneToOneDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userProfileRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
        
        User user = new User("Abcde", "Wxyz", "Abcde.Wxyz@someemail.com", "MY_SECRET_PASSWORD");
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(1992, 8, 22);
        
        
        UserProfile userProfile = new UserProfile("+91-1234567890", Gender.MALE, dateOfBirth.getTime(), "747", "2nd Cross", "Golf View Road, Kodihalli", "Bangalore", "Karnataka", "India", "560008");
        user.setUserProfile(userProfile);
        userProfile.setUser(user);
        userRepository.save(user);

    }
}
