package Domain;


import java.io.Serializable;

public class PokeUsarItemAccion implements PokeBatallaAccion, Serializable {
    private Item item;
    private Pokemon pokemon;
    public  PokeUsarItemAccion(Item item, Pokemon pokemon) {
        this.item = item;
                this.pokemon = pokemon;
    }

    /**
     * llama usarItem del item
     *
     * @return String.
     */
    @Override
    public String AccionBatalla(Team atacante, Team defensor){

        if(pokemon == null){
            pokemon =  atacante.getPokeActual();
        }
        return item.usarItemEn(pokemon);
    }
}
