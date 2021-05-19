package org.sample.rest.spring;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRespository extends JpaRepository<Blog, Integer> {
    List<Blog> findByTitleContainingOrContentContaining(String text, String textAgain);
}
