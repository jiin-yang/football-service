package com.pia.footballservice.service;

import com.pia.footballservice.model.Citizen;
import com.pia.footballservice.model.FootballTeam;
import com.pia.footballservice.model.Player;
import com.pia.footballservice.model.Position;
import com.pia.footballservice.model.dto.FootballTeamDto;
import com.pia.footballservice.model.dto.PlayerDto;
import com.pia.footballservice.model.exception.FootballTeamNotFoundException;
import com.pia.footballservice.model.exception.PlayerIsAlreadyAssignedException;
import com.pia.footballservice.repository.FootballTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FootballTeamService {

    private final FootballTeamRepository footballTeamRepository;
    private final PlayerService playerService;

    private static final int LIMIT_PLAYER = 18;
    private static final int LIMIT_FOREIGN_PLAYER = 6;
    private static final int LIMIT_GOALKEEPER = 2;

    @Autowired
    public FootballTeamService(FootballTeamRepository footballTeamRepository, PlayerService playerService){
        this.footballTeamRepository = footballTeamRepository;
        this.playerService = playerService;
    }

    public FootballTeam addFootballTeam(FootballTeam footballTeam){return footballTeamRepository.save(footballTeam);}

    public List<FootballTeam> getFootballTeams(){
        return StreamSupport
                .stream(footballTeamRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public FootballTeam getFootballTeam(Long id){
        return footballTeamRepository.findById(id).orElseThrow(() ->
                new FootballTeamNotFoundException(id));
    }

    public FootballTeam deleteFootballTeam(Long id){
        FootballTeam footballTeam = getFootballTeam(id);
        footballTeamRepository.delete(footballTeam);
        return footballTeam;
    }

    @Transactional
    public FootballTeam editFootballTeam(Long id, FootballTeam footballTeam){
        FootballTeam footballTeamToEdit = getFootballTeam(id);
        footballTeamToEdit.setName(footballTeam.getName());
        footballTeamToEdit.setLeague(footballTeam.getLeague());
        footballTeamToEdit.setLogo_url(footballTeam.getLogo_url());
        footballTeamToEdit.setCity(footballTeam.getCity());
        footballTeamToEdit.setYear(footballTeam.getYear());
        footballTeamToEdit.setColor1(footballTeam.getColor1());
        footballTeamToEdit.setColor2(footballTeam.getColor2());

        return footballTeamToEdit;
    }

    @Transactional
    public FootballTeam addPlayerToFootballTeam(Long footballTeamId, Long playerId){
        FootballTeam footballTeam = getFootballTeam(footballTeamId);
        Player player = playerService.getPlayer(playerId);

        if(Objects.nonNull(player.getFootballTeam())){
            throw new PlayerIsAlreadyAssignedException(playerId,
                    player.getFootballTeam().getId());
        }

        //LIMIT_PLAYER CONTROL
        if(footballTeam.getPlayers().size() < LIMIT_PLAYER){

            int foreign_counter = 0;

            for(int i = 0; i < footballTeam.getPlayers().size(); i++)
                if(footballTeam.getPlayers().get(i).getCitizen() == Citizen.FOREIGN)
                    foreign_counter++;

            //FOREIGN PLAYER CONTROL
            if(foreign_counter < LIMIT_FOREIGN_PLAYER){

                int goalkeeper_counter = 0;

                for(int i = 0; i < footballTeam.getPlayers().size(); i++)
                    if(footballTeam.getPlayers().get(i).getPosition() == Position.GOALKEEPER)
                        goalkeeper_counter++;

                //GOALKEEPER CONTROL
                if(goalkeeper_counter<LIMIT_GOALKEEPER || player.getPosition() != Position.GOALKEEPER){
                    footballTeam.addPlayer(player);
                    player.setFootballTeam(footballTeam);
                }
            }
        }

        return footballTeam;
    }

    @Transactional
    public FootballTeam removePlayerFromFootballTeam(Long footballTeamId, Long playerId){
        FootballTeam footballTeam = getFootballTeam(footballTeamId);
        Player player = playerService.getPlayer(playerId);
        footballTeam.removePlayer(player);
        return footballTeam;
    }

    @Transactional
    public FootballTeam removeAllPlayerFromFootballTeam(Long footballTeamId){
        FootballTeam footballTeam = getFootballTeam(footballTeamId);
        footballTeam.removeAllPlayer();
        return footballTeam;
    }

}
