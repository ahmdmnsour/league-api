package com.example.leaguecrud.rest;

import com.example.leaguecrud.request.RefereeRequest;
import com.example.leaguecrud.response.RefereeResponse;
import com.example.leaguecrud.service.RefereeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
public class RefereeController {
    private RefereeService refereeService;

    @Autowired
    public RefereeController(RefereeService refereeService) {
        this.refereeService = refereeService;
    }

    @PostMapping("/referees")
    public ResponseEntity<RefereeResponse> createReferee(@RequestBody RefereeRequest request) throws ChangeSetPersister.NotFoundException {
        RefereeResponse response = refereeService.createReferee(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/referees/{id}")
    public ResponseEntity<RefereeResponse> updateReferee(@PathVariable int id, @RequestBody RefereeRequest request) throws ChangeSetPersister.NotFoundException {
        RefereeResponse response = refereeService.updateReferee(id, request);
        return ResponseEntity.ok(response);
    }
}
