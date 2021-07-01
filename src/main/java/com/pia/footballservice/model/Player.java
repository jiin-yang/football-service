package com.pia.footballservice.model;

import com.pia.footballservice.model.dto.PlayerDto;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private Position position;
    private Citizen citizen;
    private int number;
    @ManyToOne
    private FootballTeam footballTeam;

    public static Player from(PlayerDto playerDto){
        Player player = new Player();
        player.setName(playerDto.getName());
        player.setSurname(playerDto.getSurname());
        player.setPosition(playerDto.getPosition());
        player.setCitizen(playerDto.getCitizen());
        player.setNumber(playerDto.getNumber());

        return player;
    }

}
