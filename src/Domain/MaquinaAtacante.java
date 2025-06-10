package Domain;

import java.io.Serializable;
import java.util.Comparator;


public class MaquinaAtacante implements TipoDeMaquina, Serializable {




    /**
     * Sobresicribir el metodo de TipoDeMaquina
     *
     *
     * @return PokeBatallaAccion.
     */
    @Override
    public PokeBatallaAccion decidirAccion(Team propio, Team rival) {
        System.out.println("Atacante");
        Pokemon pokePrincipal = propio.getPokemons().stream().filter(s -> s.getEstado())
                .sorted(Comparator.comparing(Pokemon::getAtaque).reversed())
                .findFirst().orElse(null);;
        if(propio.getPokeActual()!=pokePrincipal){
            return new PokeCambiarAccion(pokePrincipal);
        }
        if(pokePrincipal != null){
            Movimiento mov = pokePrincipal.getPokeMoves().stream()
                    .filter(movimiento -> "Estado".equals(movimiento.getCategoria()) && movimiento.getEfectoSecundario() != null &&
                    movimiento.getEfectoSecundario().isAttackPower() && movimiento.getPuntosPoderRestantes() >0).findFirst().orElse(null);

            if(mov == null){
                mov = pokePrincipal.getPokeMoves().stream().filter(movimiento -> "Especial".equals(movimiento.getCategoria()) && movimiento.getPuntosPoderRestantes() >0).sorted(Comparator.comparing(Movimiento::getPotencia).reversed()).findFirst().orElse(null);
            }
            if(mov == null){
                mov = pokePrincipal.getPokeMoves().stream().filter(movimiento -> movimiento.getPuntosPoderRestantes() >0).sorted(Comparator.comparing(Movimiento::getPotencia).reversed()).findFirst().orElse(null);
            }
            if(mov != null){
                return new PokeAtaqueAccion(mov);
            }

        }


        return new PokeAbandonarAccion();
    }

}