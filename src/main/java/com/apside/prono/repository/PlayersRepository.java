package com.apside.prono.repository;

import com.apside.prono.model.Players;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayersRepository extends CrudRepository<Players, Long> {

}
