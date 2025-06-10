package Domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Team implements Serializable {
    private ArrayList<Pokemon> pokemons;
    private ArrayList<Item> inventario;
    private Pokemon pokeActual;
    private String nombre;

    public Team() {
        this.pokemons = new ArrayList<>();
        this.inventario = new ArrayList<>();
        this.pokeActual = null;
        this.nombre = "";

    }
    public void setTeamName(String nombre) {
        this.nombre = nombre;
    }
    public String getName(){
        return this.nombre;
    }

    public String agregarPokemon(Pokemon p) {
        if(pokemons.isEmpty()) {
            pokeActual = p;
        }
        if (pokemons.size() < 6) {
            this.pokemons.add(p);
            return "pokemon agregado";
        } else {
            return "El equipo ya tiene 6 Pokemon";
        }
    }

    public Pokemon getPokeActual() {
        return pokeActual;
    }

    public void setPokeActual(Pokemon pokeActual) {
        this.pokeActual = pokeActual;
    }

    public Pokemon getPokemon(int index) {
        return pokemons.get(index);
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }


    public void agregarItem(Item i) {
        this.inventario.add(i);
    }

    public boolean esDerrotado(){
        for(Pokemon p: pokemons){
            if(p.getEstado()){
                return false;
            }
        }
        return true;
    }

    public Movimiento elegirMovimiento(Movimiento m) {
        for(Movimiento mov: pokeActual.getPokeMoves()){
            if(mov.equals(m)){
                return mov;
            }
        }
        return null;
    }

    public Pokemon elegirPokemon(Pokemon p) {
        for(Pokemon poke: pokemons){
            if(poke.equals(p)){
                return poke;
            }
        }
        return null;
    }

    public Item elegirItem(Item i) {
        for(Item it: inventario){
            if(it.equals(i)){
                return it;
            }
        }
        return null;
    }

    public String pokeAtaque(Team teamEnemigo,Movimiento ataque) {
        if(ataque == null){ throw new InvalidMove("ataque nulo/no valido"); }
        if(teamEnemigo == null){throw new InvalidTeam("Team nulo/no valido");}

        ArrayList<Movimiento> movimientos = pokeActual.getPokeMoves();
        for(Movimiento m: movimientos){
            if(ataque.equals(m) && !ataque.getCategoria().equals("Estado")){
                int dano = ataque.getDano(pokeActual,teamEnemigo.getPokeActual(),ataque.getPotencia(),ataque.getCategoria());
                teamEnemigo.getPokeActual().RecibirDano(dano);
                if(!teamEnemigo.getPokeActual().getEstado()){
                    return teamEnemigo.getPokeActual().getNombre() + " ha quedado fuera de combate";
                }
                return pokeActual.getNombre() +" ha usado " + ataque.getNombre() + " contra " + teamEnemigo.getPokeActual().getNombre();
            }
            if(ataque.equals(m) && ataque.getCategoria().equals("Estado")){
                ataque.getEfectoSecundario().aplicarEfecto(this.pokeActual,teamEnemigo.getPokeActual());
                return this.getPokeActual().getNombre()+"ha utilizado"+ ataque.getEfectoSecundario().getNombre();
            }
        }
        return pokeActual.getNombre() + " no posee ese movimiento ";
    }

    public void pokecambio(Pokemon p) {
        if(pokemons.contains(p) && p.getEstado()){
            pokeActual = p;
        }
    }

    public Pokemon actualizarPokeActual(){
        if(this.pokemons.isEmpty()){
            throw new InvalidTeam("no se puede actualizar poke, team vacio");
        }
        for(Pokemon poke : pokemons){
            if (poke.getEstado()){
                this.pokeActual = poke;
                return poke;

            }
        }
        return null;
    }
    public ArrayList<Item> getInventario() {
        return inventario;
    }
}