package com.apside.prono.service;

import com.apside.prono.model.Players;

public interface PlayersService {

    public Players createPlayersRepo(Players players);

    public Players getById (long id);

    public Players modifierPlayersRepo (long id, Players players);

    public Iterable<Players> getAllPlayersRepo();

    public void deletePlayersById (long id);

}
