package com.pia.footballservice.model.dto;

import com.pia.footballservice.model.FootballTeam;
import com.pia.footballservice.model.League;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class FootballTeamDto {
    private Long id;

    private String name;
    private League league;
    private String logo_url;
    private String city;
    private int year;
    private String color1;
    private String color2;
    private List<PlayerDto> playersDto = new ArrayList<>();

    public static FootballTeamDto from(FootballTeam footballTeam){
        FootballTeamDto footballTeamDto = new FootballTeamDto();
        footballTeamDto.setId(footballTeam.getId());
        footballTeamDto.setName(footballTeam.getName());
        footballTeamDto.setLeague(footballTeam.getLeague());
        footballTeamDto.setLogo_url(footballTeam.getLogo_url());
        footballTeamDto.setCity(footballTeam.getCity());
        footballTeamDto.setYear(footballTeam.getYear());
        footballTeamDto.setColor1(footballTeam.getColor1());
        footballTeamDto.setColor2(footballTeam.getColor2());
        footballTeamDto.setPlayersDto(footballTeam.getPlayers().stream().map(PlayerDto::from).collect(Collectors.toList()));

        return footballTeamDto;
    }
}
