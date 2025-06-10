package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TipoLucha implements EfectividadesTipo, Serializable {
    private final Map<String, Double> efectividades = new HashMap<>();
    private ArrayList<Movimiento> movimientos = new ArrayList<>();

    public TipoLucha() {
        definirEfectividades();
    }

    @Override
    public Map<String, Double> definirEfectividades() {
        efectividades.put("Normal", 2.0);
        efectividades.put("Hielo", 2.0);
        efectividades.put("Roca", 2.0);
        efectividades.put("Siniestro", 2.0);
        efectividades.put("Acero", 2.0);
        efectividades.put("Veneno", 0.5);
        efectividades.put("Volador", 0.5);
        efectividades.put("Psíquico", 0.5);
        efectividades.put("Bicho", 0.5);
        efectividades.put("Hada", 0.5);
        efectividades.put("Fantasma", 0.0);
        return efectividades;
    }

    @Override
    public String getNombre() {
        return "Lucha";
    }

    public double obtenerEfectividadContra(String tipoDefensor) {
        return efectividades.getOrDefault(tipoDefensor, 1.0);
    }

    @Override
    public  ArrayList<Movimiento> movimientosPredeterminados(){
        EfectosPredeterminados efectosPredeterminados = new EfectosPredeterminados();
        movimientos.add(new Movimiento("Golpe Karate", "Lucha", "Físico", 50, 100, 25,null));
        movimientos.add(new Movimiento("Doble Patada", "Lucha", "Físico", 30, 100, 30,null));
        movimientos.add(new Movimiento("Reversión", "Lucha", "Físico", 100, 100, 15,null));

        return movimientos;
    }
}
