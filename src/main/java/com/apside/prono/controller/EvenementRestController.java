package com.apside.prono.controller;


import com.apside.prono.errors.EvenementInconnuException;
import com.apside.prono.errors.PlayersInconnuException;
import com.apside.prono.model.Evenement;
import com.apside.prono.service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class EvenementRestController {

    @Autowired
    private EvenementService evenementService;

    @GetMapping(produces = "application/json", path="/evenement/{id}")
    public Evenement getById(@PathVariable long id) throws EvenementInconnuException {
        return evenementService.getById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json", path="/evenement/create")
    public ResponseEntity<Evenement> create(@RequestBody Evenement evenement, UriComponentsBuilder uriBuilder) throws EvenementInconnuException {

        evenementService.createEvenementRepo(evenement);
        //evenementService.getById(evenement.getId());


        URI location = uriBuilder.path("/evenement/{id}").buildAndExpand(evenement.getId()).toUri();
        return ResponseEntity.created(location).body(evenement);
    }

    @GetMapping(produces = "application/json", path="/evenement")
    public Iterable<Evenement> getAll() throws EvenementInconnuException  {
        return evenementService.getAllEvenementRepo();
    }

    @PostMapping(produces = "application/json", path="/evenement/update/{id}")
    public Evenement modifier(@PathVariable long id, @RequestBody Evenement evenement) throws EvenementInconnuException {
        return evenementService.modifierEvenementRepo(id, evenement);

    }

    @PostMapping (produces = "application/json", path="/evenement/delete/{id}")
    public void deleteEvenementById(@PathVariable long id) throws EvenementInconnuException {
        evenementService.deleteEvenementById(id);
    }

}
