import Domain.Movimiento;
import Domain.Pokemon;
import Domain.Tipo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class PokemonTest {

    @Test
    public void shouldCreatePoobkemonGame() {
        Pokemon test = new Pokemon("Blastoise","Agua","Blastoise pertenece a la primera generación de pokemon, proveniente de Kanto, y es la última evolución de\n" +
                "Squirtle. Es una enorme tortuga bípeda equipada con dos poderosos cañones situados en su espalda capaces\n" +
                "de disparar potentes chorros de agua, con la fuerza suficiente para quebrar muros de hormigón o perforar\n" +
                "en planchas de acero. Para evitar el retroceso, se planta firmemente sus patas traseras en el suelo y, sube\n" +
                "deliberadamente de peso.",362,291,328,280,295,339
                ,"/Recursos/Imagenes/Pokemones/PokemonsFrente/blastoise.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/blastoise.png","/Recursos/Imagenes/Pokemones/Iconos/blastoise.png");
        Map<String, Double> efectividades = test.getEfectividades();

        assertNotNull(efectividades);
        System.out.println(efectividades);
        Map<String, Double> efectividades2 = new HashMap<>();
        efectividades2.put("Fuego", 2.0);
        efectividades2.put("Roca", 2.0);
        efectividades2.put("Tierra", 2.0);
        efectividades2.put("Agua", 0.5);
        efectividades2.put("Planta", 0.5);
        efectividades2.put("Dragón", 0.5);
        assertEquals(efectividades, efectividades2);

        Double nume = test.getEfectividadContra("Planta");
        assertTrue(nume == 0.5);
    }


    @Test
    public void shouldHaveMovements(){
        Pokemon test = new Pokemon("Blastoise","Agua","Blastoise pertenece a la primera generación de pokemon, proveniente de Kanto, y es la última evolución de\n" +
                "Squirtle. Es una enorme tortuga bípeda equipada con dos poderosos cañones situados en su espalda capaces\n" +
                "de disparar potentes chorros de agua, con la fuerza suficiente para quebrar muros de hormigón o perforar\n" +
                "en planchas de acero. Para evitar el retroceso, se planta firmemente sus patas traseras en el suelo y, sube\n" +
                "deliberadamente de peso.",362,291,328,280,295,339
                ,"/Recursos/Imagenes/Pokemones/PokemonsFrente/blastoise.png","/Recursos/Imagenes/Pokemones/PokemonsEspalda/blastoise.png","/Recursos/Imagenes/Pokemones/Iconos/blastoise.png");
        assertNotNull(test.getPokeMoves());
        test.getPokeMoves().stream().forEach(m -> System.out.println(m.getNombre()));
    }


    @Test
    public void shouldHaveMovementsForEachType(){
        Tipo tipoObj = new Tipo();
        HashMap<String, ArrayList<Movimiento>> moves = tipoObj.getMovimientos();
        for(Map.Entry<String, ArrayList<Movimiento>> tipo : moves.entrySet()){
            String tipoName = tipo.getKey();
            ArrayList<Movimiento> movimientos = tipo.getValue();
            System.out.println(tipoName + movimientos.toString());
        }
        for(Movimiento m :tipoObj.getTiposMovimientos("Hielo") ){
            System.out.println(m.getNombre());
        }

        assertNotNull(tipoObj.getMovimientos());
    }

}
