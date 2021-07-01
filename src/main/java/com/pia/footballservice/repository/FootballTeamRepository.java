package com.pia.footballservice.repository;

import com.pia.footballservice.model.FootballTeam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FootballTeamRepository extends CrudRepository<FootballTeam, Long> {
}
