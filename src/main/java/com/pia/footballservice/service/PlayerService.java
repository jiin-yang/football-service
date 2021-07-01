package com.pia.footballservice.service;

import com.pia.footballservice.model.Player;
import com.pia.footballservice.model.exception.PlayerNotFoundException;
import com.pia.footballservice.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository){this.playerRepository = playerRepository;}

    public Player addPlayer(Player player){return playerRepository.save(player);}

    public List<Player> getPlayers(){
        return StreamSupport
                .stream(playerRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Player getPlayer(Long id){
        return playerRepository.findById(id).orElseThrow(() ->
            new PlayerNotFoundException(id));
    }

    public Player deletePlayer(Long id){
        Player player = getPlayer(id);
        playerRepository.delete(player);
        return player;
    }
}
