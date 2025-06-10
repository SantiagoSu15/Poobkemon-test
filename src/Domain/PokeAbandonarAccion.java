package Domain;

import java.io.Serializable;

public class PokeAbandonarAccion implements PokeBatallaAccion, Serializable {
    private Boolean abandonado;

    private void PokeAbandonarAccion(Boolean abandonado) {
        this.abandonado = true;
    }

    /**
     * Abandona un equipo poniendo su estado en derrotado
     *
     * @return String.
     */
    @Override
    public String AccionBatalla(Team atacante, Team defensor) {
        boolean derrotado = atacante.esDerrotado() ;
        return "\nEl equipo  ha abandonado";
    }
}
