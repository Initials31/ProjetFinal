package com.apside.prono.players;


import com.apside.prono.errors.PlayersDonneeNulle;
import com.apside.prono.errors.PlayersInconnuException;
import com.apside.prono.model.Players;
import com.apside.prono.repository.PlayersRepository;
import com.apside.prono.service.PlayersServiceImpl;
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
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class PlayersServiceTest {

    private static final String LIBELLE_PLAYER1 = "player1@player.com";
    private static final String LIBELLE_PLAYER2 = "player2@player.com";

    @Mock
    private PlayersRepository playersRepository;

    @InjectMocks
    private PlayersServiceImpl playersService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    private Players playersMock = new Players(10L, "Victor", "Yololo", "bonjour@gmail.com", new Date() );

   @Test
    public void canFindById () throws PlayersInconnuException  {
       Optional<Players> oPlayers = Optional.of(playersMock);

       when(playersRepository.findById(10L)).thenReturn(oPlayers);

       Players players = playersService.getById(10L);

       assertEquals("Victor", players.getFirstName());
   }

   @Test
    public void canFindAll () throws Exception {
       List<Players> listePlayers = new ArrayList<>();

       Players playerEntity = new Players();
       playerEntity.setId(1L);
       playerEntity.setMail(LIBELLE_PLAYER1);

       Players playerEntity2 = new Players();
       playerEntity2.setId(2L);
       playerEntity2.setMail(LIBELLE_PLAYER2);

       listePlayers.add(playerEntity);
       listePlayers.add(playerEntity2);

       when(playersRepository.findAll()).thenReturn(listePlayers);

       Iterable<Players> iPlayers = playersService.getAllPlayersRepo();

       List<Players> joueurs = new ArrayList<>();
       iPlayers.forEach(joueurs::add);

       /**for (Players toto : iPlayers) {
           joueurs.add(toto);
       }**/
   }

    @Test(expected = PlayersDonneeNulle.class)
    public void testPlayersDonneeNulle() throws PlayersDonneeNulle {
        Players players = new Players();

        players.setFirstName("vincent");
        players.setLastName("lololo");
        players.setMail(null);

        playersService.createPlayersRepo(players);
    }

    @Test
    public void testCreatePlayers () throws PlayersDonneeNulle {
       Players players = new Players();
        players.setId(20L);
        players.setFirstName("vincent");
        players.setLastName("lololo");
        players.setMail("vincent@play.com");

        when(playersRepository.save(players)).thenReturn(players);

        Players players2 = playersService.createPlayersRepo(players);

        assertEquals("vincent", players2.getFirstName());

    }

    @Test
    public void testDeletePlayers () throws PlayersInconnuException {

       Optional<Players> oPlayers = Optional.of(playersMock);

       Players playersDelete = new Players();
       playersDelete.setId(15L);

       when(playersRepository.findById(15L)).thenReturn(oPlayers);

       playersService.deletePlayersById(playersDelete.getId());

       verify(playersRepository, times(1)).deleteById(15L);

    }

    @Test(expected = PlayersInconnuException.class)
    public void testPlayersInconnuExceptionGetBy() throws PlayersInconnuException {
       playersService.getById(10L);
    }

    @Test(expected = PlayersInconnuException.class)
    public void testPlayersInconnuExceptionDelete() throws PlayersDonneeNulle {
        playersService.deletePlayersById(10L);
    }

    @Test
    public void canUpdatePlayers() throws PlayersInconnuException {
        Optional<Players> oPlayers = Optional.of(playersMock);

        Players players = new Players();
        players.setId(10L);
        players.setFirstName("vincent");
        players.setLastName("lololo");
        players.setMail("vincent@play.com");

        when(playersRepository.findById(10L)).thenReturn(oPlayers);

        playersService.modifierPlayersRepo(players.getId(), players);

        verify(playersRepository, times(1)).findById(players.getId());
    }

    @Test(expected = PlayersInconnuException.class)
    public void testPlayersInconnuExceptionUpdate() throws PlayersDonneeNulle {
        Players players = new Players();
        players.setId(10L);
        players.setFirstName("vincent");
        players.setLastName("lololo");
        players.setMail("vincent@play.com");

        playersService.modifierPlayersRepo(11L,players);
    }


}
