package com.pia.footballservice.model.exception;

import java.text.MessageFormat;

public class PlayerIsAlreadyAssignedException extends RuntimeException{
    public PlayerIsAlreadyAssignedException(final Long playerId, final Long footballTeamId){
        super(MessageFormat.format("Player: {0} is already assigned to Team: {1}", playerId, footballTeamId));
    }
}
