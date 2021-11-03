package com.sample.springboot.jpa.multidb.dao.user;

import com.sample.springboot.jpa.multidb.model.user.PossessionMultipleDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PossessionRepository extends JpaRepository<PossessionMultipleDB, Long> {
    
}
