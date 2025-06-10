package Domain;

import java.io.Serializable;
import java.util.Random;

public class Batalla implements Serializable {
    private Team team1, team2;
    private int turnoActual = 0;
    private transient PokeBatallaAccion siguienteAccion;
    private boolean batallaTerminada;
    private Random random = new Random();



    public Batalla(Team team1, Team team2) {
        if (team1 == null || team2 == null) {
            throw new InvalidTeam("Los equipos no pueden ser nulos");
        }
        this.team1 = team1;
        this.team2 = team2;
        this.turnoActual = 0;
        this.batallaTerminada = false;
    }

    public String LanzarMoneda() {
        turnoActual = random.nextInt(2);
        if(turnoActual == 0) {
            return "comienza el equipo 1";
        }
        return "comienza el equipo 2";
    }

    /**
     * Darle una accion de pokebatalla a la proxima accion
     *La accion es un objeto de cualquier clase q implemente PokeBatallaAccion
     *
     * @param accion La accion que guarda siguienteAccion .
     * @return void.
     */
    public void setAccion(PokeBatallaAccion accion) {
        if (accion == null) {throw new InvalidAction("La accion es nula/no valida");}
        this.siguienteAccion = accion;
    }




    /**
     * Ejecuta un turno de batalla
     *Dependiendo el tipo de "siguienteAccion" se evalua q hacer
     * los turnos se van intercalando entre 1 y 0
     *Este string q se retorna es el resultado de la accion
     * @return String.
     *
     */
    public String ejecutarBatalla() {


        Team atacante = turnoActual == 0 ? team1 : team2;
        Team defensor = turnoActual == 0 ? team2 : team1;

        String resultado = siguienteAccion.AccionBatalla(atacante, defensor);

        if (siguienteAccion instanceof PokeAbandonarAccion) {
            batallaTerminada = true;
            return resultado;
        }

        if (defensor.esDerrotado()) {
            batallaTerminada = true;
            return resultado + "\nEl equipo " + getTeamActual().getName() + " ha sido derrotado";
        }

        turnoActual = 1 - turnoActual;
        siguienteAccion = null;

        return resultado;
    }



    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }



    /**
     * Obtener el Team que esta atacando
     * Por defecto el team 1 es q tiene el turno 0
     * @return Team.
     */
    public Team getTeamActual() {
        return turnoActual == 0 ? team1 : team2;
    }

    public boolean BatallaTerminada() {
        return batallaTerminada;
    }
}
