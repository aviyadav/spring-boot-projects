package org.sample.springboot.hibernate.jpa.oracle.service;

import java.util.ArrayList;
import java.util.List;
import org.sample.springboot.hibernate.jpa.oracle.model.Player;
import org.sample.springboot.hibernate.jpa.oracle.model.Team;
import org.sample.springboot.hibernate.jpa.oracle.repository.PlayerRepository;
import org.sample.springboot.hibernate.jpa.oracle.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SoccerServiceImpl implements SoccerService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<String> getAllTeamPlayers(long teamId) {
        List<String> result = new ArrayList<>();

        List<Player> players = playerRepository.findByTeamId(teamId);
        for (Player player : players) {
            result.add(player.getName());
        }

        return result;
    }

    @Override
    public void addBarcelonaPlayer(String name, String position, int number) {
        // Team barcelona = teamRepository.findById(1l).get();
        Team barcelona = new Team();
        barcelona.setName("barcelona");
        teamRepository.save(barcelona);

        Player newPlayer = new Player();
        newPlayer.setName(name);
        newPlayer.setPosition(position);
        newPlayer.setNum(number);
        newPlayer.setTeam(barcelona);
        playerRepository.save(newPlayer);
    }

}
