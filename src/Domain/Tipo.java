package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static java.util.Collections.addAll;


public class Tipo implements Serializable {
    private final String nombre;
    private HashMap<String,ArrayList<Movimiento>> movimientos;
    private static final ArrayList<String> tiposMovimientos = new ArrayList<>(Arrays.asList(
            "Fuego", "Agua", "Electrico", "Planta", "Bicho", "Normal", "Lucha", "Veneno",
            "Tierra", "Roca", "Volador", "Hielo", "Psiquico", "Fantasma", "Siniestro", "Dragon", "Acero", "Hada"
    ));

    public Tipo(){
        this.nombre = "";
        this.movimientos = new HashMap<String,ArrayList<Movimiento>>();
        this.generarAtaques();
    }




    public void generarAtaques(){
        for(String s : tiposMovimientos){
            try{
                ArrayList<Movimiento> moves = new ArrayList<>();
                String nombreClase = "Domain.Tipo" + s;
                Class<?> clase = Class.forName(nombreClase);
                EfectividadesTipo claseTipo =  (EfectividadesTipo) clase.getDeclaredConstructor().newInstance();
                moves.addAll(claseTipo.movimientosPredeterminados());
                this.movimientos.put(s,moves);
            }catch(Exception e){
                throw new InvalidPokeTypeClass("No se pudo instanciar la clase de tipo para: " + s, e);
            }
        }
    }

    public HashMap<String,ArrayList<Movimiento>> getMovimientos() {
        return movimientos;
    }
    public ArrayList<Movimiento> getTiposMovimientos(String tipo) {
        return movimientos.get(tipo);
    }
    public String getNombre() {
        return nombre;
    }

    public static ArrayList<String> getTipos() {
        return new ArrayList<>(tiposMovimientos);
    }
}
