package Domain;


import java.io.Serializable;

public class PokeCambiarAccion implements PokeBatallaAccion, Serializable {

    private Pokemon pokemon;

    public  PokeCambiarAccion(Pokemon pokemon) {
        this.pokemon = pokemon;
    }


    /**
     *Llama el pokecambio en team
     * @return String.
     */
    @Override
    public String AccionBatalla(Team atacante, Team defensor) {
            atacante.pokecambio(pokemon);
            return "El jugador cambió a " + pokemon.getNombre();
    }




}




