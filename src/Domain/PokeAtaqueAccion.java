package Domain;

import java.io.Serializable;

public class PokeAtaqueAccion implements PokeBatallaAccion, Serializable {
    private Movimiento movimiento;

    public  PokeAtaqueAccion(Movimiento movimiento) {
        this.movimiento = movimiento;
    }


    /**
     * Saca el resultado al llamar el metodo de atacar en teams
     *Si el pokemon atacante muere se le cambia automaticamente
     * @return String.
     */
    @Override
    public String AccionBatalla(Team atacante, Team defensor) {
        String resultado = atacante.pokeAtaque(defensor, movimiento);
        movimiento.usarMovimiento();
        System.out.println(defensor.getPokeActual().getPs() + "/" + defensor.getPokeActual().getMaxPs());
        if(!defensor.getPokeActual().getEstado()){
            Pokemon nuevo = defensor.actualizarPokeActual();
            defensor.pokecambio(nuevo);

            resultado += "\n" + defensor.getPokeActual().getNombre() + " ha entrado al combate.";
        }
        return resultado;
    }
}