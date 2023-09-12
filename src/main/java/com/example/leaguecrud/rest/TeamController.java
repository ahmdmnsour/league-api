package com.example.leaguecrud.rest;

import com.example.leaguecrud.request.TeamRequest;
import com.example.leaguecrud.response.TeamResponse;
import com.example.leaguecrud.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
public class TeamController {
    private TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/teams")
    public ResponseEntity<TeamResponse> createTeam(@RequestBody TeamRequest request) throws ChangeSetPersister.NotFoundException {
        TeamResponse response = teamService.createTeam(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/teams/{id}")
    public ResponseEntity<TeamResponse> updateTeam(@PathVariable int id, @RequestBody TeamRequest request) throws ChangeSetPersister.NotFoundException {
        TeamResponse response = teamService.updateTeam(id, request);
        return ResponseEntity.ok(response);
    }
}
