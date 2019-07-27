package com.apside.prono.evenement;

import com.apside.prono.errors.EvenementDonneeNulle;
import com.apside.prono.errors.EvenementInconnuException;
import com.apside.prono.model.Contest;
import com.apside.prono.model.Evenement;
import com.apside.prono.repository.EvenementRepository;
import com.apside.prono.service.EvenementServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class EvenementServiceTest {

    @Mock
    private EvenementRepository evenementRepository;

    @InjectMocks
    private EvenementServiceImpl evenementServiceImpl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    private Contest contestMock = new Contest(5L, "Ligue 5");

    private Evenement evenementMock = new Evenement(5L, "France-Algérie", new Date(), new Date(), new Date(), 2, contestMock);

    @Test
    public void canFindById () throws EvenementInconnuException  {
        Optional<Evenement> oEvenement = Optional.of(evenementMock);

        when(evenementRepository.findById(5L)).thenReturn(oEvenement);

        Evenement evenement = evenementServiceImpl.getById(5L);

        assertEquals("France-Algérie", evenement.getLabel());
    }

    @Test
    public void canFindAll () throws Exception {
        List<Evenement> listeEvenement = new ArrayList<>();

        Contest contestEntity = new Contest();
        contestEntity.setId(10L);
        contestEntity.setLabel("Ligue 5");

        Evenement evenementEntity = new Evenement();
        evenementEntity.setLabel("France-Allemagne");
        evenementEntity.setOpenDate(new Date());
        evenementEntity.setEventDate(new Date());
        evenementEntity.setCloseDate(new Date());
        evenementEntity.setCoeff(2);
        evenementEntity.setId(15L);
        evenementEntity.setContest(contestEntity);


        Evenement evenementEntity2 = new Evenement();
        evenementEntity2.setLabel("France-Italie");
        evenementEntity2.setOpenDate(new Date());
        evenementEntity2.setEventDate(new Date());
        evenementEntity2.setCloseDate(new Date());
        evenementEntity2.setCoeff(4);
        evenementEntity2.setId(18L);
        evenementEntity2.setContest(contestEntity);

        listeEvenement.add(evenementEntity);
        listeEvenement.add(evenementEntity);

        when(evenementRepository.findAll()).thenReturn(listeEvenement);

        Iterable<Evenement> iEvenement = evenementServiceImpl.getAllEvenementRepo();

        List<Evenement> evenements = new ArrayList<>();
        iEvenement.forEach(evenements::add);

    }

    @Test(expected = EvenementDonneeNulle.class)
    public void testEvenementsDonneeNulle() throws EvenementDonneeNulle {
        Evenement evenement = new Evenement();

        Contest contestEntity = new Contest();
        contestEntity.setId(10L);
        contestEntity.setLabel("Ligue 5");

        Evenement evenementEntity = new Evenement();
        evenementEntity.setLabel("France-Allemagne");
        evenementEntity.setOpenDate(new Date());
        evenementEntity.setEventDate(new Date());
        evenementEntity.setCloseDate(null);
        evenementEntity.setCoeff(2);
        evenementEntity.setId(15L);
        evenementEntity.setContest(contestEntity);

        evenementServiceImpl.createEvenementRepo(evenementEntity);
    }

    @Test
    public void testCreateEvenement () throws EvenementDonneeNulle {
        Contest contestEntity = new Contest();
        contestEntity.setId(10L);
        contestEntity.setLabel("Ligue 5");

        Evenement evenementEntity = new Evenement();
        evenementEntity.setLabel("France-Allemagne");
        evenementEntity.setOpenDate(new Date());
        evenementEntity.setEventDate(new Date());
        evenementEntity.setCloseDate(new Date());
        evenementEntity.setCoeff(2);
        evenementEntity.setId(15L);
        evenementEntity.setContest(contestEntity);

        when(evenementRepository.save(evenementEntity)).thenReturn(evenementEntity);

        Evenement evenement = evenementServiceImpl.createEvenementRepo(evenementEntity);

        assertEquals("France-Allemagne", evenement.getLabel());

    }

    @Test
    public void testDeleteEvenement () throws EvenementInconnuException {

        Optional<Evenement> oEvenement = Optional.of(evenementMock);

        Evenement evenementDelete = new Evenement();
        evenementDelete.setId(15L);

        when(evenementRepository.findById(15L)).thenReturn(oEvenement);

        evenementServiceImpl.deleteEvenementById(evenementDelete.getId());

        verify(evenementRepository, times(1)).deleteById(15L);

    }

    @Test(expected = EvenementInconnuException.class)
    public void testEvenementInconnuExceptionGetBy() throws EvenementInconnuException{
        evenementServiceImpl.getById(10L);
    }

    @Test(expected = EvenementInconnuException.class)
    public void testEvenementInconnuExceptionDelete() throws EvenementInconnuException {
        evenementServiceImpl.deleteEvenementById(10L);
    }

    @Test
    public void canUpdateEvenement() throws EvenementInconnuException {
        Optional<Evenement> oEvenement = Optional.of(evenementMock);

        Contest contestEntity = new Contest();
        contestEntity.setId(10L);
        contestEntity.setLabel("Ligue 5");

        Evenement evenementEntity = new Evenement();
        evenementEntity.setLabel("France-Allemagne");
        evenementEntity.setOpenDate(new Date());
        evenementEntity.setEventDate(new Date());
        evenementEntity.setCloseDate(new Date());
        evenementEntity.setCoeff(2);
        evenementEntity.setId(5L);
        evenementEntity.setContest(contestEntity);

        when(evenementRepository.findById(5L)).thenReturn(oEvenement);

        evenementServiceImpl.modifierEvenementRepo(evenementEntity.getId(), evenementEntity);

        verify(evenementRepository, times(1)).findById(evenementEntity.getId());

    }

    @Test(expected = EvenementInconnuException.class)
    public void testEvenementnconnuExceptionUpdate() throws EvenementInconnuException {
        Contest contestEntity = new Contest();
        contestEntity.setId(10L);
        contestEntity.setLabel("Ligue 5");

        Evenement evenementEntity = new Evenement();
        evenementEntity.setLabel("France-Allemagne");
        evenementEntity.setOpenDate(new Date());
        evenementEntity.setEventDate(new Date());
        evenementEntity.setCloseDate(new Date());
        evenementEntity.setCoeff(2);
        evenementEntity.setId(5L);
        evenementEntity.setContest(contestEntity);

        evenementServiceImpl.modifierEvenementRepo(6L,evenementEntity);
    }

}

