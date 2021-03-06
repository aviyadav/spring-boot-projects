package com.sample.springboot.jpa.multidb;

import com.sample.springboot.jpa.multidb.dao.product.ProductRepository;
import com.sample.springboot.jpa.multidb.dao.user.PossessionRepository;
import com.sample.springboot.jpa.multidb.dao.user.UserRepository;
import com.sample.springboot.jpa.multidb.model.product.ProductMultipleDB;
import com.sample.springboot.jpa.multidb.model.user.PossessionMultipleDB;
import com.sample.springboot.jpa.multidb.model.user.UserMultipleDB;
import java.util.Collections;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.springframework.dao.DataIntegrityViolationException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MultipleDbApplication.class)
@EnableTransactionManagement
public class JpaMultipleDBIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PossessionRepository possessionRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional("userTransactionManager")
    public void whenCreatingUser_thenCreated() {
        UserMultipleDB user = new UserMultipleDB();
        user.setName("John");
        user.setEmail("john@test.com");
        user.setAge(20);
        PossessionMultipleDB p = new PossessionMultipleDB("sample");
        p = possessionRepository.save(p);
        user.setPossessionList(Collections.singletonList(p));
        user = userRepository.save(user);
        final Optional<UserMultipleDB> result = userRepository.findById(user.getId());
        assertTrue(result.isPresent());
        System.out.println(result.get().getPossessionList());
        assertEquals(1, result.get().getPossessionList().size());
    }

    @Test
    @Transactional("userTransactionManager")
    public void whenCreatingUsersWithSameEmail_thenRollback() {
        UserMultipleDB user1 = new UserMultipleDB();
        user1.setName("John");
        user1.setEmail("john@test.com");
        user1.setAge(20);
        user1 = userRepository.save(user1);
        assertTrue(userRepository.findById(user1.getId()).isPresent());

        UserMultipleDB user2 = new UserMultipleDB();
        user2.setName("Tom");
        user2.setEmail("john@test.com");
        user2.setAge(10);

        try {
            user2 = userRepository.save(user2);
            userRepository.flush();
            fail("DataIntegrityViolationException should be thrown!");
        } catch (final DataIntegrityViolationException e) {
            // Expected
        } catch (final Exception e) {
            fail("DataIntegrityViolationException should be thrown, instead got: " + e);
        }
    }

    @Test
    @Transactional("productTransactionManager")
    public void whenCreatingProduct_thenCreated() {
        ProductMultipleDB product = new ProductMultipleDB();
        product.setName("Book");
        product.setId(2);
        product.setPrice(20);
        product = productRepository.save(product);

        assertTrue(productRepository.findById(product.getId()).isPresent());
    }
}
