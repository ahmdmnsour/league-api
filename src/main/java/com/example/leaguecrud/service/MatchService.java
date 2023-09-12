package com.example.leaguecrud.service;

import com.example.leaguecrud.repository.MatchRepository;
import com.example.leaguecrud.repository.TeamRepository;
import com.example.leaguecrud.request.MatchRequest;
import com.example.leaguecrud.entity.*;
import com.example.leaguecrud.response.MatchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RepositoryRestController
public class MatchService {
    private MatchRepository matchRepository;
    private TeamRepository teamRepository;
    private ModelMapper modelMapper;

    @Autowired
    public MatchService(MatchRepository matchRepository,
                        TeamRepository teamRepository,
                        ModelMapper modelMapper) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public MatchResponse createMatch(@RequestBody MatchRequest request) throws ChangeSetPersister.NotFoundException {
        if (!teamRepository.existsById(request.getHomeTeamId()) || !teamRepository.existsById(request.getAwayTeamId())) {
            System.out.println(request.getAwayTeamId());
            throw new ChangeSetPersister.NotFoundException();
        }

        Random random = new Random();
        int homeGoals = random.nextInt(5);
        int awayGoals = random.nextInt(5);

        Match match = new Match();
        match.setMatchDate(new Date());
        match.setHomeTeam(teamRepository.findById(request.getHomeTeamId()).get());
        match.setAwayTeam(teamRepository.findById(request.getAwayTeamId()).get());
        match.setHomeTeamGoals(homeGoals);
        match.setAwayTeamGoals(awayGoals);
        match.setScorers(generateGoalScorers(match.getHomeTeam(), match.getAwayTeam(), homeGoals, awayGoals));

        MatchResponse response = modelMapper.map(matchRepository.save(match), MatchResponse.class);
        response.setHomeTeamId(match.getHomeTeam().getId());
        response.setAwayTeamId(match.getAwayTeam().getId());

        return response;
    }

    private List<Player> generateGoalScorers(Team homeTeam, Team awayTeam, int homeGoals, int awayGoals) {
        List<Player> goalScorers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < homeGoals; i++) {
            int playerIndex = random.nextInt(homeTeam.getPlayers().size());
            goalScorers.add(homeTeam.getPlayers().get(playerIndex));
            System.out.println(goalScorers);
        }

        for (int i = 0; i < awayGoals; i++) {
            int playerIndex = random.nextInt(awayTeam.getPlayers().size());
            goalScorers.add(awayTeam.getPlayers().get(playerIndex));
        }

        return goalScorers;
    }
}
