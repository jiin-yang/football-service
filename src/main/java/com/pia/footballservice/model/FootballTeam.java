package com.pia.footballservice.model;

import com.pia.footballservice.model.dto.FootballTeamDto;
import com.pia.footballservice.model.dto.PlainTeamDto;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "FootballTeam")
public class FootballTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private League league;
    private String logo_url;
    private String city;
    private int year;
    private String color1;
    private String color2;

    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "football_team_id")
    private List<Player> players = new ArrayList<>();

    public void addPlayer(Player player){players.add(player);}
    public void removePlayer(Player player){players.remove(player);}
    public void removeAllPlayer(){players.clear();}

    public static FootballTeam from(FootballTeamDto footballTeamDto){
        FootballTeam footballTeam = new FootballTeam();
        footballTeam.setName(footballTeamDto.getName());
        footballTeam.setLeague(footballTeamDto.getLeague());
        footballTeam.setLogo_url(footballTeamDto.getLogo_url());
        footballTeam.setCity(footballTeamDto.getCity());
        footballTeam.setYear(footballTeamDto.getYear());
        footballTeam.setColor1(footballTeamDto.getColor1());
        footballTeam.setColor2(footballTeamDto.getColor2());

        return footballTeam;
    }

    public static FootballTeam from(PlainTeamDto footballTeamDto){
        FootballTeam footballTeam = new FootballTeam();
        footballTeam.setName(footballTeamDto.getName());
        footballTeam.setLeague(footballTeamDto.getLeague());
        footballTeam.setLogo_url(footballTeamDto.getLogo_url());
        footballTeam.setCity(footballTeamDto.getCity());
        footballTeam.setYear(footballTeamDto.getYear());
        footballTeam.setColor1(footballTeamDto.getColor1());
        footballTeam.setColor2(footballTeamDto.getColor2());

        return footballTeam;
    }
}
