package com.apside.prono.repository;

import com.apside.prono.model.Contest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestRepository extends CrudRepository<Contest, Long> {

}
