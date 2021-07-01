package com.pia.footballservice.model.exception;

import java.text.MessageFormat;

public class FootballTeamNotFoundException extends RuntimeException {
    public FootballTeamNotFoundException(final Long id){super(MessageFormat.format("Could not find team with id{0}", id));}
}
