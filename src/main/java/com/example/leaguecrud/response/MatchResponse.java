package com.example.leaguecrud.response;

import com.example.leaguecrud.entity.Player;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
public class MatchResponse {
    private int id;
    private Date matchDate;
    private int homeTeamId;
    private int awayTeamId;
    private int homeTeamGoals;
    private int awayTeamGoals;
    private List<Player> scorers;
}
