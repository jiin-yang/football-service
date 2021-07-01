package com.pia.footballservice.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FootballTeamNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(FootballTeamNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String footballTeamNotFoundHandler(FootballTeamNotFoundException exception){return exception.getMessage();}
}
