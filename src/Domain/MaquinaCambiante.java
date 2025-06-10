package Domain;

import java.io.Serializable;
import java.util.Map;
import java.util.Random;

public class MaquinaCambiante implements TipoDeMaquina, Serializable {
    private Random random = new Random();



    /**
     * Sobresicribir el metodo de TipoDeMaquina
     *
     *
     * @return PokeBatallaAccion.
     */
    @Override
    public PokeBatallaAccion decidirAccion(Team propio, Team rival) {
        Pokemon atacante = propio.getPokeActual();
        for(Map.Entry<String, Double> tipo : atacante.getEfectividades().entrySet()){
            String defensor = tipo.getKey();
            double efec = tipo.getValue();
            System.out.println(defensor +" "+efec);
        }

        Pokemon defensor = rival.getPokeActual();
        System.out.println(defensor.getNombre() +" "+defensor.getTipo());
        String tipoRival = defensor.getTipo();

        if (!atacante.getEstado()) {
            atacante = propio.getPokemons().stream()
                    .filter(p -> p.getEstado())
                    .findFirst().orElse(null);
            if (atacante == null) {
                return new PokeAbandonarAccion();
            }
        }

        Pokemon mejorOpcion = propio.getPokemons().stream()
                .filter(p -> p.getEstado())
                .filter(p -> p.getEfectividadesTipo().definirEfectividades()
                        .getOrDefault(tipoRival, 1.0) > 1.0)
                .findFirst()
                .orElse(atacante);

        Movimiento mov = mejorOpcion.getPokeMoves().get(random.nextInt(mejorOpcion.getPokeMoves().size()));
        System.out.println(mejorOpcion.getNombre());
        System.out.println(propio.getPokeActual().getNombre());
        if (propio.getPokeActual() != mejorOpcion) {

            return new PokeCambiarAccion(mejorOpcion);
        } else {
            return new PokeAtaqueAccion(mov);
        }
    }
}