package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TipoSiniestro implements EfectividadesTipo, Serializable {
    private final Map<String, Double> efectividades = new HashMap<>();
    private ArrayList<Movimiento> movimientos = new ArrayList<>();

    public TipoSiniestro() {
        definirEfectividades();
    }

    @Override
    public Map<String, Double> definirEfectividades() {
        efectividades.put("Psíquico", 2.0);
        efectividades.put("Fantasma", 2.0);
        efectividades.put("Lucha", 0.5);
        efectividades.put("Siniestro", 0.5);
        efectividades.put("Hada", 0.5);
        return efectividades;
    }

    @Override
    public String getNombre() {
        return "Siniestro";
    }

    public double obtenerEfectividadContra(String tipoDefensor) {
        return efectividades.getOrDefault(tipoDefensor, 1.0);
    }
    @Override
    public  ArrayList<Movimiento> movimientosPredeterminados(){
        EfectosPredeterminados efectosPredeterminados = new EfectosPredeterminados();
        movimientos.add(new Movimiento("Mordisco", "Siniestro", "Físico", 60, 100, 25,null));
        movimientos.add(new Movimiento("Golpe Bajo", "Siniestro", "Estado", 0, 100, 5,efectosPredeterminados.getEfecto("Golpe Bajo")));
        movimientos.add(new Movimiento("Juego Sucio", "Siniestro", "Físico", 95, 100, 15,null));

        return movimientos;
    }
}
