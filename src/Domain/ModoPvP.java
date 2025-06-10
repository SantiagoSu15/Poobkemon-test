package Domain;

import java.io.Serializable;
import java.util.ArrayList;


public class ModoPvP extends ModoBatalla implements Serializable {

    public ModoPvP(PokemonsInGame pokemonsInGame, ItemsInGame itemsInGame) {
        super(pokemonsInGame, itemsInGame);
    }

    /**
     * Sobresicribir el metodo de ModoBatalla
     *Se crean 2 teams con los metodos en modoBatalla
     *
     * @return batalla.
     */
    @Override
    public Batalla comenzarBatalla(ArrayList<Pokemon> pokemonesEquipo1, ArrayList<Item> itemsEquipo1,ArrayList<Pokemon> pokemonesEquipo2, ArrayList<Item> itemsEquipo2) {
        Team team1 = createTeam(pokemonesEquipo1, itemsEquipo1);
        Team team2 = createTeam(pokemonesEquipo2, itemsEquipo2);
        Batalla batalla = new Batalla(team1, team2);
        System.out.println(batalla.LanzarMoneda());
        return batalla;
    }
}