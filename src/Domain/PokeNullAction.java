package Domain;

import java.io.Serializable;
import java.util.ArrayList;


public class PokeNullAction implements PokeBatallaAccion, Serializable {

    public PokeNullAction() {}

    @Override
    public String AccionBatalla(Team atacante, Team defensor){
        ArrayList<String> movim= new ArrayList<>();

        Pokemon pokeActual = atacante.getPokeActual();
        for(Movimiento m : pokeActual.getPokeMoves()){
            if(m.getCategoria().equals("Especial")){
                m.restarPP();
                movim.add(m.getNombre());
            }
        }
        return "Los movimientos: "+ movim.toString() + "Perdieron un pp";
    }
}
