package com.example.leaguecrud.rest;

import com.example.leaguecrud.request.PlayerRequest;
import com.example.leaguecrud.response.PlayerResponse;
import com.example.leaguecrud.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
public class PlayerController {
    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/players")
    public ResponseEntity<PlayerResponse> createPlayer(@RequestBody PlayerRequest request) throws ChangeSetPersister.NotFoundException {
        PlayerResponse response = playerService.createPlayer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/players/{id}")
    public ResponseEntity<PlayerResponse> updatePlayer(@PathVariable int id, @RequestBody PlayerRequest request) throws ChangeSetPersister.NotFoundException {
        PlayerResponse response = playerService.updatePlayer(id, request);
        return ResponseEntity.ok(response);
    }
}
