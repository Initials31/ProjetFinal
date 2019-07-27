package com.apside.prono.service;

import com.apside.prono.errors.ContestDonneeNulle;
import com.apside.prono.errors.ContestInconnuException;
import com.apside.prono.model.Contest;
import com.apside.prono.repository.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ContestServiceImpl implements ContestService {

    private ContestRepository contestRepository;

    public ContestServiceImpl(@Autowired ContestRepository contestRepository) {
        this.contestRepository = contestRepository;
    }

    @Override
    @Transactional
    public Contest createContestRepo(Contest contest){
        if (contest.getLabel()==null) {
            throw new ContestDonneeNulle();
        } else {
            return contestRepository.save(contest);
        }

    }

    @Override
    public Contest getById(long id) throws ContestInconnuException {
        Optional<Contest> contest = contestRepository.findById(id);

        if (contest.isPresent()) {
            return contest.get();
        } else {
            throw new ContestInconnuException(id);
        }
    }


    @Override
    @Transactional(rollbackOn = ContestInconnuException.class)
    public Contest modifierContestRepo(long id, Contest contest) throws ContestInconnuException {
        Optional<Contest> oContest = contestRepository.findById(id);

        if (oContest.isPresent()) {
            Contest contestRepo = oContest.get();
            contestRepo.setLabel(contest.getLabel());
            return contestRepository.save(contestRepo);
        } else {
            throw new ContestInconnuException(id);
        }
    }

    @Override
    public Iterable<Contest> getAllContestRepo() {
        return contestRepository.findAll();
    }

    @Override
    public void deleteContestById(long id) {
        Optional<Contest> contest = contestRepository.findById(id);

        if (contest.isPresent()) {
            contestRepository.deleteById(id);
        } else {
            throw new ContestInconnuException(id);
        }
    }
}
