package Domain;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class MaquinaDefensiva implements TipoDeMaquina, Serializable {

    private int contadorTurnos = 0;
    private Random random = new Random();


    /**
     * Sobresicribir el metodo de TipoDeMaquina
     *
     *
     * @return PokeBatallaAccion.
     */
    @Override
    public PokeBatallaAccion decidirAccion(Team propio, Team rival) {
        System.out.println("defensiva");
        contadorTurnos++;
        Pokemon actual = propio.getPokeActual();
        List<Movimiento> movimientos = actual.getPokeMoves();

        if (contadorTurnos % 3 != 0) {
            for (Movimiento m : movimientos) {
                if (m.getCategoria().equals("Estado") &&
                        (m.getEfectoSecundario().getModDef() > 0 || m.getEfectoSecundario().getmodSpecialDef() > 0)) {
                    return new PokeAtaqueAccion(m);
                }
            }
        }

        Movimiento movAleatorio = movimientos.get(random.nextInt(movimientos.size()));
        return new PokeAtaqueAccion(movAleatorio);
    }
}

