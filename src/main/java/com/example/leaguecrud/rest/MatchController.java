package com.example.leaguecrud.rest;

import com.example.leaguecrud.request.MatchRequest;
import com.example.leaguecrud.response.MatchResponse;
import com.example.leaguecrud.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RepositoryRestController
public class MatchController {
    private MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/matches")
    public ResponseEntity<Object> createMatch(@RequestBody MatchRequest request) throws ChangeSetPersister.NotFoundException {
        MatchResponse response = matchService.createMatch(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

//    @PutMapping("/matches/{id}")
//    public ResponseEntity<MatchResponse> updateMatch(@PathVariable int id, @RequestBody MatchRequest request) throws ChangeSetPersister.NotFoundException {
//        MatchResponse response = matchService.updateMatch(id, request);
//        return ResponseEntity.ok(response);
//    }
}
