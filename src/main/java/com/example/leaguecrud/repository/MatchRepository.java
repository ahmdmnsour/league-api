package com.example.leaguecrud.repository;

import com.example.leaguecrud.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Integer> {
}
