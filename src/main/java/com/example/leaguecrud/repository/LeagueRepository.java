package com.example.leaguecrud.repository;

import com.example.leaguecrud.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueRepository extends JpaRepository<League, Integer> {
}
