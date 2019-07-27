package com.apside.prono.controller;

import com.apside.prono.errors.PlayersInconnuException;
import com.apside.prono.model.Players;
import com.apside.prono.service.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class PlayersRestController {

    @Autowired
    private PlayersService playersService;

    @GetMapping(produces = "application/json", path="/players/{id}")
    public Players getById(@PathVariable long id) throws PlayersInconnuException {
        return playersService.getById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json", path="/players/create")
    public ResponseEntity<Players> create(@RequestBody Players players, UriComponentsBuilder uriBuilder) throws PlayersInconnuException {

        playersService.createPlayersRepo(players);
        //playersService.getById(players.getId());


        URI location = uriBuilder.path("/players/{id}").buildAndExpand(players.getId()).toUri();
        return ResponseEntity.created(location).body(players);
    }

    @GetMapping(produces = "application/json", path="/players")
    public Iterable<Players> getAll() throws PlayersInconnuException  {
        return playersService.getAllPlayersRepo();
    }

    @PostMapping(produces = "application/json", path="/players/update/{id}")
    public Players modifier(@PathVariable long id, @RequestBody Players players) throws PlayersInconnuException {
        return playersService.modifierPlayersRepo(id, players);

    }

    @PostMapping (produces = "application/json", path="/players/delete/{id}")
    public void deletePlayersById(@PathVariable long id) throws PlayersInconnuException {
        playersService.deletePlayersById(id);
    }

}
