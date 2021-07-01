package com.pia.footballservice.model.exception;

import java.text.MessageFormat;

public class PlayerNotFoundException extends RuntimeException{
    public PlayerNotFoundException(final Long id){super(MessageFormat.format("Could not find Player with id: {0}", id));}
}
