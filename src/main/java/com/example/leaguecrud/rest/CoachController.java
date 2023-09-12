package com.example.leaguecrud.rest;

import com.example.leaguecrud.request.CoachRequest;
import com.example.leaguecrud.response.CoachResponse;
import com.example.leaguecrud.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
public class CoachController {
    private CoachService coachService;

    @Autowired
    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @PostMapping("/coaches")
    public ResponseEntity<CoachResponse> createCoach(@RequestBody CoachRequest request) throws ChangeSetPersister.NotFoundException {
        CoachResponse response = coachService.createCoach(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/coaches/{id}")
    public ResponseEntity<CoachResponse> updateCoach(@PathVariable int id, @RequestBody CoachRequest request) throws ChangeSetPersister.NotFoundException {
        CoachResponse response = coachService.updateCoach(id, request);
        return ResponseEntity.ok(response);
    }
}
