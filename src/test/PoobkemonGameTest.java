
import Domain.Item;
import Domain.Pokemon;
import Domain.PoobkemonGame;
import Domain.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public  class PoobkemonGameTest {
    private PoobkemonGame game;
    private ArrayList<Pokemon> pokemonsInGame;
    private ArrayList<Item> itemsInGame;
    private ArrayList<Pokemon> pokemonsTeam1;
    private ArrayList<Pokemon> pokemonsTeam2;
    private ArrayList<Item> itemsTeam1;
    private ArrayList<Item> itemsTeam2;


    @Before
    public  void setUp(){
        game = new PoobkemonGame();
        itemsInGame = game.getItemsInGame().getItems();
        pokemonsInGame = game.getPokemonsInGame().getPokemons();
        pokemonsTeam1 = new ArrayList<>();
        pokemonsTeam2 = new ArrayList<>();
        itemsTeam1 = new ArrayList<>();
        itemsTeam2 = new ArrayList<>();
        for (int i = 0; i < 10 && i < pokemonsInGame.size(); i++) {
            if (i % 2 == 0) {
                pokemonsTeam1.add(pokemonsInGame.get(i));
            } else {
                pokemonsTeam2.add(pokemonsInGame.get(i));
            }
        }
        for (int i = 0; i < 2 && i < itemsInGame.size(); i++) {
            if (i % 2 == 0) {
                itemsTeam1.add(itemsInGame.get(i));
            } else {
                itemsTeam2.add(itemsInGame.get(i));
            }
        }


    }


    @Test
    public void shouldCreatePoobkemonGame() {
        assertNotNull(game.getPokemonsInGame());
        assertNotNull(game.getItemsInGame());
    }

    @Test
    public void shouldCreateTeam(){
        Team team1 = game.createTeam(pokemonsTeam1, itemsTeam1);
        Team team2 = game.createTeam(pokemonsTeam2, itemsTeam2);
        assertNotNull(team1);
        assertNotNull(team2);

    }

    @Test
    public void shouldCreatecrearTipoMaquina(){
        String defensiva = "Defensiva";
        String ofensiva = "Ofensiva";
        String cambiante = "Cambiante";
        String experta = "Experta";

        TipoDeMaquina maquinaDefensiva = game.crearTipoMaquina(defensiva);
        TipoDeMaquina maquinaOfensiva = game.crearTipoMaquina(ofensiva);
        TipoDeMaquina maquinaCambiante = game.crearTipoMaquina(cambiante);



        assertTrue(maquinaDefensiva instanceof MaquinaDefensiva);
        assertTrue(maquinaOfensiva instanceof  MaquinaAtacante);
        assertTrue(maquinaCambiante instanceof  MaquinaCambiante);
    }

    @Test
    public void isBattleFinished(){
        assertTrue(game.isBatallaTerminada());
    }

    @Test
    public void survivalBattle(){
        game.startSurvivalBattle();
        assertNotNull(game.getBatalla());
    }

    @Test
    public void shouldCreateSurvivalBattle(){
        String gameType = "PvP";
        game.startBattle(gameType,null,null,null,null,null);
        assertTrue(game.isSurvivalBattle());
    }

    @Test
    public void shouldCreatePvPBattle(){
        String gameType = "PvP";
        game.startBattle(gameType,null,pokemonsTeam1,itemsTeam1,pokemonsTeam2,itemsTeam2);
        assertTrue(game.getModoBatalla() instanceof  ModoPvP);
    }

    @Test
    public void shouldCreatePvMPBattle(){
        String gameType = "PvM";
        assertTrue("PvM".equals(gameType));
        game.startBattle(gameType,"Defensiva",pokemonsTeam2,itemsTeam2,null,null);
        assertTrue(game.getModoBatalla() instanceof  ModoPvM);
    }

    @Test
    public void shouldCreateMvMPBattle(){
        String gameType = "MvM";
        game.startBattle(gameType,"Defensiva",pokemonsTeam2,itemsTeam2,null,null);
        assertTrue(game.getModoBatalla() instanceof  ModoMvM);
    }

    @Test
    public void shoudlChangePokemon(){
        String gameType = "PvM";
        game.startBattle(gameType,"Defensiva",pokemonsTeam2,itemsTeam2,null,null);
        Pokemon nuevoPokem =  game.getTeam1().getPokemons().get(1);
        game.PokemonSelection(nuevoPokem);
        game.executeBattle();

        Pokemon pokeActu =  game.getTeam1().getPokeActual();
        assertEquals(nuevoPokem, pokeActu);
    }


    @Test
    public void shouldRunBattle(){
        String gameType = "PvM";
        game.startBattle(gameType,"Defensiva",pokemonsTeam2,itemsTeam2,null,null);
        game.RunSelection();
        game.executeBattle();
        assertTrue(game.isBatallaTerminada());
    }

    @Test
    public void shouldAttackBattle(){
        String gameType = "PvM";
        game.startBattle(gameType,"Defensiva",pokemonsTeam2,itemsTeam2,null,null);
        Movimiento mov = game.getTeamActual().getPokeActual().getPokeMoves().get(1);
        int dano = mov.getDano(game.getTeamActual().getPokeActual(),game.getTeam2().getPokeActual(),mov.getPotencia(),mov.getCategoria());

        int pp = mov.getPuntosPoder();
        game.AttackSelection(mov);
        game.executeBattle();

        Pokemon pokeAfectado = game.getTeamActual().getPokeActual();
        int vida = pokeAfectado.getPs();
        assertEquals(pokeAfectado.getMaxPs()-dano, vida);
        game.AttackSelection(mov);
        game.executeBattle();

        assertTrue(pp-1 == mov.getPuntosPoderRestantes());
    }

}
