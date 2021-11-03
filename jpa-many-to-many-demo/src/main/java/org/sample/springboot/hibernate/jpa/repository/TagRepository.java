package org.sample.springboot.hibernate.jpa.repository;

import org.sample.springboot.hibernate.jpa.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
