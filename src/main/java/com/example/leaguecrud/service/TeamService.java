package com.example.leaguecrud.service;

import com.example.leaguecrud.repository.LeagueRepository;
import com.example.leaguecrud.repository.TeamRepository;
import com.example.leaguecrud.response.TeamResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.example.leaguecrud.entity.Team;
import com.example.leaguecrud.request.TeamRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.crossstore.ChangeSetPersister;

@Service
public class TeamService {
    private TeamRepository teamRepository;
    private LeagueRepository leagueRepository;
    private ModelMapper modelMapper;

    @Autowired
    public TeamService(TeamRepository teamRepository, LeagueRepository leagueRepository, ModelMapper modelMapper) {
        this.teamRepository = teamRepository;
        this.leagueRepository = leagueRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public TeamResponse createTeam(TeamRequest request) throws ChangeSetPersister.NotFoundException {
        if (!leagueRepository.existsById(request.getLeagueId())) {
            throw new ChangeSetPersister.NotFoundException();
        }

        Team team = new Team();
        team.setName(request.getName());
        team.setLeague(leagueRepository.findById(request.getLeagueId()).get());

        TeamResponse response = modelMapper.map(teamRepository.save(team), TeamResponse.class);
        response.setLeagueId(team.getLeague().getId());

        return response;
    }

    @Transactional
    public TeamResponse updateTeam(int id, TeamRequest request) throws ChangeSetPersister.NotFoundException {
        if (!teamRepository.existsById(id) || !leagueRepository.existsById(request.getLeagueId())) {
            throw new ChangeSetPersister.NotFoundException();
        }

        Team team = teamRepository.findById(id).get();
        team.setName(request.getName());
        team.setLeague(leagueRepository.findById(request.getLeagueId()).get());

        TeamResponse response = modelMapper.map(teamRepository.save(team), TeamResponse.class);
        response.setLeagueId(team.getLeague().getId());

        return response;
    }
}
