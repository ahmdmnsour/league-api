package com.example.leaguecrud.service;

import com.example.leaguecrud.repository.PlayerRepository;
import com.example.leaguecrud.repository.TeamRepository;
import com.example.leaguecrud.response.PlayerResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.example.leaguecrud.entity.Player;
import com.example.leaguecrud.request.PlayerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.crossstore.ChangeSetPersister;

@Service
public class PlayerService {
    private PlayerRepository playerRepository;
    private TeamRepository teamRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository, ModelMapper modelMapper) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public PlayerResponse createPlayer(PlayerRequest request) throws ChangeSetPersister.NotFoundException {
        if (!teamRepository.existsById(request.getTeamId())) {
            throw new ChangeSetPersister.NotFoundException();
        }

        Player player = new Player();
        player.setName(request.getName());
        player.setTeam(teamRepository.findById(request.getTeamId()).get());

        PlayerResponse response = modelMapper.map(playerRepository.save(player), PlayerResponse.class);
        response.setTeamId(player.getTeam().getId());

        return response;
    }

    @Transactional
    public PlayerResponse updatePlayer(int id, PlayerRequest request) throws ChangeSetPersister.NotFoundException {
        if (!playerRepository.existsById(id) || !teamRepository.existsById(request.getTeamId())) {
            throw new ChangeSetPersister.NotFoundException();
        }

        Player player = playerRepository.findById(id).get();
        player.setName(request.getName());
        player.setTeam(teamRepository.findById(request.getTeamId()).get());

        PlayerResponse response = modelMapper.map(playerRepository.save(player), PlayerResponse.class);
        response.setTeamId(player.getTeam().getId());

        return response;
    }
}
