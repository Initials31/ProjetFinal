package com.apside.prono.service;

import com.apside.prono.errors.PlayersDonneeNulle;
import com.apside.prono.errors.PlayersInconnuException;
import com.apside.prono.model.Players;
import com.apside.prono.repository.PlayersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
public class PlayersServiceImpl implements PlayersService {

    private PlayersRepository playersRepository;

    public PlayersServiceImpl(@Autowired PlayersRepository playersRepository) {
        this.playersRepository = playersRepository;
    }

    @Override
    @Transactional
    public Players createPlayersRepo(Players players) {
        players.setSubscriptionDate(new Date());

        if ((players.getFirstName()==null) || (players.getLastName()==null) || (players.getMail()==null) || (players.getSubscriptionDate()==null)) {
            throw new PlayersDonneeNulle();
        } else {
            return playersRepository.save(players);
        }
    }

    @Override
    public Players getById(long id)throws PlayersInconnuException{
        Optional<Players> players = playersRepository.findById(id);

        if (players.isPresent()) {
            return players.get();
        } else {
            throw new PlayersInconnuException(id);
        }
    }


    @Override
    @Transactional(rollbackOn = PlayersInconnuException.class)
    public Players modifierPlayersRepo(long id, Players players) {
        Optional<Players> oPlayers = playersRepository.findById(id);

        if (oPlayers.isPresent()) {
            Players playerRepo = oPlayers.get();
            playerRepo.setFirstName(players.getFirstName());
            playerRepo.setLastName(players.getLastName());
            playerRepo.setMail(players.getMail());
            return playersRepository.save(playerRepo);
        } else {
            throw new PlayersInconnuException(id);
        }
    }

    @Override
    public Iterable<Players> getAllPlayersRepo() {
        return playersRepository.findAll();
    }

    @Override
    public void deletePlayersById(long id) throws PlayersInconnuException {
        Optional<Players> players = playersRepository.findById(id);

        if (players.isPresent()) {
            playersRepository.deleteById(id);
        } else {
            throw new PlayersInconnuException(id);
        }
    }

}
