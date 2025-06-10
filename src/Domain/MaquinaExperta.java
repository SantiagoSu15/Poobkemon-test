package Domain;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Random;

public  class MaquinaExperta implements TipoDeMaquina, Serializable {
    private Random random = new Random();

    private Pokemon nuevoPokeActual(Team team){
        Pokemon atacante = team.getPokeActual();
        if(!atacante.getEstado()){
            atacante = team.getPokemons().stream().
                    filter(p-> p.getEstado())
                    .findFirst().orElse(null);
            return atacante;
        }
        return atacante;
    }

    private Movimiento movimientoDeAtaque(Pokemon pokemon, String tipoRival) {
        if (pokemon.getPokeMoves().isEmpty()) {
            return null;
        }
        Movimiento mov = pokemon.getPokeMoves().stream().filter(m -> "Estado".equals(m.getCategoria()) &&
                        m.getEfectoSecundario() != null &&
                        m.getEfectoSecundario().isAttackPower() &&
                        m.getPuntosPoderRestantes() > 0)
                .findFirst()
                .orElse(null);
        if (mov == null) {
            mov = pokemon.getPokeMoves().stream().filter(m -> "Especial".equals(m.getCategoria()) &&
                            m.getPuntosPoderRestantes() > 0 &&
                            pokemon.getEfectividadesTipo().definirEfectividades()
                                    .getOrDefault(tipoRival, 1.0) > 1.0)
                    .sorted(Comparator.comparing(Movimiento::getPotencia).reversed())
                    .findFirst()
                    .orElse(null);
            if (mov == null) {
                mov = pokemon.getPokeMoves().stream().filter(m -> "Especial".equals(m.getCategoria()) &&
                                m.getPuntosPoderRestantes() > 0)
                        .sorted(Comparator.comparing(Movimiento::getPotencia).reversed())
                        .findFirst()
                        .orElse(null);
            }
        }
        return mov;
    }

    private  Movimiento movimientoDeDefensa(Pokemon pokemon){
        Movimiento mov = pokemon.getPokeMoves().stream()
                .filter(movimiento ->
                        "Estado".equals(movimiento.getCategoria()) ||
                                (movimiento.getEfectoSecundario() != null && movimiento.getEfectoSecundario().getmodSpecialDef() > 0)
                )
                .findFirst()
                .orElse(null);
        return mov;
    }

    private Movimiento movimientoSemiDefensivo(Pokemon pokemon){
        Movimiento mov = pokemon.getPokeMoves().stream()
                .filter(movimiento -> "Estado".equals(movimiento.getCategoria()) && movimiento.getEfectoSecundario() != null &&
                        movimiento.getEfectoSecundario().isSemiDefensive() && movimiento.getPuntosPoderRestantes() >0)
                .findFirst().orElse(null);
        return mov;
    }

    /**
     * Sobresicribir el metodo de TipoDeMaquina
     *
     *
     * @return PokeBatallaAccion.
     */


    @Override
    public PokeBatallaAccion decidirAccion(Team propio, Team rival) {
        Pokemon atacante = this.nuevoPokeActual(propio);

        if (atacante == null) {
            return new PokeAbandonarAccion();
        }

        if (atacante.getPs() < atacante.getMaxPs() / 2) {
            Item pocion = propio.getInventario().stream()
                    .filter(i -> i.getEfecto().equals("Curar"))
                    .findFirst()
                    .orElse(null);

            if (pocion != null) {
                return new PokeUsarItemAccion(pocion,atacante);
            }
        }

        Pokemon defensor = rival.getPokeActual();
        String tipoRival = defensor.getTipo();

        Pokemon pokePrincipal = propio.getPokemons().stream()
                .filter(p -> p.getEstado())
                .filter(p -> p.getEfectividadesTipo().definirEfectividades()
                        .getOrDefault(tipoRival, 1.0) > 1.0)
                .findFirst()
                .orElse(atacante);


        Movimiento mov = null;
        if ((defensor.getPs() < defensor.getMaxPs() / 4 && atacante.getPs() > atacante.getMaxPs() / 2) ||
                atacante.getPs() == atacante.getMaxPs() || atacante.getPs() > 2 * defensor.getPs()) {
            mov = movimientoDeAtaque(pokePrincipal, tipoRival);
        } else if (atacante.getPs() > atacante.getMaxPs() / 4) {
            mov = movimientoDeDefensa(pokePrincipal);
        } else {
            mov = movimientoSemiDefensivo(pokePrincipal);
        }
        if (mov == null) {
            mov = pokePrincipal.getPokeMoves().get(random.nextInt(pokePrincipal.getPokeMoves().size()));
        }
        if (propio.getPokeActual() != pokePrincipal) {
            return new PokeCambiarAccion(pokePrincipal);
        } else {
            return new PokeAtaqueAccion(mov);
        }
    }
}