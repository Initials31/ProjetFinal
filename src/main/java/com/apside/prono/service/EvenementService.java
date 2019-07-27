package com.apside.prono.service;

import com.apside.prono.model.Evenement;

public interface EvenementService {

    Evenement createEvenementRepo(Evenement evenement);

    Evenement getById (long id);

    Evenement modifierEvenementRepo (long id, Evenement evenement);

    Iterable<Evenement> getAllEvenementRepo();

    void deleteEvenementById (long id);
}
