package Domain;

import java.io.Serializable;
import java.util.ArrayList;

public class ModoPvM extends ModoBatalla implements Serializable {
    private TipoDeMaquina tipoMaquina1;


    public ModoPvM(PokemonsInGame pokemonsInGame, ItemsInGame itemsInGame, TipoDeMaquina tipoDeMaquina) {
        super(pokemonsInGame, itemsInGame);
        this.tipoMaquina1 = tipoDeMaquina;
    }


    /**
     * Sobresicribir el metodo de ModoBatalla
     *Se crean 2 teams con los metodos en modoBatalla  y la maquinas
     *
     * @return batalla.
     */
    @Override
    public Batalla comenzarBatalla(ArrayList<Pokemon> pokemonesEquipo1, ArrayList<Item> itemsEquipo1,
                                   ArrayList<Pokemon> pokemonesEquipo2, ArrayList<Item> itemsEquipo2) {
        Team team1 = createTeam(pokemonesEquipo1, itemsEquipo1);

        Maquina maquina1 = new Maquina(tipoMaquina1);
        randomTeam(maquina1, 6, 4);
        Maquina team2 = maquina1;
        Batalla batalla = new Batalla(team1, team2);
        return batalla;
    }
 }
