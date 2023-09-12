package com.example.leaguecrud.service;

import com.example.leaguecrud.repository.CoachRepository;
import com.example.leaguecrud.repository.TeamRepository;
import com.example.leaguecrud.response.CoachResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.example.leaguecrud.entity.Coach;
import com.example.leaguecrud.request.CoachRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.crossstore.ChangeSetPersister;

@Service
public class CoachService {
    private CoachRepository coachRepository;
    private TeamRepository teamRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CoachService(CoachRepository coachRepository, TeamRepository teamRepository, ModelMapper modelMapper) {
        this.coachRepository = coachRepository;
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public CoachResponse createCoach(CoachRequest request) throws ChangeSetPersister.NotFoundException {
        if (!teamRepository.existsById(request.getTeamId())) {
            throw new ChangeSetPersister.NotFoundException();
        }

        Coach coach = new Coach();
        coach.setName(request.getName());
        coach.setTeam(teamRepository.findById(request.getTeamId()).get());

        CoachResponse response = modelMapper.map(coachRepository.save(coach), CoachResponse.class);
        response.setTeamId(coach.getTeam().getId());

        return response;
    }

    @Transactional
    public CoachResponse updateCoach(int id, CoachRequest request) throws ChangeSetPersister.NotFoundException {
        if (!coachRepository.existsById(id) || !teamRepository.existsById(request.getTeamId())) {
            throw new ChangeSetPersister.NotFoundException();
        }

        Coach coach = coachRepository.findById(id).get();
        coach.setName(request.getName());
        coach.setTeam(teamRepository.findById(request.getTeamId()).get());

        CoachResponse response = modelMapper.map(coachRepository.save(coach), CoachResponse.class);
        response.setTeamId(coach.getTeam().getId());

        return response;
    }
}
