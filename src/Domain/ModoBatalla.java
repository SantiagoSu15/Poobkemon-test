package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;


/**
 * Clase para los distintos modos de juego
 *
 *
 */
public abstract class ModoBatalla implements Serializable {
    protected PokemonsInGame pokemonsInGame;
    protected ItemsInGame itemsInGame;
    protected Random random;


    public ModoBatalla(PokemonsInGame pokemonsInGame, ItemsInGame itemsInGame) {
        this.pokemonsInGame = pokemonsInGame;
        this.itemsInGame = itemsInGame;
        this.random = new Random();
    }

    public abstract Batalla comenzarBatalla(ArrayList<Pokemon> pokemonesEquipo1, ArrayList<Item> itemsEquipo1,ArrayList<Pokemon> pokemonesEquipo2, ArrayList<Item> itemsEquipo2);

    protected Team createTeam(ArrayList<Pokemon> pokemons, ArrayList<Item> items) {
        if(pokemons ==null || items ==null){throw new InvalidItem("Error al crear el team, pokemones o items nulos");}
        Team team = new Team();
        for (Pokemon p : pokemons) {
            team.agregarPokemon(p);
        }
        for (Item item : items) {
            team.agregarItem(item);
        }
        return team;
    }

    protected void randomTeam(Maquina team, int maxPokemons, int maxItems) {
        while (team.getPokemons().size() < maxPokemons) {
            int indicePokes = random.nextInt(pokemonsInGame.getPokemons().size());
            Pokemon pokeAleatorio = pokemonsInGame.getPokemons().get(indicePokes);
            team.agregarPokemon(pokeAleatorio);
        }
        while (team.getInventario().size() < maxItems) {
            int indiceItems = random.nextInt(itemsInGame.getItems().size());
            Item itemMaquina = itemsInGame.getItems().get(indiceItems);
            team.agregarItem(itemMaquina);
        }
    }
}

