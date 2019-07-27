package com.apside.prono.controller;

import com.apside.prono.errors.ContestInconnuException;
import com.apside.prono.model.Contest;
import com.apside.prono.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class ContestRestController {

    @Autowired
    private ContestService contestService;

    @GetMapping(produces = "application/json", path="/contest/{id}")
    public Contest getById(@PathVariable long id) throws ContestInconnuException {
        return contestService.getById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json", path="/contest/create")
    public ResponseEntity<Contest> create(@RequestBody Contest contest, UriComponentsBuilder uriBuilder) throws ContestInconnuException {

        contestService.createContestRepo(contest);
        //contestService.getById(contest.getId());


        URI location = uriBuilder.path("/contest/{id}").buildAndExpand(contest.getId()).toUri();
        return ResponseEntity.created(location).body(contest);
    }

    @GetMapping(produces = "application/json", path="/contest")
    public Iterable<Contest> getAll() throws ContestInconnuException  {
        return contestService.getAllContestRepo();
    }

    @PostMapping(produces = "application/json", path="/contest/update/{id}")
    public Contest modifier(@PathVariable long id, @RequestBody Contest contest) throws ContestInconnuException {
        return contestService.modifierContestRepo(id, contest);

    }

    @PostMapping (produces = "application/json", path="/contest/delete/{id}")
    public void deleteContestById(@PathVariable long id) throws ContestInconnuException {
        contestService.deleteContestById(id);
    }

}
