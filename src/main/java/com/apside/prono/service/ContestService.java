package com.apside.prono.service;

import com.apside.prono.errors.ContestInconnuException;
import com.apside.prono.model.Contest;

public interface ContestService {

    Contest createContestRepo(Contest contest);

    Contest getById (long id) throws ContestInconnuException;

    Contest modifierContestRepo (long id, Contest contest);

    Iterable<Contest> getAllContestRepo();

    void deleteContestById (long id);
}
