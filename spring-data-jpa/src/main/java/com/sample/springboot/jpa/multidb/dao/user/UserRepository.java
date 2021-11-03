package com.sample.springboot.jpa.multidb.dao.user;

import com.sample.springboot.jpa.multidb.model.user.UserMultipleDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserMultipleDB, Integer> {
    
}
