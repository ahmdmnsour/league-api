package com.example.leaguecrud.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "league_id", nullable = false)
    private League league;
    @OneToOne(mappedBy = "team")
    private Coach coach;
    @OneToMany(mappedBy = "team")
    private List<Player> players;
    @OneToMany(mappedBy = "homeTeam")
    private List<Match> homeMatches;
    @OneToMany(mappedBy = "awayTeam")
    private List<Match> awayMatches;
}
