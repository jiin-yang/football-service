package com.pia.footballservice.controller;

import com.pia.footballservice.model.Player;
import com.pia.footballservice.model.dto.PlayerDto;
import com.pia.footballservice.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService){this.playerService = playerService;}

    @PostMapping
    public ResponseEntity<PlayerDto> addPlayer(@RequestBody final PlayerDto playerDto){
        Player player = playerService.addPlayer(Player.from(playerDto));
        return new ResponseEntity<>(PlayerDto.from(player), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PlayerDto>> getPlayers(){
        List<Player> players = playerService.getPlayers();
        List<PlayerDto> playersDto = players.stream().map(PlayerDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(playersDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<PlayerDto> getPlayer(@PathVariable final Long id){
        Player player = playerService.getPlayer(id);
        return new ResponseEntity<>(PlayerDto.from(player), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<PlayerDto> deletePlayer(@PathVariable final Long id){
        Player player = playerService.deletePlayer(id);
        return new ResponseEntity<>(PlayerDto.from(player), HttpStatus.OK);
    }
}
