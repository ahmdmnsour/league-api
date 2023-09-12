package com.example.leaguecrud.service;

import com.example.leaguecrud.repository.LeagueRepository;
import com.example.leaguecrud.repository.RefereeRepository;
import com.example.leaguecrud.response.RefereeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.example.leaguecrud.entity.Referee;
import com.example.leaguecrud.request.RefereeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.crossstore.ChangeSetPersister;

@Service
public class RefereeService {
    private RefereeRepository refereeRepository;
    private LeagueRepository leagueRepository;
    private ModelMapper modelMapper;

    @Autowired
    public RefereeService(RefereeRepository refereeRepository, LeagueRepository leagueRepository, ModelMapper modelMapper) {
        this.refereeRepository = refereeRepository;
        this.leagueRepository = leagueRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public RefereeResponse createReferee(RefereeRequest request) throws ChangeSetPersister.NotFoundException {
        if (!leagueRepository.existsById(request.getLeagueId())) {
            throw new ChangeSetPersister.NotFoundException();
        }

        Referee referee = new Referee();
        referee.setName(request.getName());
        referee.setLeague(leagueRepository.findById(request.getLeagueId()).get());

        RefereeResponse response = modelMapper.map(refereeRepository.save(referee), RefereeResponse.class);
        response.setLeagueId(referee.getLeague().getId());

        return response;
    }

    @Transactional
    public RefereeResponse updateReferee(int id, RefereeRequest request) throws ChangeSetPersister.NotFoundException {
        if (!refereeRepository.existsById(id) || !leagueRepository.existsById(request.getLeagueId())) {
            throw new ChangeSetPersister.NotFoundException();
        }

        Referee referee = refereeRepository.findById(id).get();
        referee.setName(request.getName());
        referee.setLeague(leagueRepository.findById(request.getLeagueId()).get());

        RefereeResponse response = modelMapper.map(refereeRepository.save(referee), RefereeResponse.class);
        response.setLeagueId(referee.getLeague().getId());

        return response;
    }
}
