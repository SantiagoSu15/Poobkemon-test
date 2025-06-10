package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TipoBicho implements EfectividadesTipo, Serializable {
    private final Map<String, Double> efectividades = new HashMap<>();
    private ArrayList<Movimiento> movimientos = new ArrayList<>();

    public TipoBicho() {
        definirEfectividades();
    }

    @Override
    public Map<String, Double> definirEfectividades() {
        efectividades.put("Planta", 2.0);
        efectividades.put("Psíquico", 2.0);
        efectividades.put("Siniestro", 2.0);
        efectividades.put("Fuego", 0.5);
        efectividades.put("Lucha", 0.5);
        efectividades.put("Veneno", 0.5);
        efectividades.put("Volador", 0.5);
        efectividades.put("Fantasma", 0.5);
        efectividades.put("Acero", 0.5);
        efectividades.put("Hada", 0.5);
        return efectividades;
    }

    @Override
    public String getNombre() {
        return "Bicho";
    }

    public double obtenerEfectividadContra(String tipoDefensor) {
        return efectividades.getOrDefault(tipoDefensor, 1.0);
    }
    @Override
    public  ArrayList<Movimiento> movimientosPredeterminados(){
        EfectosPredeterminados efectosPredeterminados = new EfectosPredeterminados();
        movimientos.add(new Movimiento("Picadura", "Bicho", "Físico", 60, 100, 20,null));
        movimientos.add(new Movimiento("Disparo Demora", "Bicho", "Estado", 0, 95, 40,efectosPredeterminados.getEfecto("Disparo Demora")));
        movimientos.add(new Movimiento("Corte Furia", "Bicho", "Físico", 40, 95, 20,null));

        return movimientos;
    }
}
