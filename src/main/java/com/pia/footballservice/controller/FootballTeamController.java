package com.pia.footballservice.controller;

import com.pia.footballservice.model.FootballTeam;
import com.pia.footballservice.model.dto.FootballTeamDto;
import com.pia.footballservice.model.dto.PlainTeamDto;
import com.pia.footballservice.service.FootballTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teams")
public class FootballTeamController {

    private FootballTeamService footballTeamService;

    @Autowired
    public FootballTeamController(FootballTeamService footballTeamService){this.footballTeamService = footballTeamService;}

    @PostMapping
    public ResponseEntity<PlainTeamDto> addTeam(@RequestBody final PlainTeamDto plainTeamDto){
        FootballTeam footballTeam = footballTeamService.addFootballTeam(FootballTeam.from(plainTeamDto));
        return new ResponseEntity<>(PlainTeamDto.from(footballTeam), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FootballTeamDto>> getTeams(){
        List<FootballTeam> footballTeams = footballTeamService.getFootballTeams();
        List<FootballTeamDto> footballTeamDtos = footballTeams.stream().map(FootballTeamDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(footballTeamDtos, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<FootballTeamDto> getTeam(@PathVariable final Long id){
        FootballTeam footballTeam = footballTeamService.getFootballTeam(id);
        return new ResponseEntity<>(FootballTeamDto.from(footballTeam), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<FootballTeamDto> deleteTeam(@PathVariable final Long id){
        FootballTeam footballTeam = footballTeamService.deleteFootballTeam(id);
        return new ResponseEntity<>(FootballTeamDto.from(footballTeam), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<FootballTeamDto> editTeam(@PathVariable final Long id,
                                            @RequestBody final FootballTeamDto footballTeamDto){
        FootballTeam footballTeam = footballTeamService.editFootballTeam(id, FootballTeam.from(footballTeamDto));
        return new ResponseEntity<>(FootballTeamDto.from(footballTeam), HttpStatus.OK);
    }

    @PostMapping(value = "{teamId}/players/{playerId}")
    public ResponseEntity<FootballTeamDto> addPlayerToTeam(@PathVariable final Long teamId,
                                                 @PathVariable final Long playerId){
        FootballTeam footballTeam = footballTeamService.addPlayerToFootballTeam(teamId, playerId);
        return new ResponseEntity<>(FootballTeamDto.from(footballTeam), HttpStatus.OK);
    }

    @DeleteMapping(value = "{teamId}/players/{playerId}")
    public ResponseEntity<FootballTeamDto> removePlayerFromTeam(@PathVariable final Long teamId,
                                                      @PathVariable final Long playerId){
        FootballTeam footballTeam = footballTeamService.removePlayerFromFootballTeam(teamId, playerId);
        return new ResponseEntity<>(FootballTeamDto.from(footballTeam), HttpStatus.OK);
    }

    @DeleteMapping(value = "{teamId}/removeAllPlayers")
    public ResponseEntity<FootballTeamDto> removeAllPlayerFromTeam(@PathVariable final Long teamId){
        FootballTeam footballTeam = footballTeamService.removeAllPlayerFromFootballTeam(teamId);
        return new ResponseEntity<>(FootballTeamDto.from(footballTeam), HttpStatus.OK);
    }
}
