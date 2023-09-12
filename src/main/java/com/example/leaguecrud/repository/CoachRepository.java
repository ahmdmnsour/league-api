package com.example.leaguecrud.repository;

import com.example.leaguecrud.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach, Integer> {
}
