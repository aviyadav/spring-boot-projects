package org.sample.springboot.hibernate.jpa.repository;

import org.sample.springboot.hibernate.jpa.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
