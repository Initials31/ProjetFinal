package com.apside.prono.service;

import com.apside.prono.errors.EvenementDonneeNulle;
import com.apside.prono.errors.EvenementInconnuException;
import com.apside.prono.model.Evenement;
import com.apside.prono.repository.EvenementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class EvenementServiceImpl implements EvenementService {

    private EvenementRepository evenementRepository;

    public EvenementServiceImpl(@Autowired EvenementRepository evenementRepository) {
        this.evenementRepository = evenementRepository;
    }

    @Override
    @Transactional
    public Evenement createEvenementRepo(Evenement evenement) {
        if ((evenement.getCloseDate()==null) || (evenement.getCoeff()<=1) || (evenement.getEventDate()==null) || (evenement.getLabel()==null) || (evenement.getOpenDate()==null) || (evenement.getContest()==null)) {
                throw new EvenementDonneeNulle();
        } else {
            return evenementRepository.save(evenement);
        }
    }

    @Override
    public Evenement getById(long id) throws EvenementInconnuException {
        Optional<Evenement> evenement = evenementRepository.findById(id);

        if (evenement.isPresent()) {
            return evenement.get();
        } else {
            throw new EvenementInconnuException(id);
        }
    }


    @Override
    @Transactional(rollbackOn = EvenementInconnuException.class)
    public Evenement modifierEvenementRepo(long id, Evenement evenement) {
        Optional<Evenement> oEvenement = evenementRepository.findById(id);

        if (oEvenement.isPresent()) {
            Evenement evenementRepo = oEvenement.get();
            evenementRepo.setCloseDate(evenement.getCloseDate());
            evenementRepo.setEventDate(evenement.getEventDate());
            evenementRepo.setOpenDate(evenement.getOpenDate());
            evenementRepo.setLabel(evenement.getLabel());
            evenementRepo.setCoeff(evenement.getCoeff());
            evenementRepo.setContest(evenement.getContest());
            return evenementRepository.save(evenementRepo);
        } else {
            throw new EvenementInconnuException(id);
        }
    }

    @Override
    public Iterable<Evenement> getAllEvenementRepo() {
        return evenementRepository.findAll();
    }

    @Override
    public void deleteEvenementById(long id) {

        Optional<Evenement> evenement = evenementRepository.findById(id);

        if (evenement.isPresent()) {
            evenementRepository.deleteById(id);
        } else {
            throw new EvenementInconnuException(id);
        }
    }

}
