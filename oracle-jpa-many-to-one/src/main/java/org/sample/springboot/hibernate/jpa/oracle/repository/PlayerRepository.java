package org.sample.springboot.hibernate.jpa.oracle.repository;

import java.util.List;
import org.sample.springboot.hibernate.jpa.oracle.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
    List<Player> findByTeamId(long teamId);
}
