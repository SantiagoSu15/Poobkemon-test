package Domain;

import java.io.Serializable;
import java.util.ArrayList;

public class ModoMvM extends ModoBatalla implements Serializable {
    private TipoDeMaquina tipoMaquina1;
    private TipoDeMaquina tipoMaquina2;


    /**
     * En modo batalla, el TipoDeMaquina da la estrategia q la maquina usara
     *
     *
     * @return .
     */
    public ModoMvM(PokemonsInGame pokemonsInGame, ItemsInGame itemsInGame, TipoDeMaquina tipoDeMaquina, TipoDeMaquina tipoDeMaquina2) {
        super(pokemonsInGame, itemsInGame);
        this.tipoMaquina1 = tipoDeMaquina;
        this.tipoMaquina2 = tipoDeMaquina2;
    }

    /**
     * Sobresicribir el metodo de ModoBatalla
     *Se crean 2 teams con los metodos en modoBatalla  y las maquinas
     *
     * @return batalla.
     */

    @Override
    public Batalla comenzarBatalla(ArrayList<Pokemon> pokemonesEquipo1, ArrayList<Item> itemsEquipo1,ArrayList<Pokemon> pokemonesEquipo2, ArrayList<Item> itemsEquipo2){
        Maquina maquina1 = new Maquina(tipoMaquina1);
        Maquina maquina2 = new Maquina(tipoMaquina2);
        randomTeam(maquina1,6,4);
        randomTeam(maquina2,6,4);
        Batalla batalla = new Batalla(maquina1, maquina2);
        return batalla;
    }
}
