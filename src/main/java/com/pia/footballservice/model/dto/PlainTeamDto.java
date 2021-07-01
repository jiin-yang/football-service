package com.pia.footballservice.model.dto;

import com.pia.footballservice.model.FootballTeam;
import com.pia.footballservice.model.League;
import lombok.Data;

@Data
public class PlainTeamDto {
    private Long id;
    private String name;
    private League league;
    private String logo_url;
    private String city;
    private int year;
    private String color1;
    private String color2;

    public static PlainTeamDto from(FootballTeam footballTeam){
        PlainTeamDto plainTeamDto = new PlainTeamDto();
        plainTeamDto.setId(footballTeam.getId());
        plainTeamDto.setName(footballTeam.getName());
        plainTeamDto.setLeague(footballTeam.getLeague());
        plainTeamDto.setLogo_url(footballTeam.getLogo_url());
        plainTeamDto.setCity(footballTeam.getCity());
        plainTeamDto.setYear(footballTeam.getYear());
        plainTeamDto.setColor1(footballTeam.getColor1());
        plainTeamDto.setColor2(footballTeam.getColor2());

        return plainTeamDto;
    }
}
