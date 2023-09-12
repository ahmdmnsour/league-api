package com.example.leaguecrud.repository;

import com.example.leaguecrud.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {
}
