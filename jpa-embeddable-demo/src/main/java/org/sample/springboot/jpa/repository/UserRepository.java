package org.sample.springboot.jpa.repository;

import java.util.List;
import org.sample.springboot.jpa.model.Name;
import org.sample.springboot.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByName(Name name);

    List<User> findByNameFirstName(String firstName);

    List<User> findByAddressCountry(String country);
}
