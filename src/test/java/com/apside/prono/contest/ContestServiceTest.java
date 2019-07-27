package com.apside.prono.contest;

import com.apside.prono.errors.ContestDonneeNulle;
import com.apside.prono.errors.ContestInconnuException;
import com.apside.prono.model.Contest;
import com.apside.prono.repository.ContestRepository;
import com.apside.prono.service.ContestServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ContestServiceTest {

        @Mock
        private ContestRepository contestRepository;

        @InjectMocks
        private ContestServiceImpl contestServiceImpl;

        @Before
        public void setup() {
            MockitoAnnotations.initMocks(this);
        }

        private Contest contestMock = new Contest(5L, "Ligue 5");

        @Test
        public void canFindById () throws ContestInconnuException  {
            Optional<Contest> oContest = Optional.of(contestMock);

            when(contestRepository.findById(5L)).thenReturn(oContest);

            Contest contest = contestServiceImpl.getById(5L);

            assertEquals("Ligue 5", contest.getLabel());
        }

    @Test
    public void canFindAll () throws Exception {
        List<Contest> listeContest = new ArrayList<>();

        Contest contestEntity = new Contest();
        contestEntity.setId(12L);
        contestEntity.setLabel("Ligue 12");

        Contest contestEntity2 = new Contest();
        contestEntity2.setId(20L);
        contestEntity2.setLabel("Ligue 20");

        listeContest.add(contestEntity);
        listeContest.add(contestEntity2);

        when(contestRepository.findAll()).thenReturn(listeContest);

        Iterable<Contest> iContest = contestServiceImpl.getAllContestRepo();

        List<Contest> contest = new ArrayList<>();
        iContest.forEach(contest::add);

        /**for (Players toto : iPlayers) {
         joueurs.add(toto);
         }**/
    }

    @Test(expected = ContestDonneeNulle.class)
    public void testContestDonneeNulle() throws ContestDonneeNulle {
        Contest contestEntity = new Contest();
        contestEntity.setId(12L);
        contestEntity.setLabel(null);

        contestServiceImpl.createContestRepo(contestEntity);
    }

    @Test
    public void testCreateContest () throws ContestDonneeNulle {
        Contest contestEntity = new Contest();
        contestEntity.setId(12L);
        contestEntity.setLabel("Ligue 12");

        when(contestRepository.save(contestEntity)).thenReturn(contestEntity);

        Contest contest = contestServiceImpl.createContestRepo(contestEntity);

        assertEquals("Ligue 12", contest.getLabel());

    }

    @Test
    public void testDeleteContest () throws ContestInconnuException {

        Optional<Contest> oContest = Optional.of(contestMock);

        Contest contestDelete = new Contest();
        contestDelete.setId(15L);

        when(contestRepository.findById(15L)).thenReturn(oContest);

        contestServiceImpl.deleteContestById(contestDelete.getId());

        verify(contestRepository, times(1)).deleteById(15L);

    }

    @Test(expected = ContestInconnuException.class)
    public void testContestInconnuExceptionGetBy() throws ContestInconnuException {
        contestServiceImpl.getById(10L);
    }

    @Test(expected = ContestInconnuException.class)
    public void testContestInconnuExceptionDelete() throws ContestInconnuException {
        contestServiceImpl.deleteContestById(10L);
    }

    @Test
    public void canUpdateContest() throws ContestInconnuException {
        Optional<Contest> oContest = Optional.of(contestMock);

        Contest contestEntity = new Contest();
        contestEntity.setId(5L);
        contestEntity.setLabel("Ligue 12");

        when(contestRepository.findById(5L)).thenReturn(oContest);

        contestServiceImpl.modifierContestRepo(contestEntity.getId(), contestEntity);

        verify(contestRepository, times(1)).findById(contestEntity.getId());

    }

    @Test(expected = ContestInconnuException.class)
    public void testContestnconnuExceptionUpdate() throws ContestInconnuException {
        Contest contestEntity = new Contest();
        contestEntity.setId(10L);
        contestEntity.setLabel("Ligue 5");

        contestServiceImpl.modifierContestRepo(30L,contestEntity);
    }

}
