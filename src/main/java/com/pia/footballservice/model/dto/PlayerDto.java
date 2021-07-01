package com.pia.footballservice.model.dto;

import com.pia.footballservice.model.Citizen;
import com.pia.footballservice.model.Player;
import com.pia.footballservice.model.Position;
import lombok.Data;

import java.util.Objects;

@Data
public class PlayerDto {

    private Long id;
    private String name;
    private String surname;
    private Position position;
    private Citizen citizen;
    private int number;
    //private PlainTeamDto plainTeamDto;

    public static PlayerDto from(Player player){
        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(player.getId());
        playerDto.setName(player.getName());
        playerDto.setSurname(player.getSurname());
        playerDto.setPosition(player.getPosition());
        playerDto.setCitizen(player.getCitizen());
        playerDto.setNumber(player.getNumber());

        /*
        if(Objects.nonNull(player.getFootballTeam())){
            playerDto.setPlainTeamDto(PlainTeamDto.from(player.getFootballTeam()));
        }
        */

        return playerDto;
    }
}
